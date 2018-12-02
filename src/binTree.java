
public class binTree {
    private nodo root;
    private int k;

    public binTree(int value){
        this.root = new nodo(value);
        this.k = 0;
    }

    public nodo getRoot(){
        return this.root;
    }

    public int getK(){
        return this.k;
    }

    public double getNumNodos(){
        return Math.pow( 2, this.k);
    }

    public int getKHeight(){
        return this.k + 1;
    }

    public binTree joinTree(binTree tree){
        int val1 = this.root.getValue();
        int val2 = tree.root.getValue();

        if (val1 < val2){
            this.root.addChild(tree.root);
            this.k++;
            return this;
        }
        tree.root.addChild(this.root);
        tree.k++;
        return tree;
    }
}
