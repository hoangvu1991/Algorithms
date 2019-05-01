package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdDraw;

/******************************************************************************
 *  Compilation:  javac FunctionsExamples.java
 *  Execution:    none
 *
 *  Examples of functions.
 *
 ******************************************************************************/

public class FunctionExamples {

    // absolute value of an int value
    public static int abs(int x) {
        if (x < 0) return -x;
        else       return  x;
    }

    // absolute value of a double value (overloading)
    public static double abs(double x) {
        if (x < 0) return -x;
        else       return  x;
    }

    // primality test (multiple return statements)
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n/i; i++)
            if (n % i == 0) return false;
        return true;
    }

    // hypotenuse of a right triangle (multiple arguments)
    // see also Math.hypot()
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(a*a + b*b);
    }

    // Harmonic number
    public static double harmonic(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++)
            sum += 1.0 / i;
        return sum;
    }

    // uniform random integer in [0, n)
    public static int uniform(int n) {
        return (int) (Math.random() * n);
    }

    // draw a triangle to standard draw (side effects)
    public static void drawTriangle(double x0, double y0, double x1, double y1, double x2, double y2) {
        StdDraw.line(x0, y0, x1, y1);
        StdDraw.line(x1, y1, x2, y2);
        StdDraw.line(x2, y2, x0, y0);
    }

    public static void main(String[] args) {
        System.out.println(harmonic(99));
    }
}