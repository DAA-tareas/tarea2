/**
 * Created by GPG on 11/30/2018.
 */
public class NaiveDijkstra {

    private float[] dist;
    private boolean[] marcado;
    private Node[] prev;

    public NaiveDijkstra(int n) {
        dist = new float[n];
        for (int i = 1; i < n; i++) {
            dist[i] = Float.MAX_VALUE;
        }
        // default initialization is false
        marcado = new boolean[n];
        prev = new Node[n];
    }

    public float[] getDist() {
        return dist;
    }

    public boolean[] getMarcado() {
        return marcado;
    }

    public Node[] getPrev() {
        return prev;
    }

    public void run(GraphOfNodes g) {
        int V = g.getNumVertex();
        for (int i = 1; i < V; i++) {
            float minDist = Float.MAX_VALUE;
            // Node value
            int minNode = -1;

            // Find minimum
            for (int j = 1; j < V; j++) {
                if (!marcado[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minNode = j;
                }
            }

            int u = minNode;
        }
    }

}
