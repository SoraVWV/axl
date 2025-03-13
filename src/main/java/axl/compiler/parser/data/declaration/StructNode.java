package axl.compiler.parser.data.declaration;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.RootNode;
import axl.compiler.parser.data.TypeNode;
import axl.compiler.parser.data.expression.DefinitionExpressionNode;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class StructNode implements Node<RootNode> {

    private final RootNode parent;

    public StructNode(RootNode parent) {
        this.parent = parent;
    }

    private Token name;

    private TypeNode<StructNode> parentStruct;

    private List<DefinitionExpressionNode<StructNode>> definitions = Collections.emptyList();
}
