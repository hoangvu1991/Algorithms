package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

/******************************************************************************
 *  Compilation:  javac RandomSurfer.java
 *  Execution:    java RandomSurfer trials
 *  Data files:   https://introcs.cs.princeton.edu/java/16pagerank/tiny.txt
 *                https://introcs.cs.princeton.edu/java/16pagerank/medium.txt
 *
 *  % java Transition < tiny.txt | java RandomSurfer 1000000
 *   0.27297 0.26583 0.14598 0.24729 0.06793
 *
D:\projects\Algorithms\out\production\classes>
java net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel.Transition < ..\resources\algs4-data\book\tiny.txt |
java net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel.RandomSurfer 1000000
 ******************************************************************************/

public class RandomSurfer {
    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);   // number of moves
        int m = StdIn.readInt();                  // number of pages  - ignore since m = n
        int n = StdIn.readInt();                  // number of pages
        if (m != n) {
            StdOut.println("m does not equal n");
            return;
        }

        // Read transition matrix.
        double[][] p = new double[n][n];          // p[i][j] = prob. that surfer moves from page i to page j
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = StdIn.readDouble();
                StdOut.printf("%7.5f ",  p[i][j]);
            }
            StdOut.println();
        }

        int[] freq = new int[n];                  // freq[i] = # times surfer hits page i

        // Start at page 0.
        int page = 0;

        for (int t = 0; t < trials; t++) {
            // Make one random move.
            double r = Math.random();
            //System.out.println("r: " + r);
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                // Find interval containing r.
                sum += p[page][j];
                System.out.printf("%d %7.5f _ %7.5f: ", j, r, sum);System.out.println();
                if (r < sum) {
                    page = j;
                    System.out.println("page: " + page);
                    break;
                }
            }
            freq[page]++;
        }

        // Print page ranks.
        for (int i = 0; i < n; i++) {
            StdOut.printf("%8.5f", (double) freq[i] / trials);
        }
        StdOut.println();
    }
}