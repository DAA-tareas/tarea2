import java.util.ArrayList;
import java.util.List;

public class Node {

    private int value;
    private List<Edge> connections;

    // info para cuando se inserte a una cola de fibonacci
    private double priority;
    private int k;
    private Node parent;
    private List<Node> children;
    boolean isRoot, marked;

    public Node(int v) {
        this.value = v;
        this.connections = new ArrayList<>();
        this.priority = 0;
        this.k = 0;
        this.parent = null;
        this.children = new ArrayList();
        this.isRoot = false;
        this.marked = false;
    }

    public int getValue() {
        return value;
    }

    public List<Edge> getConnections() {
        return connections;
    }

    public double getConnectionWeight(Node node) {
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


    public double getPriority(){
        return this.priority;
    }

    public Node getParent(){
        return this.parent;
    }

    public List<Node> getChildren(){
        return this.children;
    }

    public void setChildren(List<Node> children){
        this.children = children;
    }

    public void setPriority(Double priority){
        this.priority = priority;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void addChild(Node child){
        this.children.add(child);
    }

    public void removeChild(Node child){
        this.children.remove(child);
    }

    public void addChildren(List<Node> children){
        this.children.addAll(children);
    }

    public void removeChildren(List<Node> children){
        this.children.remove(children);
    }

    public boolean isRoot(){
        return this.isRoot;
    }

    public void setIsRoot(boolean bool){
        this.isRoot = bool;
    }

    public boolean isMarked(){
        return this.isRoot;
    }

    public void setIsMarked(boolean bool){
        this.isRoot = bool;
    }

    public int getK(){ return this.k;}

    public void setK(int k){ this.k = k;}
}
