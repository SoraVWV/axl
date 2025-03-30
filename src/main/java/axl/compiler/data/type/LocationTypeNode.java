package axl.compiler.data.type;

import axl.compiler.data.BinaryNode;
import axl.compiler.data.Node;
import axl.compiler.data.TypeNode;
import axl.compiler.parser.data.type.LocationTypeSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class LocationTypeNode<Parent extends Node<?>> extends LocationTypeSyntaxNode<Parent> implements TypeNode<Parent>, BinaryNode<Parent> {

    private final Parent parent;

    public LocationTypeNode(Parent parent) {
        this.parent = parent;
    }

    private @Nullable LocationTypeNode<LocationTypeNode<Parent>> left;

    private @Nullable LocationTypeNode<LocationTypeNode<Parent>> right;

    private List<LocationTypeNode<LocationTypeNode<Parent>>> generics = Collections.emptyList();

    @Override
    public boolean isLocation() {
        return true;
    }

    @Override
    public boolean isLambda() {
        return false;
    }
}
