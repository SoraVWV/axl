package axl.compiler.parser.data.type;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.BinaryNode;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.TypeNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Data
public class LocationTypeNode<Parent extends Node<?>> implements TypeNode<Parent>, BinaryNode<Parent> {

    private final Parent parent;

    public LocationTypeNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable LocationTypeNode<LocationTypeNode<Parent>> left;

    private @Nullable LocationTypeNode<LocationTypeNode<Parent>> right;

    private List<LocationTypeNode<LocationTypeNode<Parent>>> generics = Collections.emptyList();

    private Token value;

    @Override
    public boolean isLocation() {
        return true;
    }

    @Override
    public boolean isLambda() {
        return false;
    }
}
