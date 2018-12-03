import java.util.ArrayList;
import java.util.List;

public class Node {

    private int value;
    private List<Edge> connections;

    public Node(int v) {
        this.value = v;
        this.connections = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public List<Edge> getConnections() {
        return connections;
    }

    public float getConnectionWeight(Node node) {
        for (Edge edge : this.connections) {
            if (edge.getEnd().equals(node)) {
                return edge.getWeight();
            }
        }
        return 0;
    }

    public void addEdge(Edge e) {
        this.connections.add(e);
    }

    public void printEdges() {
        System.out.println("Edges: ");
        for (Edge edge : this.connections) {
            System.out.println(" To node: " + edge.getEnd().getValue() + " peso: " + edge.getWeight());
        }
    }

}
