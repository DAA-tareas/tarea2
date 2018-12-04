/**
 * Created by GPG on 12/3/2018.
 */
public class Test {

    public static void test(int n, int e){
        System.out.println("Tiempos de ejecucion de Dijkstra para n=" + n + " e=10^" + e);
        for (int i = 0; i<10; i++) {

            double iniGraphBuild = System.currentTimeMillis();
            GraphOfNodes g = new GraphOfNodes(n, e);
            double deltaGraphBuild = System.currentTimeMillis() - iniGraphBuild;
            System.out.println("Time building graph " + deltaGraphBuild + " ms");
            // Naive
            NaiveDijkstra nd = new NaiveDijkstra(n);
            double iniNaiveTime = System.currentTimeMillis();
            nd.run(g);
            double deltaNaiveTime = System.currentTimeMillis() - iniNaiveTime;

            // Classic Heap
            PriorityDijkstra pdClassic = new PriorityDijkstra(n);
            double iniClassicTime = System.currentTimeMillis();
            pdClassic.classicHeapDijkstra(g, g.getNodes().get(0));
            double deltaClassicTime = System.currentTimeMillis() - iniClassicTime;

            // Fibonacci
            PriorityDijkstra pdFibonacci = new PriorityDijkstra(n);
            double iniFibonacciTime = System.currentTimeMillis();
            pdFibonacci.fibonacciHeapDijkstra(g, g.getNodes().get(0));
            double deltaFibonacciTime = System.currentTimeMillis() - iniFibonacciTime;

            System.out.println("test nº" + i);
            System.out.println("Naive Approach " + deltaNaiveTime + " ms");
            System.out.println("Priority-Classic-Heap " + deltaClassicTime + " ms");
            System.out.println("Priority-Fibonacci-Heap " + deltaFibonacciTime + " ms");
        }
    }


    public static void main(String[] args){
        int n = 100000;
        for (int i = 0; i<3; i++) {
            Test.test(n, 10*n);
            Test.test(n, 100 * n);
            Test.test(n, 1000*n);
        }
    }

}
