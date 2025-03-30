package axl.compiler.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.data.RootNode;
import axl.compiler.parser.data.declaration.PackageSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PackageNode extends PackageSyntaxNode<RootNode> implements Node<RootNode> {

    private final RootNode parent;

    public PackageNode(RootNode parent) {
        this.parent = parent;
    }
}
