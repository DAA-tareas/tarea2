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

    public void addEdge(Edge e) {
        this.connections.add(e);
    }

}
