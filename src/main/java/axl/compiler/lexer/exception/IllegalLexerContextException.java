package axl.compiler.lexer.exception;

import axl.compiler.lexer.data.LexerFrame;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IllegalLexerContextException extends RuntimeException {

    private final LexerFrame frame;

    public IllegalLexerContextException(LexerFrame frame, String message) {
        super(message);
        this.frame = frame;
    }
}
