package axl.compiler.data.expression;

import axl.compiler.data.BinaryNode;
import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.parser.data.expression.BinaryExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BinaryExpressionNode<Parent extends Node<?>> extends BinaryExpressionSyntaxNode<Parent> implements BinaryNode<Parent>, ExpressionNode<Parent> {

    private final Parent parent;

    public BinaryExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private ExpressionNode<BinaryExpressionNode<Parent>> left;

    private ExpressionNode<BinaryExpressionNode<Parent>> right;
}
