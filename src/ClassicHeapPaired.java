import java.util.Arrays;

/**
 * Dijkstra con Heap Clasico
 * Children of the node at position n would be at positions 2n + 1 and 2n + 2 in a zero-based array.
 * Balancing a heap is done by sift-up or sift-down operations
 * Insertion is often done by adding the new element at the end of the heap in the first available free space.
 * This will generally violate the heap property, and so the elements are then sifted up until the heap property has been reestablished.
 * Similarly, deleting the root is done by removing the root and then putting the last element in the root and sifting down to rebalance. Thus replacing is done by deleting the root
 * and putting the new element in the root and sifting down, avoiding a sifting up step compared to pop (sift down of last element) followed by push (sift up of new element).
 */
public class ClassicHeapPaired {
    // Variables internas
    private Pair heap[];
    private int index;

    /** Constructor
     *
     * @param elements
     */
    public ClassicHeapPaired(Pair[] elements) {
        this.index = 0;
        this.heap = new Pair[elements.length];
        this.heapify(elements, elements.length);
    }

    /**
     * Inserta los elements en el heap
     *
     * @param elements
     * @param ind
     */
    private void heapify(Pair[] elements, int ind) {
        for (int i = 0; i < ind; i++) {
            this.insertKey(elements[i]);
        }
    }

    /**
     * Inserta val en el indice 'index' del heap
     *
     * @param nodePair
     */
    private void insertKey(Pair nodePair) {
        // Chequear capacidad (Ya sabemos el tamaño del heap)
        int i = this.index;
        this.heap[i] = nodePair;

        //Mientras que el padre sea mayor al hijo
        while (i != 0 && heap[(i - 1) / 2].getDistance() > heap[i].getDistance()) {
            // Swap
            this.swap((i - 1) / 2, i);
            //Actualizar indice al padre
            i = (i - 1) / 2;
        }

        //Aumentar indice donde insertar
        this.index++;
    }

    /**
     * Intercambia los valores de los indices i por el j
     *
     * @param i indice a intercambiar
     * @param j indice a intercambiar
     */
    private void swap(int i, int j) {
        Pair tmp = this.heap[i];
        this.heap[i] = heap[j];
        this.heap[j] = tmp;
    }

    /**
     * Extrae el minimo y reordena el heap
     *
     * @return el minimo del heap
     */
    public Pair extractMin() {
        Pair min = new Pair(-1, Integer.MAX_VALUE);
        if(this.index < 0){
            return min;
        }else{
            min = this.heap[0];
            //Reemplazar raiz con el ultimo elemento apuntado por index
            this.heap[0] = this.heap[this.index - 1];
            this.heap[this.index - 1] = new Pair(-1, Integer.MAX_VALUE);
            // Chequear que no sea negativo
            this.index--;



            int i = 0;
            // Mientras el elemento sea mayor a alguno de sus hijos
            while (i < this.index) {
                int j = 0;
                // Si el hijo derecho no esta dentro de los elementos restante, el hijo izquierdo es el min
                if ((2 * i + 2) > (this.index - 1))
                    j = 2 * i + 1;
                // Si el hijo izquierdo no esta dentro de los elementos restantes, no hay swap
                if ((2 * i + 1) > (this.index - 1))
                    break;
                    // Si ambos hijos estan dentro de los elementos, buscar el minimo
                else {
                    if (this.heap[2 * i + 1].getDistance() > this.heap[2 * i + 2].getDistance()) {
                        j = 2 * i + 2;
                    } else {
                        j = 2 * i + 1;
                    }
                }

                if (this.heap[i].getDistance() > this.heap[j].getDistance()) {
                    // Intercambiar con el menor
                    this.swap(i, j);
                    i = j;
                } else {
                    break;
                }
            }

            return min;
        }

    }

    /**
     * Inserta el valor newVal en heap[ind] donde newVal < heap[ind]
     *
     * @param nodeId:    Indice a cambiar
     * @param newDist: Valor a insertar en heap[ind]
     */
    public void decreaseKey(int nodeId, int newDist) {
        int ind = 0;
        // Buscar nodo y cambiar su valor
        for(Pair p : heap){
            if(p.getNodeId() == nodeId){
                ind = p.getNodeId(); // guardar el indice del nodo
                p.setDistance(newDist);
                break;
            }
        }
        // Cambiar el valor
        //this.heap[ind] = newDist;

        // Cambiar elementos que rompan el invariante
        // Mientras que la distancia del padre sea mayor que la del hijo
        while (ind > 0 && this.heap[(ind - 1) / 2].getDistance() > heap[ind].getDistance()) {
            swap(ind, (ind - 1) / 2);
            ind = (ind - 1) / 2;
        }
    }

    public Pair[] getHeap() {
        return this.heap;
    }

    public int getIndex() {
        return this.index;
    }

    public static void main(String[] args) {
        int[] elements = {10, 5, 3, 15, 12, 11, 8, 14, 17, 13, 4, 16, 9, 20, 25};
        System.out.println(Arrays.toString(elements));
        int[] nodeId = new int[elements.length];
        for(int i=0; i<elements.length; i++){
            nodeId[i] = i;
        }
        System.out.println(Arrays.toString(nodeId));
        Pair[] pairNodes = new Pair[elements.length];
        for(int i=0; i<elements.length; i++){
            pairNodes[i] = new Pair(nodeId[i], elements[i]);
        }

        System.out.println("-----o-----");

        ClassicHeapPaired nd = new ClassicHeapPaired(pairNodes);
        System.out.println(Arrays.toString(nd.getHeap()));
        System.out.println("heap index = " + nd.getIndex());

        System.out.println("-----o-----");
        System.out.println("decreaseKey index 6 con newVal 1");
        nd.decreaseKey(6, 1);
        System.out.println(Arrays.toString(nd.getHeap()));
        System.out.println("heap index = " + nd.getIndex());

        System.out.println("min1: " + nd.extractMin());
        System.out.println(Arrays.toString(nd.getHeap()));
        System.out.println("heap index = " + nd.getIndex());

        System.out.println("min2: " + nd.extractMin());
        System.out.println(Arrays.toString(nd.getHeap()));
        System.out.println("heap index = " + nd.getIndex());

        System.out.println("min3: " + nd.extractMin());
        System.out.println(Arrays.toString(nd.getHeap()));
        System.out.println("heap index = " + nd.getIndex());

        /*
        System.out.println(nd.distances.length);
        System.out.println(nd.marked.length);
        for(boolean b : nd.marked){
            System.out.println(b);
        }
        */


    }

}
