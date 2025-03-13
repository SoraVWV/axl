package axl.compiler.parser.data.statement;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.StatementNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class WhileNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private final Parent parent;

    public WhileNode(Parent parent) {
        this.parent = parent;
    }

    private Token whileToken;

    private ExpressionNode<WhileNode<Parent>> condition;

    private StatementNode<WhileNode<Parent>> then;

    private @Nullable Token elseToken;

    private StatementNode<WhileNode<Parent>> elseThen;
}
