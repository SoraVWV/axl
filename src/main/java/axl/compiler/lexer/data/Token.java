package axl.compiler.lexer.data;

import lombok.Data;

@Data
public class Token {

    private int line;

    private int column;

    private int length;

    private int offset;

    private TokenType type;

    public static Token of(TokenType type) {
        Token token = new Token();
        token.type = type;
        return token;
    }
}
