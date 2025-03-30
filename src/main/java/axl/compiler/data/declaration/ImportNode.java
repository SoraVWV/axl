package axl.compiler.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.data.RootNode;
import axl.compiler.parser.data.declaration.ImportSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImportNode extends ImportSyntaxNode<RootNode> implements Node<RootNode> {

    private final RootNode parent;

    public ImportNode(RootNode parent) {
        this.parent = parent;
    }
}
