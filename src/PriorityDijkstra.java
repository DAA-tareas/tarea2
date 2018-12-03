import java.util.List;

public class PriorityDijkstra {

    private GraphOfNodes graph;
    private Node origin;

    private ClassicHeap ch;
    private FibonacciHeap fh;

    public PriorityDijkstra(GraphOfNodes g, Node origin) {
        graph = g;
        this.origin = origin;
    }

    public void clasicHeapDijkstra() {
        Pair[] pairedNodes = new Pair[graph.getNumVertex()];
        List<Node> graphNodes = graph.getNodes();
        for(int i=0; i<graph.getNumVertex(); i++){
            // If the node is the origin
            if(graphNodes.get(i).getValue() == origin.getValue())
                pairedNodes[i] = new Pair();
        }
    }

}
