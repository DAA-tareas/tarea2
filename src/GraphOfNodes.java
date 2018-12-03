import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphOfNodes {

    private int numVertex;
    private List<Node> nodes;

    public GraphOfNodes(int n, int e) {
        numVertex = n;
        nodes = new ArrayList<>();

        // Random float between 0 inclusive and 1 exclusive
        Random rand = new Random();
        // Random int between 0 and n
        Random rand2 = new Random();

        // Generate nodes
        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            nodes.add(node);
        }

        // Generate edges to ensure connectivity
        for (int i = 0; i < n - 1; i++) {
            float r = rand.nextFloat();
            // Change random number 0 to 1 -> now 0 exclusive and 1 inclusive
            float new_r = (r == 0) ? 1 : r;
            Node n1 = nodes.get(i);
            Node n2 = nodes.get(i + 1);
            Edge edge = new Edge(n1, n2, new_r);
            Edge edge2 = new Edge(n2, n1, new_r);
            n1.addEdge(edge);
            n2.addEdge(edge2);
        }

        // Generate rest e - n + 1 edges
        int rest = e - n + 1;
        // Number of places edges
        int count = n - 1;
        for (int i = rest; i < e; i++) {
            // Possible new edge
            boolean notReady = true;
            while (notReady) {
                int v1 = rand2.nextInt(n);
                int v2 = rand2.nextInt(n);
                Node n1 = nodes.get(v1);
                Node n2 = nodes.get(v2);
                // Don't include edge from and to the same vertex
                if (!n1.equals(n2)) {
                    boolean valid = true;
                    // Check presence of possible new edge in existing ones
                    for (Edge edge : n1.getConnections()) {
                        if (edge.getStart().equals(n2) || edge.getEnd().equals(n2)) {
                            valid = false;
                            break;
                        }
                    }

                    // Aggregate new edge
                    if (valid) {
                        float r = rand.nextFloat();
                        // Change random number 0 to 1 -> now 0 exclusive and 1 inclusive
                        float new_r = (r == 0) ? 1 : r;
                        Edge edge = new Edge(n1, n2, new_r);
                        Edge edge2 = new Edge(n2, n1, new_r);
                        n1.addEdge(edge);
                        n2.addEdge(edge2);
                        count++;
                        notReady = false;
                    }
                }
            }
        }
    }

    public int getNumVertex() {
        return numVertex;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public static void main(String[] args){
        GraphOfNodes g = new GraphOfNodes(6, 10);
        List<Node> nodeList = g.getNodes();
        for (Node node : nodeList) {
            System.out.println("Node: " + node.getValue());
            System.out.println("Edges: ");
            for (Edge edge : node.getConnections()) {
                System.out.println(" To node: " + edge.getEnd().getValue() + " peso: " + edge.getWeight());
            }
            System.out.println("-----------");
        }
    }

}
