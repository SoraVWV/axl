package axl.compiler.utils;

import axl.compiler.parser.data.Node;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
public class TreeAnalyzerTest {
    private static final Node node = new ListNode(List.of(
            new TextNode("First"),
            new TextNode("Second"),
            new TextNode("Third")
    ));

    private static final List<String> textAndState = List.of(
            "(Enter) List",
            "(Enter) First",
            "(Exit) First",
            "(Enter) Second",
            "(Exit) Second",
            "(Enter) Third",
            "(Exit) Third",
            "(Exit) List"
    );

    @Test
    public void simpleAnalyze() {
        List<String> result = new ArrayList<>();
        new TreeAnalyzer(List.of(new TestVisitor(result))).analyze(node);
        assertEquals(textAndState, result);
    }

    private static class TestVisitor implements Visitor<Node> {
        private final List<String> output;

        private TestVisitor(List<String> output) {
            this.output = output;
        }

        @Override
        public void enter(TreeAnalyzer<Node> treeAnalyzer, Node node) {
            output.add("(Enter) " + node.toString());
            if (node instanceof ListNode list) {
                for (Node child : list.child) {
                    treeAnalyzer.analyze(child);
                }
            }
        }

        @Override
        public void exit(TreeAnalyzer<Node> treeAnalyzer, Node node) {
            output.add("(Exit) " + node.toString());
        }
    }

    @Getter
    private static class ListNode implements Node {
        private final List<Node> child;

        private ListNode(List<Node> child) {
            this.child = child;
        }

        @Override
        public String toString() {
            return "List";
        }
    }

    @Getter
    private static class TextNode implements Node {

        private final String text;

        private TextNode(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }
}
