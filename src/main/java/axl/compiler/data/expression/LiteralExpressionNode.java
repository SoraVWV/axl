package axl.compiler.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.parser.data.expression.LiteralExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LiteralExpressionNode<Parent extends Node<?>> extends LiteralExpressionSyntaxNode<Parent> implements ExpressionNode<Parent> {

    private final Parent parent;

    public LiteralExpressionNode(Parent parent) {
        this.parent = parent;
    }
}
