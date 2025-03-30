package axl.compiler.parser.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public abstract class DefinitionExpressionSyntaxNode<Parent extends Node<?>> implements ExpressionNode<Parent> {

    private Token announcerToken;

    private Token nameToken;

    private @Nullable Token operatorToken;
}
