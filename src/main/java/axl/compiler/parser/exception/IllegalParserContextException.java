package axl.compiler.parser.exception;

import axl.compiler.lexer.data.LexerFrame;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IllegalParserContextException extends RuntimeException {

    private final Token token;

    public IllegalParserContextException(Token token, String message) {
        super(message);
        this.token = token;
    }
}
