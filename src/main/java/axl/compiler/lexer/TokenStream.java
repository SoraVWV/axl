package axl.compiler.lexer;

import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenFrame;
import axl.compiler.lexer.data.TokenType;

public interface TokenStream {

    boolean hasNext();

    Token next();

    Token peek();

    void back();

    TokenFrame saveFrame();

    void restoreFrame(TokenFrame frame);

    void enable(TokenType type);

    void disable(TokenType type);
}
