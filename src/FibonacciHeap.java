import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap {
    private List<binTree> lista;
    private int min, n;

    public FibonacciHeap(){
        this.lista = new ArrayList<>();
        this.n = 0;
    }

    public void insert(binTree b0){
        int value = b0.getRoot().getPriority();

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
    public void insert(Node nodo, int priority){
        nodo.setPriority(priority);
        binTree b0 = new binTree(nodo);
        this.insert(b0);
    }

    public void insertAll(List<Node> list, int[] priorities){
        for (Node nodo: list){
            for(int priority: priorities){
                this.insert(nodo, priority);
            }
        }
    }

    public boolean isEmpty(){
        return this.n == 0;
    }

    public int findMin(){
        return this.lista.get(this.min).getRoot().getPriority();
    }

    public List<binTree> getLista(){
        return this.lista;
    }

    public void merge(FibonacciHeap fibHeap){
        this.lista.addAll(fibHeap.getLista());
    }

    public void extractMin(){
        binTree arbolMin = this.lista.get(this.min);
        this.lista.remove(min);
        n--;

        for (Node hijo: arbolMin.getRoot().getChildren()){
            this.insert(hijo, hijo.getPriority());
        }

        int sizeA = (int) Math.ceil( (long) Math.log(n) / (long) Math.log(2));
        Integer[] A = new Integer[sizeA];

        for (binTree tree: this.lista){
            int k = tree.getK();

            if (A[k]==null){
                A[k] = this.lista.indexOf(tree);
            }
            else{
                binTree kTree = this.lista.get(A[k]);
                A[k] = null;

                this.lista.remove(tree);
                this.lista.remove(kTree);

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
    }

    public void calcMin(){
        Integer min = null;

        for (binTree tree: this.lista){
            if (tree.getRoot().getPriority()<this.lista.get(min).getRoot().getPriority() || min == null){
                min = this.lista.indexOf(tree);
            }
        }
    }


    public void printFH(){
        for (binTree tree: this.lista){
            tree.printBT(tree);
            System.out.println("------------");
        }
    }

    public static void main(String[] args) {
        FibonacciHeap H = new FibonacciHeap();
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            list.add(new Node(i));
        }
        int i = 0;
        for (Node nodo: list){
            H.insert(nodo, i++);
        }

        H.printFH();
        H.extractMin();
        H.printFH();
    }


}
