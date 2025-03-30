package axl.compiler.parser.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import lombok.Data;

@Data
public abstract class CallExpressionSyntaxNode<Parent extends Node<?>> implements ExpressionNode<Parent> {
}
