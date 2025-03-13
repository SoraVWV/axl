package axl.compiler.parser.data.statement;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.StatementNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class ContinueNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private final Parent parent;

    public ContinueNode(Parent parent) {
        this.parent = parent;
    }

    private Token continueToken;
}
