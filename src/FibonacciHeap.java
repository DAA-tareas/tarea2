import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FibonacciHeap {
    private List<binTree> lista;
    private int min, n;

    public FibonacciHeap(){
        this.lista = new ArrayList<>();
        this.n = 0;
    }

    public void insert(binTree b0){
        double value = b0.getRoot().getPriority();

        if (lista.isEmpty()){
            this.lista.add(b0);
            this.min = 0;
        }
        else{
            this.lista.add(b0);
            if (value < this.lista.get(this.min).getRoot().getPriority()){
                this.min = this.lista.size() - 1;
            }
        }
        this.n++;

    }
    public void insert(Node nodo, Double priority){
        nodo.setPriority(priority);
        binTree b0 = new binTree(nodo);
        this.insert(b0);
    }

    public void insertAll(List<Node> list, double[] priorities){
        for (Node nodo: list){
            for(double priority: priorities){
                this.insert(nodo, priority);
            }
        }
    }

    public boolean isEmpty(){
        return this.n == 0;
    }

    public double findMin(){
        return this.lista.get(this.min).getRoot().getPriority();
    }

    public List<binTree> getLista(){
        return this.lista;
    }

    public void merge(FibonacciHeap fibHeap){
        this.lista.addAll(fibHeap.getLista());
    }

    public Node extractMin(){
        binTree arbolMin = this.lista.get(this.min);
        Node ret = arbolMin.getRoot();
        this.lista.remove(min);
        n--;

        int j =0;
        for (Node hijo: arbolMin.getRoot().getChildren()){
            this.insert(hijo, hijo.getPriority());
        }

        int sizeA = (int) Math.ceil( Math.log(n) / Math.log(2));
        Integer[] A = new Integer[sizeA + 1];


        for (int i = 0; i<this.lista.size(); i++){
            binTree tree = this.lista.get(i);
            int k = tree.getK();

            if (A[k]==null){
                A[k] = this.lista.indexOf(tree);
            }
            else{
                binTree kTree = this.lista.get(A[k]);
                A[k] = null;

                this.lista.add(tree.joinTree(kTree));
            }
        }

        List<binTree> listaA = new ArrayList();

        for (int i = 0; i<A.length; i++){
            if (A[i]!=null){
                listaA.add(this.lista.get(A[i]));
            }
        }
        this.lista = listaA;

        this.calcMin();

        return ret;
    }

    public void calcMin(){
        Integer min = null;

        for (binTree tree: this.lista){
            if (min == null || tree.getRoot().getPriority()<this.lista.get(min).getRoot().getPriority()){
                min = this.lista.indexOf(tree);
            }
        }

        this.setMin(min);
    }

    public void setMin(int min){
        this.min = min;
    }


    public void printFH(){
        for (binTree tree: this.lista){
            System.out.println("k = " + tree.getK());
            tree.printBT(tree);
            System.out.println("------------");
        }
        System.out.println("------------");
    }

    public void cut(Node nodo, Node parent){
        parent.removeChild(nodo);
        parent.setK(parent.getK() - 1);
        this.insert(nodo, nodo.getPriority());
        nodo.setParent(null);
        nodo.setIsMarked(false);
    }

    public void cutAux(Node parent){
        Node grandParent = parent.getParent();

        if (grandParent!=null){
            if (parent.isMarked() == false){
                parent.setIsMarked(true);
            }
            else{
                this.cut(parent, grandParent);
                this.cutAux(grandParent);
            }
        }
    }

    public void decreaseKey(Node nodo, double newPri){
        nodo.setPriority(newPri);
        Node parent = nodo.getParent();
        if(parent!=null && nodo.getPriority()<parent.getPriority()){
            this.cut(nodo, parent);
            cutAux(parent);
        }
    }

    public static void main(String[] args) {
        FibonacciHeap H = new FibonacciHeap();
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            list.add(new Node(i));
        }
        double i = 0;
        for (Node nodo: list){
            H.insert(nodo, i++);
        }

        H.extractMin();
        H.printFH();
        H.extractMin();
        H.printFH();
    }


}
