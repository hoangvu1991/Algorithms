package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Hexadecimal {

    public static int hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }

    // precondition:  d is a nonnegative integer
    public static String decimal2Hex(int d) {
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = d % 16;                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 16;
        }
        return hex;
    }

    // precondition:  d is a nonnegative integer
    public static String decimal2Binary(int d) {
        String digits = "01";
        if (d == 0) return "0";
        String bina = "";
        while (d > 0) {
            int digit = d % 2;                // rightmost digit
            bina = digits.charAt(digit) + bina;  // string concatenation
            d = d / 2;
        }
        return bina;
    }


    public static void main(String[] args) {
        String n = "11A"; // args[0];
        int decimal = hex2Decimal(n);
        StdOut.println(decimal);

        String hex = decimal2Hex(19);
        StdOut.println(hex);

        String bina = decimal2Binary(106);
        StdOut.println(bina);

        System.out.println(Integer.toBinaryString(106));
    }
}