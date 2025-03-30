package axl.compiler.parser.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public abstract class ArgumentSyntaxNode<Parent extends Node<?>> implements Node<Parent> {

    private @Nullable Token tokenName;
}
