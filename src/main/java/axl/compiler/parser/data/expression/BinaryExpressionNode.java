package axl.compiler.parser.data.expression;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.BinaryNode;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import lombok.Data;

@Data
public class BinaryExpressionNode<Parent extends Node<?>> implements BinaryNode<Parent>, ExpressionNode<Parent> {

    private final Parent parent;

    public BinaryExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private Token operator;

    private ExpressionNode<BinaryExpressionNode<Parent>> left;

    private ExpressionNode<BinaryExpressionNode<Parent>> right;
}
