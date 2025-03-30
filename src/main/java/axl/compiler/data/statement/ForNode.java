package axl.compiler.data.statement;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.parser.data.statement.ForSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForNode<Parent extends Node<?>> extends ForSyntaxNode<Parent> implements StatementNode<Parent> {

    private final Parent parent;

    public ForNode(Parent parent) {
        this.parent = parent;
    }

    private ExpressionNode<ForNode<Parent>> iterator;

    private StatementNode<ForNode<Parent>> then;

    private @Nullable StatementNode<ForNode<Parent>> elseThen;
}
