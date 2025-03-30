package axl.compiler.parser.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
public abstract class ImportSyntaxNode<Parent extends Node<?>> implements Node<Parent> {

    private Token importToken;

    private List<Token> locationTokens;

    private @Nullable Token starToken;

    private @Nullable Token asToken;

    private @Nullable Token nameToken;
}
