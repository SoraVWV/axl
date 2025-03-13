package axl.compiler.lexer.impl.feature;

import axl.compiler.lexer.data.Lexer;
import axl.compiler.lexer.data.LexerFrame;
import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.lexer.exception.IllegalLexerContentException;

public class LexerLiteral {

    public Token tokenizeNumber(Lexer lexer) {
        if (lexer.peek(0) == '0') {
            if (lexer.peek(1) == 'x' || lexer.peek(1) == 'X')
                return this.tokenizeHexNumber(lexer);
            else if (lexer.peek(1) == 'b' || lexer.peek(1) == 'B')
                return this.tokenizeBinNumber(lexer);
            else if (lexer.peek(1) == '.')
                return this.tokenizeFloatingPointNumber(lexer);
            else if (lexer.peek(1) == '_' || isNumber(lexer.peek(1)))
                return this.tokenizeDecNumber(lexer);

            lexer.next();
            return Token.of(TokenType.DEC_NUMBER);
        }

        if (lexer.peek(0) == '.') {
            if (!isNumber(lexer.peek(1))) {
                lexer.next();
                return Token.of(TokenType.DOT);
            }

            return this.tokenizeFloatingPointNumber(lexer);
        }

        return this.tokenizeDecNumber(lexer);
    }

    private Token tokenizeHexNumber(Lexer lexer) {
        lexer.next(2);
        int cnt = 0;
        boolean zeroStart = true;

        if (lexer.peek() != '0') {
            zeroStart = false;
            cnt++;
        }

        if (!isHexNumber(lexer.next()))
            throw new IllegalLexerContentException(lexer.getFrame(), "Numeric literal must start with a digit");

        boolean lastUnderscore = false;

        for (; ; ) {
            if (isHexNumber(lexer.peek())) {
                lastUnderscore = false;
                if (lexer.peek() != '0' && zeroStart) {
                    zeroStart = false;
                    cnt++;
                }
                lexer.next();
            } else if (lexer.peek() == '_') {
                lastUnderscore = true;
                lexer.next();
            } else {
                break;
            }
        }

        if (lastUnderscore)
            throw new IllegalLexerContentException(lexer.getFrame(), "Numeric literal cannot have an underscore as its last character");

        if (lexer.peek() == 'L' || lexer.peek() == 'l') {
            if (cnt > 16)
                throw new IllegalLexerContentException(lexer.getFrame(), "Value of numeric literal is too large");
            lexer.next();
            return Token.of(TokenType.HEX_LONG_NUMBER);
        }

        if (cnt > 8)
            throw new IllegalLexerContentException(lexer.getFrame(), "Value of numeric literal is too large");

        return Token.of(TokenType.HEX_NUMBER);
    }

    private Token tokenizeBinNumber(Lexer lexer) {
        lexer.next(2);
        int cnt = 0;
        boolean zeroStart = true;

        if (lexer.peek() != '0') {
            zeroStart = false;
            cnt++;
        }
        if (!isBinNumber(lexer.next()))
            throw new IllegalLexerContentException(lexer.getFrame(), "Numeric literal must start with a digit");

        boolean lastUnderscore = false;

        for (; ; ) {
            if (isBinNumber(lexer.peek())) {
                lastUnderscore = false;
                if (lexer.peek() != '0' && zeroStart) {
                    zeroStart = false;
                    cnt++;
                }
                lexer.next();
            } else if (lexer.peek() == '_') {
                lastUnderscore = true;
                lexer.next();
            } else {
                break;
            }
        }

        if (lastUnderscore)
            throw new IllegalLexerContentException(lexer.getFrame(), "Numeric literal cannot have an underscore as its last character");

        if (lexer.peek() == 'L' || lexer.peek() == 'l') {
            if (cnt > 64)
                throw new IllegalLexerContentException(lexer.getFrame(), "Value of numeric literal is too large");

            lexer.next();

            return Token.of(TokenType.BIN_LONG_NUMBER);
        }

        if (cnt > 32)
            throw new IllegalLexerContentException(lexer.getFrame(), "Value of numeric literal is too large");

        return Token.of(TokenType.BIN_NUMBER);
    }

