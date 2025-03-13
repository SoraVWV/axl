package axl.compiler.parser.data.expression;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import lombok.Data;

import java.util.List;

@Data
public class CallExpressionNode<Parent extends Node<?>> implements ExpressionNode<Parent> {

    private final Parent parent;

    public CallExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private ExpressionNode<CallExpressionNode<Parent>> value;

    private List<ExpressionNode<CallExpressionNode<Parent>>> arguments;
}
