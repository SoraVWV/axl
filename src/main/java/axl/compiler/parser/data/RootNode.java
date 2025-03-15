package axl.compiler.parser.data;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.declaration.ImportNode;
import axl.compiler.parser.data.declaration.PackageNode;
import axl.compiler.parser.data.declaration.StructNode;
import axl.compiler.parser.data.expression.DefinitionExpressionNode;
import lombok.Data;

import java.util.List;

@Data
public class RootNode implements Node<Node<?>> {

    private PackageNode packageNode;

    private List<ImportNode> importNodes;

    private List<DefinitionExpressionNode<RootNode>> definitions;

    private List<StructNode> structs;
}
