package axl.compiler.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.parser.data.expression.CallExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CallExpressionNode<Parent extends Node<?>> extends CallExpressionSyntaxNode<Parent> implements ExpressionNode<Parent> {

    private final Parent parent;

    public CallExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private ExpressionNode<CallExpressionNode<Parent>> value;

    private List<ExpressionNode<CallExpressionNode<Parent>>> arguments;
}
