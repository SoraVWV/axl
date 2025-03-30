package axl.compiler.data.statement;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.parser.data.statement.WhileSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class WhileNode<Parent extends Node<?>> extends WhileSyntaxNode<Parent> implements StatementNode<Parent> {

    private final Parent parent;

    public WhileNode(Parent parent) {
        this.parent = parent;
    }

    private ExpressionNode<WhileNode<Parent>> condition;

    private StatementNode<WhileNode<Parent>> then;

    private @Nullable StatementNode<WhileNode<Parent>> elseThen;
}
