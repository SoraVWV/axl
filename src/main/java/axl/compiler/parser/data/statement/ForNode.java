package axl.compiler.parser.data.statement;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.StatementNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class ForNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private final Parent parent;

    public ForNode(Parent parent) {
        this.parent = parent;
    }

    private Token forToken;

    private Token name;

    private Token in;

    private ExpressionNode<ForNode<Parent>> iterator;

    private StatementNode<ForNode<Parent>> then;

    private @Nullable Token elseToken;

    private StatementNode<ForNode<Parent>> elseThen;
}
