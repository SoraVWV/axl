package axl.compiler.parser.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.lexer.data.Token;
import lombok.Data;

@Data
public abstract class StructSyntaxNode<Parent extends Node<?>> implements Node<Parent> {

    private Token nameToken;
}
