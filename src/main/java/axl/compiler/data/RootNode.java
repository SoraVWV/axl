package axl.compiler.data;

import axl.compiler.parser.data.RootSyntaxNode;
import axl.compiler.parser.data.declaration.ImportSyntaxNode;
import axl.compiler.parser.data.declaration.PackageSyntaxNode;
import axl.compiler.parser.data.declaration.StructSyntaxNode;
import axl.compiler.parser.data.expression.DefinitionExpressionSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RootNode extends RootSyntaxNode implements Node<Node<?>> {

    private PackageSyntaxNode<RootNode> location;

    private List<ImportSyntaxNode<RootNode>> imports;

    private List<DefinitionExpressionSyntaxNode<RootNode>> definitions;

    private List<StructSyntaxNode<RootNode>> structs;
}
