package axl.compiler.parser.data.type;

import axl.compiler.lexer.data.Token;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.TypeNode;
import axl.compiler.parser.data.declaration.ArgumentNode;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Data
public class LambdaTypeNode<Parent extends Node<?>> implements TypeNode<Parent> {

    private final Parent parent;

    public LambdaTypeNode(Parent parent) {
        this.parent = parent;
    }

    private List<ArgumentNode<LambdaTypeNode<Parent>>> arguments = Collections.emptyList();

    private @Nullable Token implicationToken;

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
