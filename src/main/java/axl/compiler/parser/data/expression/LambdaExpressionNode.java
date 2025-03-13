package axl.compiler.parser.data.expression;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.StatementNode;
import axl.compiler.parser.data.TypeNode;
import axl.compiler.parser.data.declaration.ArgumentNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Data
public class LambdaExpressionNode<Parent extends Node<?>> implements ExpressionNode<Parent> {

    private final Parent parent;

    public LambdaExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable List<ArgumentNode<LambdaExpressionNode<Parent>>> arguments = Collections.emptyList();

    private @Nullable Token implicationToken;

    private @Nullable TypeNode<LambdaExpressionNode<Parent>> returnType;

    private StatementNode<LambdaExpressionNode<Parent>> body;
}
