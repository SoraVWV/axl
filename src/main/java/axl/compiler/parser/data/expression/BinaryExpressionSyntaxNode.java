package axl.compiler.parser.data.expression;

import axl.compiler.data.BinaryNode;
import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.lexer.data.Token;
import lombok.Data;

@Data
public abstract class BinaryExpressionSyntaxNode<Parent extends Node<?>> implements BinaryNode<Parent>, ExpressionNode<Parent> {

    private Token operatorToken;
}
