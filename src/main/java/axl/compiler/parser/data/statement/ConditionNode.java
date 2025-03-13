package axl.compiler.parser.data.statement;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.StatementNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
public class ConditionNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private final Parent parent;

    public ConditionNode(Parent parent) {
        this.parent = parent;
    }

    private Token ifToken;

    private ExpressionNode<ConditionNode<Parent>> condition;

    private StatementNode<ConditionNode<Parent>> then;

    private @Nullable Token elseToken;

    private StatementNode<ConditionNode<Parent>> elseThen;
}
