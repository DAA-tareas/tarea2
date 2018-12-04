import java.util.stream.DoubleStream;

public class Test {

    public static void test(int n, int e) {
        System.out.println("Tiempos de ejecucion de Dijkstra para n=" + n + " e=10^" + e);

        int iterations = 2;

        double[] naiveTimes = new double[iterations];
        double[] classicTimes = new double[iterations];
        double[] fiboTimes = new double[iterations];


        for (int i = 0; i < iterations; i++) {
            System.out.print("Started " + i + " rep ");
            double iniGraphBuild = System.currentTimeMillis();
            GraphOfNodes g = new GraphOfNodes(n, e);
            double deltaGraphBuild = System.currentTimeMillis() - iniGraphBuild;
            //System.out.println("Time building graph " + deltaGraphBuild + " ms");

            // Naive
            NaiveDijkstra nd = new NaiveDijkstra(n);
            double iniNaiveTime = System.currentTimeMillis();
            nd.run(g);
            double deltaNaiveTime = System.currentTimeMillis() - iniNaiveTime;
            naiveTimes[i] = deltaNaiveTime;

            // Classic Heap
            PriorityDijkstra pdClassic = new PriorityDijkstra(n);
            double iniClassicTime = System.currentTimeMillis();
            pdClassic.classicHeapDijkstra(g, g.getNodes().get(0));
            double deltaClassicTime = System.currentTimeMillis() - iniClassicTime;
            classicTimes[i] = deltaClassicTime;

            // Fibonacci

            PriorityDijkstra pdFibonacci = new PriorityDijkstra(n);
            double iniFibonacciTime = System.currentTimeMillis();
            pdFibonacci.fibonacciHeapDijkstra(g, g.getNodes().get(0));
            double deltaFibonacciTime = System.currentTimeMillis() - iniFibonacciTime;
            fiboTimes[i] = deltaFibonacciTime;
            /*
            System.out.println("Naive Approach " + deltaNaiveTime + " ms");
            System.out.println("Priority-Classic-Heap " + deltaClassicTime + " ms");
            System.out.println("Priority-Fibonacci-Heap " + deltaFibonacciTime + " ms");
            System.out.println("-------------");
            */
        }

        //Promedios
        double meanNaive = DoubleStream.of(naiveTimes).sum()/iterations;
        double meanClassic = DoubleStream.of(classicTimes).sum()/iterations;
        double meanFibo = DoubleStream.of(fiboTimes).sum()/iterations;

        //Errores
        double stdNaive2 = 0;
        double stdClassic2 = 0;
        double stdFibo2 = 0;

        for(int i=0; i<iterations; i++){
            stdNaive2 += Math.pow(naiveTimes[i] - meanNaive, 2);
            stdClassic2 += Math.pow(classicTimes[i] - meanClassic, 2);
            stdFibo2 += Math.pow(fiboTimes[i] - meanFibo, 2);
        }

        stdNaive2 = Math.pow(stdNaive2/iterations, 0.5);
        stdClassic2 = Math.pow(stdClassic2/iterations, 0.5);
        stdFibo2 = Math.pow(stdFibo2/iterations, 0.5);

        System.out.println("Resultados despues de " + iterations + " iteraciones");
        System.out.println("Promedio Naive " + meanNaive + " Error=" + stdNaive2);
        System.out.println("Promedio Classic " + meanClassic + " Error=" + stdClassic2);
        System.out.println("Promedio Fibo " + meanFibo + " Error=" + stdFibo2);

    }


    public static void main(String[] args) {
        int n = 100000;
        int repeat = 1;
        for (int i = 0; i < repeat; i++) {
            Test.test(n, 10 * n);
            System.out.println("-------------");
            Test.test(n, 100 * n);
            //System.out.println("-------------");
            //Test.test(n, 1000 * n);
        }
    }

}
