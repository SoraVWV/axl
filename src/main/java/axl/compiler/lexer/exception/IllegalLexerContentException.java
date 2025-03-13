package axl.compiler.lexer.exception;

import axl.compiler.lexer.data.LexerFrame;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IllegalLexerContentException extends RuntimeException {

    private final LexerFrame frame;

    public IllegalLexerContentException(LexerFrame frame, String message) {
        super(message);
        this.frame = frame;
    }
}
