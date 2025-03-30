package axl.compiler.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.data.TypeNode;
import axl.compiler.parser.data.expression.DefinitionExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefinitionExpressionNode<Parent extends Node<?>> extends DefinitionExpressionSyntaxNode<Parent> implements ExpressionNode<Parent> {

    private final Parent parent;

    public DefinitionExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable TypeNode<DefinitionExpressionNode<Parent>> type;

    private @Nullable ExpressionNode<DefinitionExpressionNode<Parent>> value;
}
