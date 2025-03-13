package axl.compiler.parser;

import axl.compiler.parser.data.Node;

public interface NodeVisitor<AcceptNode extends Node<?>> {
    
    default void enter(AcceptNode node) {
    }

    void visit(AcceptNode node);

    default void exit(AcceptNode node) {
    }
}
