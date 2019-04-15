package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;


public class Sqrtn {
    public static void main(String[] args) {

        // read in the command-line argument
        double c = 16; // Double.parseDouble(args[0]);
        double k = 4; // Double.parseDouble(args[0]);
        double epsilon = 1.0e-15;  // relative error tolerance// 1.0e-1 == 0.1
        double t = c;              // estimate of the square root of c

        // repeatedly apply Newton update step until desired precision is achieved
        while (Math.abs(Math.pow(t, k) - c) > epsilon*t) {
            t = ((k-1) * t + c/Math.pow(t, k-1))/k;
        }

        // print out the estimate of the square root of c
        System.out.println(t);
    }

}