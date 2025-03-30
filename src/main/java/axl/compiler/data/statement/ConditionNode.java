package axl.compiler.data.statement;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.parser.data.statement.ConditionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConditionNode<Parent extends Node<?>> extends ConditionSyntaxNode<Parent> implements StatementNode<Parent> {

    private final Parent parent;

    public ConditionNode(Parent parent) {
        this.parent = parent;
    }

    private ExpressionNode<ConditionNode<Parent>> condition;

    private StatementNode<ConditionNode<Parent>> then;

    private @Nullable StatementNode<ConditionNode<Parent>> elseThen;
}
