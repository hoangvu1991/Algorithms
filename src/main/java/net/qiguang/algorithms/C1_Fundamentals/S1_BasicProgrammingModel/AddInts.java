package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac AddInts.java
 *  Execution:    java AddInts
 *  Dependencies: StdIn.java StdOut.java
 *
 *  This program takes a command-line argument n, reads in n integers,
 *  and prints out their sum.
 *
 *  % java AddInts n
 *
 ******************************************************************************/

public class AddInts {
    public static void main(String[] args) {
        int n = 4;//Integer.parseInt(args[0]);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int value = StdIn.readInt();
            sum = sum + value;
        }
        StdOut.println("Sum is " + sum);
    }
}