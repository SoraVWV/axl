package axl.compiler.utils;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public class TreeAnalyzer<Node> {

    private final List<Visitor<Node>> visitors;

    private final Queue<NodeEntry> queue = new LinkedList<>();

    private boolean running = false;

    public TreeAnalyzer(List<Visitor<Node>> visitors) {
        this.visitors = visitors;
    }

    public void analyze(Node first) {
        queue.add(new NodeEntry(Type.ENTER, first));

        if (running)
            return;
        running = true;

        while (!queue.isEmpty()) {
            NodeEntry element = queue.remove();
            switch (element.type) {
                case ENTER -> {
                    visitors.forEach(visitor -> visitor.enter(this, element.node));
                    queue.add(new NodeEntry(Type.EXIT, element.node));
                }
                case EXIT -> visitors.forEach(visitor -> visitor.exit(this, element.node));
            }
        }
    }

    @Data
    private final class NodeEntry {

        public final Type type;

        public final Node node;

        private NodeEntry(Type type, Node node) {
            this.type = type;
            this.node = node;
        }
    }

    private enum Type {
        ENTER,
        EXIT
    }
}
