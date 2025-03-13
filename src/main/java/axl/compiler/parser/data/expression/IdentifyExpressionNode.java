package axl.compiler.parser.data.expression;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import lombok.Data;

@Data
public class IdentifyExpressionNode<Parent extends Node<?>> implements ExpressionNode<Parent> {

    private final Parent parent;

    public IdentifyExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private Token operator;

    private Token value;
}
