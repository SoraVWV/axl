package axl.compiler.lexer;

import axl.compiler.lexer.data.Lexer;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.lexer.exception.IllegalLexerContentException;
import axl.compiler.lexer.impl.TokenStreamImpl;
import axl.compiler.lexer.impl.feature.LexerIdentify;
import axl.compiler.lexer.impl.feature.LexerLiteral;
import axl.compiler.lexer.impl.feature.LexerOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TokenStreamTest {

    private TokenStream stream;

    private String loadContent(String fileName) throws IOException {
        return Files.readString(Path.of("src/test/resources/lexer/" + fileName));
    }

    @BeforeEach
    public void setUp() {
        this.stream = new TokenStreamImpl();

        TokenStreamImpl stream = (TokenStreamImpl) this.stream;

        Lexer lexer = new Lexer();
        stream.setLexer(lexer);

        stream.setIdentify(new LexerIdentify());
        stream.setOperator(new LexerOperator());
        stream.setLiteral(new LexerLiteral());
        stream.setContext(List.of(TokenType.values()));
    }

    @Test
    @DisplayName("tokenization `Hello World` file")
    public void simpleTokenization() throws IOException {
        Lexer lexer = new Lexer();
        lexer.setContent(loadContent("sample_code.axl"));
        ((TokenStreamImpl) stream).getLexer().setContent(lexer.getContent());

        final List<TokenType> expectedTokens = Arrays.asList(
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

        for (TokenType type : expectedTokens)
            assertEquals(stream.next().getType(), type);

        assertFalse(stream.hasNext());
    }

    @Test
    @DisplayName("tokenization empty file")
    public void emptyFileTokenization() throws IOException {
        Lexer lexer = new Lexer();
        lexer.setContent(loadContent("empty_file.axl"));
        ((TokenStreamImpl) stream).getLexer().setContent(lexer.getContent());

        assertFalse(stream.hasNext());
    }

    @Test
    @DisplayName("tokenization with only comments")
    public void commentsOnlyTokenization() throws IOException {
        Lexer lexer = new Lexer();
        lexer.setContent(loadContent("comments_only.axl"));
        ((TokenStreamImpl) stream).getLexer().setContent(lexer.getContent());

        assertFalse(stream.hasNext());
    }

    @Test
    @DisplayName("tokenization with non closed comment")
    public void commentNotClosedTokenization() {
        Lexer lexer = new Lexer();
        assertThrows(
                IllegalLexerContentException.class,
                () -> lexer.setContent(loadContent("comment_not_closed.axl"))
        );
    }

    @Test
    @DisplayName("tokenization complex expressions")
    public void complexExpressionTokenization() throws IOException {
        Lexer lexer = new Lexer();
        lexer.setContent(loadContent("complex_expression.axl"));
        ((TokenStreamImpl) stream).getLexer().setContent(lexer.getContent());

        final List<TokenType> expectedTokens = Arrays.asList(
                TokenType.IDENTIFY,
                TokenType.ASSIGN,
                TokenType.IDENTIFY,
                TokenType.PLUS,
                TokenType.UNARY_MINUS,
                TokenType.DEC_NUMBER,
                TokenType.MULTIPLY,
                TokenType.LEFT_PARENT,
                TokenType.DEC_NUMBER,
                TokenType.MINUS,
                TokenType.DEC_NUMBER,
                TokenType.RIGHT_PARENT
        );

        for (TokenType type : expectedTokens)
            assertEquals(stream.next().getType(), type);
            
        assertFalse(stream.hasNext());
    }

    @Test
    @DisplayName("tokenization escaped characters")
    public void escapedCharactersTokenization() throws IOException {
        Lexer lexer = new Lexer();
        lexer.setContent(loadContent("escaped_characters.axl"));
        ((TokenStreamImpl) stream).getLexer().setContent(lexer.getContent());

        final List<TokenType> expectedTokens = Arrays.asList(
                TokenType.STRING_LITERAL,
                TokenType.CHAR_LITERAL,
                TokenType.STRING_LITERAL
        );

        for (TokenType type : expectedTokens)
            assertEquals(stream.next().getType(), type);

        assertFalse(stream.hasNext());
    }

    @Test
    @DisplayName("tokenization various numbers and literals")
    public void numbersAndLiteralsTokenization() throws IOException {
        Lexer lexer = new Lexer();
        lexer.setContent(loadContent("numbers_and_literals.axl"));
        ((TokenStreamImpl) stream).getLexer().setContent(lexer.getContent());

        final List<TokenType> expectedTokens = Arrays.asList(
                TokenType.HEX_LONG_NUMBER,
                TokenType.BIN_LONG_NUMBER,
                TokenType.DEC_LONG_NUMBER,
                TokenType.HEX_NUMBER,
                TokenType.BIN_NUMBER,
                TokenType.DEC_NUMBER,
                TokenType.FLOAT_NUMBER,
                TokenType.DOUBLE_NUMBER,
                TokenType.FLOAT_EXP_NUMBER,
                TokenType.DOUBLE_EXP_NUMBER,
                TokenType.CHAR_LITERAL,
                TokenType.STRING_LITERAL
        );

        for (TokenType type : expectedTokens)
            assertEquals(stream.next().getType(), type);

        assertFalse(stream.hasNext());
    }
}
