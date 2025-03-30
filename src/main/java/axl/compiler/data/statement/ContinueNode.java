package axl.compiler.data.statement;

import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.parser.data.statement.ContinueSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContinueNode<Parent extends Node<?>> extends ContinueSyntaxNode<Parent> implements StatementNode<Parent> {

    private final Parent parent;

    public ContinueNode(Parent parent) {
        this.parent = parent;
    }
}
