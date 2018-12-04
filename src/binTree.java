import java.util.List;

public class binTree {
    private Node root;
    private int k;

    public binTree(Node nodo){
        nodo.setIsRoot(true);
        this.root = nodo;
        this.k = 0;
    }

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node nodo){ this.root = nodo;}

    public int getK(){
        return this.k;
    }

    public void setK(int n){
        this.k = n;
    }

    public double getNumNodos(){
        return Math.pow( 2, this.k);
    }

    public int getKHeight(){
        return this.k + 1;
    }

    public binTree joinTree(binTree tree){
        int val1 = this.root.getPriority();
        Node treeRoot = tree.getRoot();
        int val2 = treeRoot.getPriority();

        if (val1 < val2){
            treeRoot.setIsRoot(false);
            treeRoot.setParent(this.root);
            this.root.addChild(treeRoot);
            this.k++;
            return this;
        }
        this.root.setIsRoot(false);
        this.root.setParent(treeRoot);
        treeRoot.addChild(this.root);
        tree.setRoot(treeRoot);

        tree.setK(tree.getK() + 1);
        return tree;
    }

    public void printChildren(List<Node> children){
        for(Node child: children){
            System.out.print(child.getPriority() + "|");
        }
        System.out.print("|");
    }

    public void travelBT(List<Node> children){
        printChildren(children);
        for(Node child: children){
            travelBT(child.getChildren());
        }
    }

    public void printBT(binTree tree){
        root = tree.getRoot();
        System.out.println(root.getPriority());
        List<Node> children = root.getChildren();
        travelBT(children);
    }
}
