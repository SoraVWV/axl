package axl.compiler.parser.data.type;

import axl.compiler.data.BinaryNode;
import axl.compiler.data.Node;
import axl.compiler.data.TypeNode;
import axl.compiler.lexer.data.Token;
import lombok.Data;

@Data
public abstract class LocationTypeSyntaxNode<Parent extends Node<?>> implements TypeNode<Parent>, BinaryNode<Parent> {

    private Token valueToken;
}
