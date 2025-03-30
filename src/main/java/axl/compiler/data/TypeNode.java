package axl.compiler.data;

public interface TypeNode<Parent extends Node<?>> extends Node<Parent> {

    boolean isLocation();

    boolean isLambda();
}