    private Token tokenizeDecNumber(Lexer lexer) {
        tokenizeDecPart(lexer, true);

        if (lexer.peek() == '.' || lexer.peek() == 'E' || lexer.peek() == 'e')
            return tokenizeFloatingPointNumber(lexer);

        if (lexer.peek() == 'L' || lexer.peek() == 'l') {
            lexer.next();

            return Token.of(TokenType.DEC_LONG_NUMBER);
        }

        if (lexer.peek() == 'F' || lexer.peek() == 'f') {
            lexer.next();

            return Token.of(TokenType.FLOAT_NUMBER);
        }

        if (lexer.peek() == 'D' || lexer.peek() == 'd') {
            lexer.next();

            return Token.of(TokenType.DOUBLE_NUMBER);
        }

        return Token.of(TokenType.DEC_NUMBER);
    }

    private Token tokenizeFloatingPointNumber(Lexer lexer) {
        lexer.restoreFrame();

        boolean exp = false;
        tokenizeDecPart(lexer, false);

        if (lexer.peek() == '.') {
            lexer.next();
            tokenizeDecPart(lexer, true);
        }

        if (lexer.peek() == 'E' || lexer.peek() == 'e') {
            switch (lexer.next()) {
                case '-', '+':
                    lexer.next();
                default:
            }

            tokenizeDecPart(lexer, true);
            exp = true;
        }

        if (lexer.peek() == 'F' || lexer.peek() == 'f') {
            lexer.next();

            return Token.of(exp ? TokenType.FLOAT_EXP_NUMBER : TokenType.FLOAT_NUMBER);
        }

        if (lexer.peek() == 'D' || lexer.peek() == 'd')
            lexer.next();

        return Token.of(exp ? TokenType.DOUBLE_EXP_NUMBER : TokenType.DOUBLE_NUMBER);
    }

    private void tokenizeDecPart(Lexer lexer, boolean req) {
        boolean firstUnderscore = true;
        boolean lastUnderscore = false;
        boolean hasNumber = false;

        for (; ; ) {
            if (isNumber(lexer.peek())) {
                lastUnderscore = false;
                firstUnderscore = false;
                hasNumber = true;
                lexer.next();
            } else if (lexer.peek() == '_') {
                if (firstUnderscore)
                    throw new IllegalLexerContentException(lexer.getFrame(), "Numeric literal cannot have an underscore as it's first or last character");

                lastUnderscore = true;
                lexer.next();
            } else {
                break;
            }
        }

        if (!hasNumber && req)
            throw new IllegalLexerContentException(lexer.getFrame(), "Numeric literal cannot terminate with a dot");

        if (lastUnderscore)
            throw new IllegalLexerContentException(lexer.getFrame(), "Numeric literal cannot have an underscore as it's last character");
    }

    public Token tokenizeString(Lexer lexer) {
        if (!lexer.getContext().contains(TokenType.STRING_LITERAL))
            throw new IllegalStateException();

        lexer.next();
        while (lexer.peek() != '"') {
            if (lexer.isEnd())
                throw new IllegalLexerContentException(lexer.getFrame(), "String literal is not completed");

            if (lexer.peek() == '\\')
                this.readEscape(lexer, '"');
            else
                lexer.next();
        }
        lexer.next();

        return Token.of(TokenType.STRING_LITERAL);
    }

    public Token tokenizeChar(Lexer lexer) {
        if (!lexer.getContext().contains(TokenType.CHAR_LITERAL))
            throw new IllegalStateException();

        lexer.next();

        if (lexer.peek() == '\\')
            readEscape(lexer, '\'');
        else
            lexer.next();

        if (lexer.next() != '\'')
            throw new IllegalLexerContentException(lexer.getFrame(), "Symbol literal is not completed");

        return Token.of(TokenType.CHAR_LITERAL);
    }

    private void readEscape(Lexer lexer, char allowed) {
        LexerFrame frame = lexer.currentFrame();

        lexer.next();
        switch (lexer.peek()) {
            case '\'':
            case '"':
                if (lexer.peek() != allowed)
                    break;
            case 'n':
            case 't':
            case 'r':
            case '0':
            case '\\':
                lexer.next();
                return;
            case 'u':
                lexer.next();
                for (int i = 0; i < 4; i++) {
                    if (isHexNumber(lexer.next()))
                        continue;

                    throw new IllegalLexerContentException(frame, "Invalid unicode");
                }
                return;
        }

        throw new IllegalLexerContentException(frame, "Invalid escape sequence");
    }

    public static boolean isNumber(char current) {
        return ('0' <= current && current <= '9');
    }

    public static boolean isHexNumber(char current) {
        return ('0' <= current && current <= '9')
                || ('a' <= current && current <= 'f')
                || ('A' <= current && current <= 'F');
    }

    public static boolean isBinNumber(char current) {
        return current == '0' || current == '1';
    }
}
