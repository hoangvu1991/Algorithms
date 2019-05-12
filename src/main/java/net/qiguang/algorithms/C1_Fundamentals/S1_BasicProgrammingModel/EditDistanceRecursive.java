package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.Arrays;

public class EditDistanceRecursive {
    private static final int MATCH = 0;
    private static final int INSERT = 1;
    private static final int DELETE = 2;

    public static void main(String args[]) {
        String s = "FOOD";
        String t = "MONEY";
        String s1 = "A";
        String t1 = "B";
        String s2 = "thou shalt not";
        String t2 = "you should not";
        int distance = string_compare(s, t, s.length(), t.length());
        //int distance2 = calculate(s2, t2);
        System.out.println(distance);
        //System.out.println(distance2);
    }

    static int string_compare(String s, String t, int i, int j) {
        int k; /* counter */
        int[] opt = new int[3]; /* cost of the three options */
        int lowest_cost; /* lowest cost */
        if (i == 0) return (j * indel(' '));
        if (j == 0) return (i * indel(' '));
        opt[MATCH] = string_compare(s, t, i - 1, j - 1) + match(s.charAt(i-1), t.charAt(j-1));
        opt[INSERT] = string_compare(s, t, i, j - 1) + indel(t.charAt(j-1));
        opt[DELETE] = string_compare(s, t, i - 1, j) + indel(s.charAt(i-1));
        lowest_cost = opt[MATCH];
        for (k = INSERT; k <= DELETE; k++)
            if (opt[k] < lowest_cost) lowest_cost = opt[k];
        return (lowest_cost);
    }
    static int match(char c, char d) {
        if (c == d) return 0;
        else return 1;
    }
    static int indel(char c) {
        return 1;
    }

    static int calculate(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }
        if (y.isEmpty()) {
            return x.length();
        }
        int substitution = calculate(x.substring(1), y.substring(1)) + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = calculate(x, y.substring(1)) + 1;
        int deletion = calculate(x.substring(1), y) + 1;
        return min(substitution, insertion, deletion);
    }
    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
    private static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

}
