package axl.compiler.lexer.data;

public interface Token {

    int getLine();

    int getColumn();

    TokenType getType();
}
