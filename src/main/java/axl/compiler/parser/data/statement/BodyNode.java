package axl.compiler.parser.data.statement;

import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.StatementNode;
import lombok.Data;

import java.util.List;

@Data
public class BodyNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private final Parent parent;

    public BodyNode(Parent parent) {
        this.parent = parent;
    }

    private List<Node<BodyNode<Parent>>> children;
}
