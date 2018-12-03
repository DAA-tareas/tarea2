public class Edge {

    private Node start;
    private Node end;
    private float weight;

    public Edge(Node start, Node end, float w) {
        this.start = start;
        this.end = end;
        this.weight = w;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public float getWeight() {
        return weight;
    }

}
