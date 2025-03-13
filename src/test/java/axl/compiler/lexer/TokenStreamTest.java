package axl.compiler.lexer;

import axl.compiler.lexer.data.Lexer;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.lexer.impl.TokenStreamImpl;
import axl.compiler.lexer.impl.feature.LexerIdentify;
import axl.compiler.lexer.impl.feature.LexerLiteral;
import axl.compiler.lexer.impl.feature.LexerOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TokenStreamTest {

    private static final String content = """
    fn main(args: Array<String>) -> Int {
        println("Hello, World!")
        return 0
    }
    """;

    private static final List<TokenType> types = Arrays.asList(
            TokenType.FN,
            TokenType.IDENTIFY,
            TokenType.LEFT_PARENT,
            TokenType.IDENTIFY,
            TokenType.COLON,
            TokenType.IDENTIFY,
            TokenType.LESS,
            TokenType.IDENTIFY,
            TokenType.GREATER,
            TokenType.RIGHT_PARENT,
            TokenType.IMPLICATION,
            TokenType.IDENTIFY,
            TokenType.LEFT_BRACE,
            TokenType.IDENTIFY,
            TokenType.LEFT_PARENT,
            TokenType.STRING_LITERAL,
            TokenType.RIGHT_PARENT,
            TokenType.RETURN,
            TokenType.DEC_NUMBER,
            TokenType.RIGHT_BRACE
    );

    private TokenStream stream;

    @BeforeEach
    public void setUp() {
        this.stream = new TokenStreamImpl();

        TokenStreamImpl stream = (TokenStreamImpl) this.stream;

        Lexer lexer = new Lexer();
        stream.setLexer(lexer);
        lexer.setContent(content);
        lexer.setFrame(lexer.currentFrame());

        stream.setIdentify(new LexerIdentify());
        stream.setOperator(new LexerOperator());
        stream.setLiteral(new LexerLiteral());
        stream.setContext(List.of(TokenType.values()));
    }

    @Test
    public void simpleTokenization() {
        for (TokenType type: types)
            assertEquals(stream.next().getType(), type);

        assertFalse(stream.hasNext());
    }
}
