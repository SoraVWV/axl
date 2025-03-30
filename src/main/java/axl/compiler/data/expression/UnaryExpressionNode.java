package axl.compiler.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.parser.data.expression.UnaryExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnaryExpressionNode<Parent extends Node<?>> extends UnaryExpressionSyntaxNode<Parent> implements ExpressionNode<Parent> {

    private final Parent parent;

    public UnaryExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private ExpressionNode<UnaryExpressionNode<Parent>> value;
}
