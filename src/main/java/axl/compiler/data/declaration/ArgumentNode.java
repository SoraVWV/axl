package axl.compiler.data.declaration;

import axl.compiler.data.Node;
import axl.compiler.data.TypeNode;
import axl.compiler.parser.data.declaration.ArgumentSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArgumentNode<Parent extends Node<?>> extends ArgumentSyntaxNode<Parent> implements Node<Parent> {

    private final Parent parent;

    public ArgumentNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable TypeNode<ArgumentNode<Parent>> type;
}
