package axl.compiler.parser.data.expression;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.ExpressionNode;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.TypeNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class DefinitionExpressionNode<Parent extends Node<?>> implements ExpressionNode<Parent> {

    private final Parent parent;

    public DefinitionExpressionNode(Parent parent) {
        this.parent = parent;
    }

    private Token announcer;

    private Token name;

    private @Nullable Token operator;

    private @Nullable TypeNode<DefinitionExpressionNode<Parent>> type;

    private @Nullable ExpressionNode<DefinitionExpressionNode<Parent>> value;
}
