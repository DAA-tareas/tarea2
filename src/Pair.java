/**
 * Created by GPG on 12/3/2018.
 */
public class Pair {
    private int nodeId;
    private int distance;

    public Pair(int nId, int distance){
        nodeId = nId;
        this.distance = distance;
    }

    public int getNodeId(){
        return this.nodeId;
    }

    public int getDistance(){
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
