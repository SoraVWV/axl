package axl.compiler.parser.data.declaration;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.RootNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
public class ImportNode implements Node<RootNode> {

    private final RootNode parent;

    public ImportNode(RootNode parent) {
        this.parent = parent;
    }

    private Token packageToken;

    private List<Token> location;

    private @Nullable Token star;

    private @Nullable Token as;

    private @Nullable Token name;
}
