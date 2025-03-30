package axl.compiler.parser.data.statement;

import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public abstract class BodySyntaxNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private @Nullable Token startToken;

    private @Nullable Token endToken;
}
