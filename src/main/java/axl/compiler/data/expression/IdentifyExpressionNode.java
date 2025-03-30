package axl.compiler.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.parser.data.expression.IdentifyExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IdentifyExpressionNode<Parent extends Node<?>> extends IdentifyExpressionSyntaxNode<Parent> implements ExpressionNode<Parent> {

    private final Parent parent;

    public IdentifyExpressionNode(Parent parent) {
        this.parent = parent;
    }
}
