/**
 * Created by GPG on 12/3/2018.
 */
public class Pair {
    private int nodeId;
    private double distance;

    public Pair(int nId, double distance){
        nodeId = nId;
        this.distance = distance;
    }

    public int getNodeId(){
        return this.nodeId;
    }

    public double getDistance(){
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String toString(){
        return "(" + nodeId + ", " + distance + ")";
    }
}
