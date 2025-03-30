package axl.compiler.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.data.RootNode;
import axl.compiler.data.TypeNode;
import axl.compiler.data.expression.DefinitionExpressionNode;
import axl.compiler.parser.data.declaration.StructSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StructNode extends StructSyntaxNode<RootNode> implements Node<RootNode> {

    private final RootNode parent;

    public StructNode(RootNode parent) {
        this.parent = parent;
    }

    private TypeNode<StructNode> parentStruct;

    private List<DefinitionExpressionNode<StructNode>> definitions = Collections.emptyList();
}
