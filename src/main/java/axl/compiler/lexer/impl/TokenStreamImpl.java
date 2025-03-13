package axl.compiler.lexer.impl;

import axl.compiler.lexer.TokenStream;
import axl.compiler.lexer.data.Lexer;
import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.LexerFrame;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.lexer.impl.feature.LexerFeature;
import axl.compiler.lexer.impl.feature.LexerIdentify;
import axl.compiler.lexer.impl.feature.LexerLiteral;
import lombok.Data;

import java.util.List;

@Data
public class TokenStreamImpl implements TokenStream {

    private Lexer lexer;

    private LexerFeature identify;

    private LexerFeature operator;

    private LexerLiteral literal;

    @Override
    public boolean hasNext() {
        return !lexer.isEnd();
    }

    @Override
    public Token next() {
        lexer.createFrame();
        if (lexer.isEnd())
            throw new IllegalStateException("Calling a tokenizer on a completed file");

        Token token;

        if (LexerIdentify.isIdentifierStart(lexer.peek()))
            token = identify.tokenize(lexer);
        else if (LexerLiteral.isNumber(lexer.peek()) || lexer.peek() == '.')
            token = literal.tokenizeNumber(lexer);
        else if (lexer.peek() == '"')
            token = literal.tokenizeString(lexer);
        else if (lexer.peek() == '\'')
            token = literal.tokenizeChar(lexer);
        else
            token = operator.tokenize(lexer);

        token.setOffset(lexer.getFrame().getOffset());
        token.setLine(lexer.getFrame().getLine());
        token.setColumn(lexer.getFrame().getColumn());
        token.setOffset(lexer.getFrame().getOffset());
        token.setLength(lexer.getOffset() - lexer.getFrame().getOffset());

        lexer.skip();
        lexer.setToken(token);

        return token;
    }

    @Override
    public Token peek() {
        return null;
    }

    @Override
    public void back() {

    }

    @Override
    public LexerFrame saveFrame() {
        return null;
    }

    @Override
    public void restoreFrame(LexerFrame frame) {

    }

    @Override
    public void setContext(List<TokenType> allowed) {
        lexer.setContext(allowed);
    }
}
