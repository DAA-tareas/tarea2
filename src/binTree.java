import java.util.ArrayList;
import java.util.List;

public class binTree {
    private Node root;

    public binTree(Node nodo){
        nodo.setIsRoot(true);
        this.root = nodo;
    }

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node nodo){ this.root = nodo;}

    public int getK(){
        return this.root.getK();
    }

    public int getNumNodos(){
        return (int)Math.pow( 2, this.root.getK());
    }

    public int getKHeight(){
        return this.root.getK() + 1;
    }

    public binTree joinTree(binTree tree){
        Node Root = this.getRoot();
        double val1 = Root.getPriority();
        Node treeRoot = tree.getRoot();
        double val2 = treeRoot.getPriority();

        if (val1 < val2){
            treeRoot.setIsRoot(false);
            treeRoot.setIsMarked(false);
            treeRoot.setParent(this.root);
            Root.addChild(treeRoot);
            this.setRoot(Root);
            this.root.setK(this.root.getK() + 1);
            return this;
        }
        this.root.setIsRoot(false);
        this.root.setIsMarked(false);
        this.root.setParent(treeRoot);
        treeRoot.addChild(this.root);
        treeRoot.setK(treeRoot.getK() + 1);
        tree.setRoot(treeRoot);
        return tree;
    }

    public void printChildren(List<Node> children){
        Node pastParent = null;
        List<Node> nextChildren = new ArrayList<>();

        for(Node child: children){
            if (child.getParent()!=pastParent){
                System.out.print("|");
            }
            System.out.print(child.getPriority() + "|");
            pastParent = child.getParent();

            if (child.getPriority()!=-1) {

                List<Node> grandchildren = child.getChildren();

                if (grandchildren.isEmpty()) {
                    Node node = new Node(-1);
                    node.setPriority((double)-1);
                    grandchildren.add(node);
                }

                nextChildren.addAll(grandchildren);
            }
        }
        System.out.println("");
        if (!nextChildren.isEmpty()) {
            printChildren(nextChildren);
        }
    }


    public void printBT(binTree tree){
        root = tree.getRoot();
        System.out.println(root.getPriority());
        List<Node> children = root.getChildren();
        printChildren(children);
        System.out.println("");
    }

}
