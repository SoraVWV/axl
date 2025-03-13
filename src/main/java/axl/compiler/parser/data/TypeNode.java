package axl.compiler.parser.data;

public interface TypeNode<Parent extends Node<?>> extends Node<Parent> {

    boolean isLocation();

    boolean isLambda();
}
