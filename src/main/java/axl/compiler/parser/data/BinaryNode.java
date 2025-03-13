package axl.compiler.parser.data;

public interface BinaryNode<Parent extends Node<?>> extends Node<Parent> {

    Node<?> getLeft();

    Node<?> getRight();
}
