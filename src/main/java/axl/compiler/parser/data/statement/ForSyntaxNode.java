package axl.compiler.parser.data.statement;

import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public abstract class ForSyntaxNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private Token forToken;

    private Token nameToken;

    private Token inToken;

    private @Nullable Token elseToken;
}
