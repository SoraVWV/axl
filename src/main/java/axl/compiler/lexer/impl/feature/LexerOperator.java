package axl.compiler.lexer.impl.feature;

import axl.compiler.lexer.data.Lexer;
import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenGroup;
import axl.compiler.lexer.data.TokenType;

public class LexerOperator implements LexerFeature {

    @Override
    public Token tokenize(Lexer lexer) {
        int currentLength = 0;
        TokenType current = null;

        for (TokenType type : lexer.getContext()) {
            if (type.getGroup() != TokenGroup.OPERATOR && type.getGroup() != TokenGroup.DELIMITER)
                continue;

            String representation = type.getRepresentation();
            if (!isRepresentation(lexer, representation))
                continue;

            if (currentLength < representation.length()) {
                currentLength = representation.length();
                current = type;
            }
        }

        if (current == null)
            throw new IllegalArgumentException();
//            throw new IllegalLexicalException("Unknown symbol", this, frame);

        if (current == TokenType.MINUS) {
            if (
                    lexer.getToken() == null ||
                            lexer.getToken().getType().getGroup() == TokenGroup.OPERATOR ||
                            lexer.getToken().getType() == TokenType.LEFT_PARENT ||
                            lexer.getToken().getType() == TokenType.LEFT_SQUARE ||
                            lexer.getToken().getType() == TokenType.RETURN ||
                            lexer.getToken().getType() == TokenType.THIS
            )
                current = TokenType.UNARY_MINUS;
        }

        lexer.next(currentLength);
        return Token.of(current);
    }

    private boolean isRepresentation(Lexer lexer, String representation) {
        for (int i = 0; i < representation.length(); i++)
            if (lexer.peek(i) != representation.charAt(i))
                return false;

        return true;
    }
}
