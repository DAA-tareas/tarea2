
public class binTree {
    private nodo root;
    private int k;

    public binTree(int value){
        nodo Nodo = new nodo(value);
        Nodo.setIsRoot(true);
        this.root = Nodo;
        this.k = 0;
    }

    public binTree(nodo Nodo){
        Nodo.setIsRoot(true);
        this.root = Nodo;
        this.k = 0;
    }

    public nodo getRoot(){
        return this.root;
    }

    public void setRoot(nodo Nodo){ this.root = Nodo;}

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
        int val1 = this.root.getValue();
        nodo treeRoot = tree.getRoot();
        int val2 = treeRoot.getValue();

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
}
