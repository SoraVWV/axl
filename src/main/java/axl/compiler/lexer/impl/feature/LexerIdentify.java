package axl.compiler.lexer.impl.feature;

import axl.compiler.lexer.data.Lexer;
import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenGroup;
import axl.compiler.lexer.data.TokenType;

public class LexerIdentify implements LexerFeature {

    @Override
    public Token tokenize(Lexer lexer) {
        do {
            lexer.next();
        } while (isIdentifierPart(lexer.peek()));

        TokenType type = getByRepresentation(lexer, lexer.slice());

        if (type == null) {
            if (!lexer.getContext().contains(TokenType.IDENTIFY))
                throw new IllegalStateException();

            type = TokenType.IDENTIFY;
        }

        return Token.of(type);
    }

    private TokenType getByRepresentation(Lexer lexer, String representation) {
        for (TokenType type: lexer.getContext()) {
            if (type.getGroup() != TokenGroup.KEYWORD)
                continue;

            if (type.getRepresentation().equals(representation))
                return type;
        }

        return null;
    }

    public static boolean isIdentifierStart(char current) {
        return Character.isLetter(current) || current == '_';
    }

    public static boolean isIdentifierPart(char current) {
        return isIdentifierStart(current) || LexerLiteral.isNumber(current);
    }
}
