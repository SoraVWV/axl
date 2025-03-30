package axl.compiler.data.statement;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.parser.data.statement.ReturnSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReturnNode<Parent extends Node<?>> extends ReturnSyntaxNode<Parent> implements StatementNode<Parent> {

    private final Parent parent;

    public ReturnNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable ExpressionNode<ReturnNode<Parent>> value;
}

