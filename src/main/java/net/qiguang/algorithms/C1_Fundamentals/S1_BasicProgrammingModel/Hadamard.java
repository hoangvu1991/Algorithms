package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

/******************************************************************************
 *  Compilation:  javac Hadamard.java
 *  Execution:    java Hadamard n
 *
 *  Prints the Hadamard matrix of order n. Assumes n is a power of 2.
 *
 *  % java Hadamard 2
 *  * *
 *  * .
 *
 *  % java Hadamard 4
 *  * * * *
 *  * . * .
 *  * * . .
 *  * . . *
 *
 ******************************************************************************/

public class Hadamard {
    public static void main(String[] args) {
        int n = Integer.parseInt(args.length == 0 ? "3" : args[0]);
        boolean[][] hadamard = new boolean[n][n];

        // initialize Hadamard matrix of order n
        hadamard[0][0] = true;
        for (int k = 1; k < n; k += k) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    hadamard[i+k][j]   =  hadamard[i][j];
                    hadamard[i][j+k]   =  hadamard[i][j];
                    hadamard[i+k][j+k] = !hadamard[i][j];
                }
            }
        }

        // print matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hadamard[i][j]) System.out.print("T ");
                else                System.out.print("F ");
            }
            System.out.println();
        }
    }
}