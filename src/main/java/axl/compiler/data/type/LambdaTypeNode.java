package axl.compiler.data.type;

import axl.compiler.data.Node;
import axl.compiler.data.TypeNode;
import axl.compiler.parser.data.declaration.ArgumentSyntaxNode;
import axl.compiler.parser.data.type.LambdaTypeSyntaxNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class LambdaTypeNode<Parent extends Node<?>> extends LambdaTypeSyntaxNode<Parent> implements TypeNode<Parent> {

    private final Parent parent;

    public LambdaTypeNode(Parent parent) {
        this.parent = parent;
    }

    private List<ArgumentSyntaxNode<LambdaTypeNode<Parent>>> arguments = Collections.emptyList();

    private @Nullable TypeNode<LambdaTypeNode<Parent>> returnType;

    @Override
    public boolean isLocation() {
        return false;
    }

    @Override
    public boolean isLambda() {
        return true;
    }
}
