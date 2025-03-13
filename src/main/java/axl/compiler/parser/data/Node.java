package axl.compiler.parser.data;

import axl.compiler.parser.NodeVisitor;

public interface Node<Parent extends Node<?>> {

    default Parent getParent() {
        throw new UnsupportedOperationException();
    }

    default void visit(NodeVisitor<? extends Node<?>> visitor) {
        throw new UnsupportedOperationException("Semantic analysis is not implemented");
    }
}
