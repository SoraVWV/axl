package axl.compiler.parser;

import axl.compiler.parser.data.Node;

public interface NodeVisitor<N> {
    
    void enter(Node node);

    N visit(Node node);

    void exit(Node node);
}
