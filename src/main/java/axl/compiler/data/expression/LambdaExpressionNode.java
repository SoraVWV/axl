package axl.compiler.data.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.data.TypeNode;
import axl.compiler.data.declaration.ArgumentNode;
import axl.compiler.parser.data.expression.LambdaExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class LambdaExpressionNode<Parent extends Node<?>> extends LambdaExpressionSyntaxNode<Parent> implements ExpressionNode<Parent> {

    private final Parent parent;

    public LambdaExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable List<ArgumentNode<LambdaExpressionNode<Parent>>> arguments = Collections.emptyList();

    private @Nullable TypeNode<LambdaExpressionNode<Parent>> returnType;

    private StatementNode<LambdaExpressionNode<Parent>> body;
}
