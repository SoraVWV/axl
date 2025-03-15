package axl.compiler.utils;

public interface Visitor<Node> {

    void enter(TreeAnalyzer<Node> treeAnalyzer, Node node);

    void exit(TreeAnalyzer<Node> treeAnalyzer, Node node);
}
