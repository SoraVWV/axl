package axl.compiler.parser.data.declaration;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.TypeNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class ArgumentNode<Parent extends Node<?>> implements Node<Parent> {

    private final Parent parent;

    public ArgumentNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable Token name;

    private @Nullable TypeNode<ArgumentNode<Parent>> type;
}
