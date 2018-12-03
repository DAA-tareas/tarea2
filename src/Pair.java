/**
 * Created by GPG on 12/3/2018.
 */
public class Pair {
    private int nodeId;
    private float distance;

    public Pair(int nId, float distance){
        nodeId = nId;
        this.distance = distance;
    }

    public int getNodeId(){
        return this.nodeId;
    }

    public float getDistance(){
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String toString(){
        return "(" + nodeId + ", " + distance + ")";
    }
}
