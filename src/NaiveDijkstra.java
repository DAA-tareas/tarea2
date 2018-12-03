import java.util.List;

public class NaiveDijkstra {

    private double[] dist;
    private boolean[] mark;
    private Node[] prev;

    public NaiveDijkstra(int n) {
        dist = new double[n];
        for (int i = 1; i < n; i++) {
            dist[i] = Float.MAX_VALUE;
        }
        // default initialization is false
        mark = new boolean[n];
        prev = new Node[n];
    }

    public double[] getDist() {
        return dist;
    }

    public boolean[] getMark() {
        return mark;
    }

    public Node[] getPrev() {
        return prev;
    }

    public void run(GraphOfNodes g) {
        int V = g.getNumVertex();
        for (int i = 0; i < V; i++) {
            double minDist = Double.MAX_VALUE;
            // Node value
            int minNode = -1;

            // Find minimum
            for (int j = 0; j < V; j++) {
                if (!mark[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minNode = j;
                }
            }
            int u = minNode;
            mark[u] = true;
            Node nu = g.getNodes().get(u);
            for (Edge edge : nu.getConnections()) {
                int v = edge.getEnd().getValue();
                if (dist[v] > dist[u] + edge.getWeight()) {
                    dist[v] = dist[u] + edge.getWeight();
                    prev[v] = nu;
                }
            }
        }
        prev[0] = g.getNodes().get(0);
    }

    public static void main(String[] args) {
        int n = 6;   //1000;
        int e = 10;   //100*n;
        GraphOfNodes g = new GraphOfNodes(n, e);
        NaiveDijkstra nd = new NaiveDijkstra(n);

        nd.run(g);


    }

}
