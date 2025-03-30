package axl.compiler.data.statement;

import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.parser.data.statement.BreakSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BreakNode<Parent extends Node<?>> extends BreakSyntaxNode<Parent> implements StatementNode<Parent> {

    private final Parent parent;

    public BreakNode(Parent parent) {
        this.parent = parent;
    }
}
