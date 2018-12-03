import java.util.List;
import java.util.ArrayList;

public class nodo{
    private int value;
    private nodo parent;
    private List<nodo> children;
    boolean isRoot;

    public nodo(int value){
        this.value = value;
        this.parent = null;
        this.children = new ArrayList();
        this.isRoot = false;
    }

    public int getValue(){
        return this.value;
    }

    public nodo getParent(){
        return this.parent;
    }

    public List<nodo> getChildren(){
        return this.children;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setParent(nodo parent){
        this.parent = parent;
    }

    public void addChild(nodo child){
        this.children.add(child);
    }

    public void removeChild(nodo child){
        this.children.remove(child);
    }

    public void addChildren(List<nodo> children){
        this.children.addAll(children);
    }

    public void removeChildren(List<nodo> children){
        this.children.remove(children);
    }

    public boolean isRoot(){
        return this.isRoot;
    }

    public void setIsRoot(boolean bool){
        this.isRoot = bool;
    }
}