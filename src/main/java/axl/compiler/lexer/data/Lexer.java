package axl.compiler.lexer.data;

import lombok.Data;

import java.util.List;

@Data
public class Lexer {

    private String content;

    private List<TokenType> context;

    private int offset = 0;

    private int line = 1;

    private int column = 1;

    private Token token;

    private LexerFrame frame;

    public void next(int n) {
        for (int i = 0; i < n; i++)
            next();
    }

    public char next() {
        char result = peek();
        if (result == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }
        offset++;

        return result;
    }

    public char peek(int n) {
        int offset = this.offset + n;
        if (offset >= getContent().length())
            return '\0';

        return getContent().charAt(offset);
    }

    public char peek() {
        return peek(0);
    }

    public boolean isEnd() {
        return offset >= getContent().length();
    }

    public void skip() {
        for (;;) {
            if (peek(0) == '/' && peek(1) == '*')
                skipMultilineComment();
            else if (peek(0) == '/' && peek(1) == '/')
                skipSingleComment();
            else if (peek() == ' ' || peek() == '\t' || peek() == '\n' || peek() == '\r')
                next();
            else
                break;
        }
    }

    public void skipSingleComment() {
        next(2);

        while (peek() != '\r' && peek() != '\n' && peek() != '\0')
            next();

        next();
    }

    public void skipMultilineComment() {
//        TokenizerFrame localFrame = currentFrame();
        next(2);

        while (peek(0) != '*' || peek(1) != '/') {
            if (isEnd())
                throw new IllegalArgumentException();
//                throw new IllegalLexicalException("Multiline comment was not closed", this, localFrame);

            next();
        }

        next(2);
    }

    public void createFrame() {
        this.frame = currentFrame();
    }

    public void restoreFrame() {
        this.offset = frame.getOffset();
        this.column = frame.getColumn();
        this.line = frame.getLine();
    }

    public LexerFrame currentFrame() {
        return new LexerFrame(line, column, offset);
    }

    public String slice() {
        return getContent()
                .substring(
                        frame.getOffset(),
                        getOffset()
                );
    }
}
