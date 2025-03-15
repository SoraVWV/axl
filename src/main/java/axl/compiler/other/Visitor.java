package axl.compiler.other;

import axl.compiler.parser.data.Node;

public interface Visitor {
    void enter(Analyzer analyzer, Node<?> node);
    void exit(Analyzer analyzer, Node<?> node);
}
