package axl.compiler.lexer;

import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.LexerFrame;
import axl.compiler.lexer.data.TokenType;

import java.util.List;

public interface TokenStream {

    boolean hasNext();

    Token next();

    Token peek();

    LexerFrame saveFrame();

    void restoreFrame(LexerFrame frame);

    void setContext(List<TokenType> allowed);

    List<TokenType> getContext();

    int peekLastLine(LexerFrame frame);
}
