package axl.compiler.lexer;

import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenStreamFrame;
import axl.compiler.lexer.data.TokenType;

import java.util.List;

public interface TokenStream {

    boolean hasNext();

    Token next();

    Token peek();

    void back();

    TokenStreamFrame saveFrame();

    void restoreFrame(TokenStreamFrame frame);

    void setContext(List<TokenType> allowed);
}
