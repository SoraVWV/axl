package axl.compiler.lexer.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LexerFrame {

    private int line;

    private int column;

    private int offset;
}
