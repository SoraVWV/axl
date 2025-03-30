package axl.compiler.data.statement;

import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.parser.data.statement.BodySyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BodyNode<Parent extends Node<?>> extends BodySyntaxNode<Parent> implements StatementNode<Parent> {

    private final Parent parent;

    public BodyNode(Parent parent) {
        this.parent = parent;
    }

    private List<Node<BodyNode<Parent>>> children;
}
