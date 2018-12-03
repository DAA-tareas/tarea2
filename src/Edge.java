public class Edge {

    private Node start;
    private Node end;
    private double weight;

    public Edge(Node start, Node end, double w) {
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

    public double getWeight() {
        return weight;
    }

}
