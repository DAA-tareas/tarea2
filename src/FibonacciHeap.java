import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap {
    private List<binTree> lista;
    private int min;

    public FibonacciHeap(){
        this.lista = new ArrayList<>();
    }

    public void insert(int value){
        binTree b0 = new binTree(value);

        if (lista.isEmpty()){
            lista.add(b0);
            min = 0;
        }
        else{
            lista.add(b0);
            if (value < lista.get(this.min).getRoot().getValue()){
                this.min = lista.size() - 1;
            }
        }
    }

    public int findMin(){
        return this.lista.get(min).getRoot().getValue();
    }

    public void merge(FibonacciHeap fibHeap){
        this.lista.addAll(fibHeap.lista);
    }
}
