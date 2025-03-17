package axl.compiler.utils;

import lombok.Data;

import java.util.*;

@Data
public class TreeAnalyzer<Node> {

    private final List<Visitor<Node>> visitors;

    private final Deque<Node> enterQueue = new LinkedList<>();

    private boolean running = false;

    public TreeAnalyzer(List<Visitor<Node>> visitors) {
        this.visitors = visitors;
    }

    public void analyze(Node first) {
        enterQueue.add(first);

        if (running)
            return;
        running = true;

        List<NodeEntry> exitPool = new ArrayList<>();

        while (!enterQueue.isEmpty()) {
            Node node = enterQueue.remove();
            int startLen = enterQueue.size();

            visitors.forEach(visitor -> visitor.enter(this, node));

            int diff = enterQueue.size() - startLen;
            if (diff > 0)
                exitPool.add(new NodeEntry(node, diff));
            else visitors.forEach(visitor -> visitor.exit(this, node));

            for (int i = 0; i < exitPool.size();) {
                NodeEntry entry = exitPool.get(i);
                if (--entry.countdown < 0) {
                    exitPool.remove(entry);
                    visitors.forEach(visitor -> visitor.exit(this, entry.node));
                    continue;
                }
                i++;
            }
        }
    }

    @Data
    private final class NodeEntry {
        public final Node node;
        public int countdown;

        private NodeEntry(Node node, int countdown) {
            this.node = node;
            this.countdown = countdown;
        }
    }
}
