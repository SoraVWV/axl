package axl.compiler.parser.exception;

import axl.compiler.lexer.data.LexerFrame;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IllegalParserContentException extends RuntimeException {

    private final Token token;

    public IllegalParserContentException(Token token, String message) {
        super(message);
        this.token = token;
    }
}
