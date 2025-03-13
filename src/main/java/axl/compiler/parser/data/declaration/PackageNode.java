package axl.compiler.parser.data.declaration;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.RootNode;
import lombok.Data;

import java.util.List;

@Data
public class PackageNode implements Node<RootNode> {

    private final RootNode parent;

    public PackageNode(RootNode parent) {
        this.parent = parent;
    }

    private Token packageToken;

    private List<Token> location;
}
