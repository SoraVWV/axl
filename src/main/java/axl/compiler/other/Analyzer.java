package axl.compiler.other;

import axl.compiler.parser.data.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Analyzer {
    private final List<Visitor> visitors;
    private final Queue<NodeElement> queue = new LinkedList<>();
    private boolean running = false;

    public Analyzer(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void analyze(Node<?> first) {
        queue.add(new NodeElement(Type.ENTER, first));
        if (running)
            return;
        running = true;
        while (!queue.isEmpty()) {
            NodeElement element = queue.remove();
            switch (element.type) {
                case ENTER -> {
                    visitors.forEach(visitor -> visitor.enter(this, element.node));
                    queue.add(new NodeElement(Type.EXIT, element.node));
                }
                case EXIT -> visitors.forEach(visitor -> visitor.exit(this, element.node));
            }
        }
    }

    private static final class NodeElement {
        public final Type type;
        public final Node<?> node;

        private NodeElement(Type type, Node<?> node) {
            this.type = type;
            this.node = node;
        }
    }

    private enum Type {
        ENTER,
        EXIT
    }
}
