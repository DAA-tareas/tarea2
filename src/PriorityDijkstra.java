import java.util.ArrayList;
import java.util.List;

public class PriorityDijkstra {

    private GraphOfNodes graph;
    private Node origin;
    private Node[] prev;
    private double[] dist;

    private ClassicHeapPaired ch;
    private FibonacciHeap fh;

    public PriorityDijkstra(GraphOfNodes g, Node origin) {
        graph = g;
        this.origin = origin;
        prev = new Node[g.getNumVertex()];
        dist = new double[g.getNumVertex()];
    }

    public Node[] getPrev() {
        return prev;
    }

    public double[] getDist() {
        return dist;
    }

    public void clasicHeapDijkstra() {
        Pair[] pairedNodes = new Pair[graph.getNumVertex()];
        List<Node> graphNodes = graph.getNodes();
        for(int i=0; i<graph.getNumVertex(); i++){
            // If the node is the origin
            if(graphNodes.get(i).getValue() == origin.getValue()) {
                dist[i] = 0;
            }else {
                dist[i] = Integer.MAX_VALUE;
            }
            pairedNodes[i] = new Pair(graphNodes.get(i).getValue(), dist[i]);
            // Predecesor de v nulo
            prev[i] = null;
        }
        // Clasic heap
        this.ch = new ClassicHeapPaired(pairedNodes);

        while(ch.getIndex() >= 0){
            Pair m = ch.extractMin(); // Par(id nodo, distancia a origen)
            // Buscar el nodo
            Node mNode = graphNodes.get(m.getNodeId());
            // Para cada vecino de nodo m
            for (Edge edge : mNode.getConnections()) {
                int v = edge.getEnd().getValue();
                if (dist[v] > dist[mNode.getValue()] + edge.getWeight()) {
                    dist[v] = dist[mNode.getValue()] + edge.getWeight();
                    prev[v] = mNode;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int e = 10;
        GraphOfNodes g = new GraphOfNodes(n, e);
        PriorityDijkstra pd = new PriorityDijkstra(g, g.getNodes().get(0));
        pd.clasicHeapDijkstra();
        // /*
        // Print Graph
        List<Node> nodeList = g.getNodes();
        for (Node node : nodeList) {
            System.out.println("Node: " + node.getValue());
            System.out.println("Edges: ");
            for (Edge edge : node.getConnections()) {
                System.out.println(" To node: " + edge.getEnd().getValue() + " peso: " + edge.getWeight());
            }
            System.out.println("-----------");
        }
        System.out.println("\nPrinting optimum connections\n");
        // Print optimum connections
        Node[] prev = pd.getPrev();
        for (int i = 1; i < n; i++) {
            Node prevNode = prev[i];
            Node thisNode = g.getNodes().get(i);
            double[] allDists = pd.getDist();
            double w = prevNode.getConnectionWeight(thisNode);
            System.out.println("Previous node " + prevNode.getValue() + " to this node " + thisNode.getValue());
            System.out.println(" Weight from zero to this node: " + allDists[i]);
        }
    }

}
