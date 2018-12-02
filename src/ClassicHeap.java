/**
 * Dijkstra con Heap Clasico
 * Children of the node at position n would be at positions 2n + 1 and 2n + 2 in a zero-based array.
 * Balancing a heap is done by sift-up or sift-down operations
 * Insertion is often done by adding the new element at the end of the heap in the first available free space.
     * This will generally violate the heap property, and so the elements are then sifted up until the heap property has been reestablished.
 * Similarly, deleting the root is done by removing the root and then putting the last element in the root and sifting down to rebalance. Thus replacing is done by deleting the root
     * and putting the new element in the root and sifting down, avoiding a sifting up step compared to pop (sift down of last element) followed by push (sift up of new element).
 */
public class ClassicHeap {
    // Variables internas
    private int heap[];

    // Constructor
    public ClassicHeap(int[] elements){
        this.heap = new int[elements.length];
        //this.heapify(elements)
    }

    // sift up

    // sift down

    //heapify

    public static void main(String[] args){
        //ClassicHeap nd = new ClassicHeap(10);
        /*
        System.out.println(nd.distances.length);
        System.out.println(nd.marked.length);
        for(boolean b : nd.marked){
            System.out.println(b);
        }
        */
    }

}
