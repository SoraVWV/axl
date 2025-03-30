package axl.compiler.parser.data.type;

import axl.compiler.data.Node;
import axl.compiler.data.TypeNode;
import axl.compiler.lexer.data.Token;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public abstract class LambdaTypeSyntaxNode<Parent extends Node<?>> implements TypeNode<Parent> {

    private @Nullable Token implicationToken;
}
