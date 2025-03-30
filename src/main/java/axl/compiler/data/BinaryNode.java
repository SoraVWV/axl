package axl.compiler.data;

public interface BinaryNode<Parent extends Node<?>> extends Node<Parent> {

    Node<?> getLeft();

    Node<?> getRight();
}
