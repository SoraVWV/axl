package axl.compiler.parser.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.lexer.data.Token;
import lombok.Data;

import java.util.List;

@Data
public abstract class PackageSyntaxNode<Parent extends Node<?>> implements Node<Parent> {

    private Token packageToken;

    private List<Token> locationTokens;
}
