package axl.compiler.parser.data.statement;

import axl.compiler.data.Node;
import axl.compiler.data.StatementNode;
import axl.compiler.lexer.data.Token;
import lombok.Data;

@Data
public abstract class ReturnSyntaxNode<Parent extends Node<?>> implements StatementNode<Parent> {

    private Token returnToken;
}
