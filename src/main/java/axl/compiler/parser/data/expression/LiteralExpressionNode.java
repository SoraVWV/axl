package axl.compiler.parser.data.expression;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import lombok.Data;

@Data
public class LiteralExpressionNode<Parent extends Node<?>> implements ExpressionNode<Parent> {

    private final Parent parent;

    public LiteralExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private Token value;
}
