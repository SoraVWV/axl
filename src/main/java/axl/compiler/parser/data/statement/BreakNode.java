package axl.compiler.parser.data.statement;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.StatementNode;
import lombok.Data;

@Data
public class BreakNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private final Parent parent;

    public BreakNode(Parent parent) {
        this.parent = parent;
    }

    private Token breakToken;
}
