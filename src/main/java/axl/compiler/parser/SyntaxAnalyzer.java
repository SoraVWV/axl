package axl.compiler.parser;

import axl.compiler.data.Node;
import axl.compiler.lexer.TokenStream;
import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.parser.exception.IllegalParserContentException;
import org.jetbrains.annotations.Nullable;

public interface SyntaxAnalyzer<Root extends Node<?>> {

    Root analyze();

    TokenStream getTokenStream();

    default Token eat(TokenType type) {
        if (!getTokenStream().hasNext())
            throw new IllegalParserContentException(getTokenStream().peek(), "Token not found");

        Token token = getTokenStream().next();
        if (token.getType() != type)
            throw new IllegalParserContentException(getTokenStream().peek(), "Unknown token");

        return token;
    }

    default @Nullable Token lowEat(TokenType type) {
        if (!getTokenStream().hasNext())
            return null;

        Token token = getTokenStream().peek();
        if (token.getType() != type) {
            return null;
        }

        getTokenStream().next();
        return token;
    }

    default boolean boolEat(TokenType type) {
        if (!getTokenStream().hasNext())
            return false;

        Token token = getTokenStream().peek();
        if (token.getType() != type) {
            return false;
        }

        getTokenStream().next();
        return true;
    }

    default boolean check(TokenType type) {
        if (!getTokenStream().hasNext())
            return false;

        Token token = getTokenStream().peek();
        return token.getType() == type;
    }
}
