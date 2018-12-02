import java.util.Random;

public class Grafo {

    private int[] vertex;
    private float[][] edges;

    public Grafo(int n, int e) {
        // Initialize arrays
        vertex = new int[n];
        edges = new float[e][3];

        // Save al vertex
        for (int i = 0; i < n; i++) {
            vertex[i] = i;
        }

        // Random float between 0 inclusive and 1 exclusive
        Random rand = new Random();
        // Random int between 0 and n
        Random rand2 = new Random();

        // Generate edges to ensure connectivity
        for (int i = 0; i < n; i++) {
            edges[i][0] = i;
            edges[i][1] = (i + 1) % n;
            float r = rand.nextFloat();
            // Change random number 0 to 1 -> now 0 exclusive and 1 inclusive
            edges[i][2] = (r == 0) ? 1 : r;
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
                // Don't include edge from and to the same vertex
                if (v1 != v2) {
                    boolean valid = true;
                    // Check presence of possible new edge in existing ones
                    for (int j = 0; j < count; j++) {
                        if ((v1 == edges[j][0] && v2 == edges[j][1]) || (v1 == edges[j][1] && v2 == edges[j][0])) {
                            valid = false;
                            break;
                        }
                    }
                    // Aggregate new edge
                    if (valid) {
                        edges[i][0] = v1;
                        edges[i][1] = v2;
                        float r = rand.nextFloat();
                        // Change random number 0 to 1 -> now 0 exclusive and 1 inclusive
                        edges[i][2] = (r == 0) ? 1 : r;
                        notReady = false;
                    }
                }
            }
        }

    }

    public int[] getVertex() {
        return vertex;
    }

    public float[][] getEdges() {
        return edges;
    }

}
