package axl.compiler.data;

public interface Node<Parent extends Node<?>> {

    default Parent getParent() {
        throw new UnsupportedOperationException();
    }
}
