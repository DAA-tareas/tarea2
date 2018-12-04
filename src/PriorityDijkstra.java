import java.util.List;

public class PriorityDijkstra {

    //private GraphOfNodes graph;
    //private Node origin;
    private Node[] prev;
    private double[] dist;

    private ClassicHeap ch;
    private FibonacciHeap fh;

    public PriorityDijkstra(int n) {
        //graph = g;
        //this.origin = origin;
        prev = new Node[n];
        dist = new double[n];
    }

    public Node[] getPrev() {
        return prev;
    }

    public double[] getDist() {
        return dist;
    }


    /**
     * Dijstra implementado con Classic Heap
     */
    public void classicHeapDijkstra(GraphOfNodes graph, Node origin) {
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
        this.ch = new ClassicHeap(pairedNodes);

        // while !Q.isEmpty()
        while(ch.getIndex() > 0){
            Pair m = ch.extractMin(); // Par(id nodo, distancia a origen)
            // Buscar el nodo
            Node mNode = graphNodes.get(m.getNodeId());
            // Para cada vecino de nodo m
            for (Edge edge : mNode.getConnections()) {
                int v = edge.getEnd().getValue();
                double nuevaDist = dist[mNode.getValue()] + edge.getWeight();
                if (nuevaDist < dist[v]){
                    dist[v] = nuevaDist;
                    prev[v] = mNode;
                    this.ch.decreaseKey(v, nuevaDist);
                }
            }
        }

    }

    /**
     * * Dijstra implementado con Fibonacci Heap
     */
    public void fibonacciDijkstra(GraphOfNodes graph, Node origin) {
    }

    public void fibonacciHeapDijkstra() {
        FibonacciHeap fb = new FibonacciHeap();
        List<Node> graphNodes = graph.getNodes();
        for(int i=0; i<graph.getNumVertex(); i++){
            // If the node is the origin
            if(graphNodes.get(i).getValue() == origin.getValue()) {
                dist[i] = 0;
            }else {
                dist[i] = Integer.MAX_VALUE;
            }
            // Predecesor de v nulo
            prev[i] = null;
            fb.insert(graphNodes.get(i), dist[i]);
        }

        while(!fb.isEmpty()){
            Node m = fb.extractMin();
            // Para cada vecino de nodo m
            for (Edge edge : m.getConnections()) {
                Node neighbour = edge.getEnd();
                int v = neighbour.getValue();
                if (dist[v] > dist[m.getValue()] + edge.getWeight()) {
                    dist[v] = dist[m.getValue()] + edge.getWeight();
                    prev[v] = m;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        int e = 1000*n;
        GraphOfNodes g = new GraphOfNodes(n, e);
        PriorityDijkstra pd = new PriorityDijkstra(n);
        double iniTime = System.currentTimeMillis();
        pd.classicHeapDijkstra(g, g.getNodes().get(0));
        double deltaTime = System.currentTimeMillis() - iniTime;
        System.out.println("Tiempo en busqueda caminos mas cortos " + deltaTime);

        /*
        System.out.println("Id Nodo Origen " + g.getNodes().get(0).getValue());
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
            //double w = prevNode.getConnectionWeight(thisNode);
            System.out.println("Previous node " + prevNode.getValue() + " to this node " + thisNode.getValue());
            System.out.println(" Weight from zero to this node: " + allDists[i]);
        }
        */


    }

}
