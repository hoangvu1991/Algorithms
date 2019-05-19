package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.Arrays;

public class EditDistanceRecursive {
    private static final int MATCH = 0;
    private static final int INSERT = 1;
    private static final int DELETE = 2;
    public static final String ACTION_SUBSTITUTE = "Sub ";
    public static final String ACTION_INSERT = "Ins ";
    public static final String ACTION_DELETE = "Del ";

    public static void main(String args[]) {
        String s = "FOOD";
        String t = "MONEY";
        String s1 = "A";
        String t1 = "B";
        String s2 = "thou shalt not";
        String t2 = "you should not";
//        Result resultDistance = string_compare(s, t, s.length(), t.length());
//        System.out.println(resultDistance.cost);
//        System.out.println(resultDistance.action);

//        int distance = string_compare(s2, t2, s2.length(), t2.length());
//        System.out.println(distance);

//        int distance2 = calculate(s, t);
//        System.out.println(distance2);
//
        int distance3 = calculateDynamic(s2, t2);
        System.out.println(distance3);
    }

    /*
    static Result string_compare(String s, String t, int i, int j) {
        int[] opt = new int[3]; // cost of the three options
        String action = "";
        Result result = new Result();

        if (i == 0) {
            result.setCost(j * indel(' '));
            int count = j + 1;
            result.setAction(count + " " + ACTION_INSERT);
            return result;
        }
        if (j == 0) {
            result.setCost(i * indel(' '));
            int count = i + 1;
            result.setAction(count + " " + ACTION_DELETE);
            return result;
        }
        Result resultSub = string_compare(s, t, i - 1, j - 1);
        opt[MATCH] = resultSub.getCost() + match(s.charAt(i - 1), t.charAt(j - 1));
        if (s.charAt(i - 1) !=  t.charAt(j - 1)) {
            action += ACTION_SUBSTITUTE;
        }

        Result resultIns = string_compare(s, t, i, j - 1);
        opt[INSERT] = resultIns.getCost() + indel(t.charAt(j - 1));

        Result resultDel = string_compare(s, t, i - 1, j);
        opt[DELETE] = resultDel.getCost() + indel(s.charAt(i - 1));

        int min = min(opt[MATCH], opt[INSERT], opt[DELETE]);


        if (min == opt[MATCH]) {
            action += resultSub.action;
        } else if (min == opt[INSERT]) {
            action += resultIns.action;
        } else if (min == opt[DELETE]) {
            action += resultDel.action;
        }

        result.setCost(min);
        result.setAction(action);


        return result; // lowest cost
    }
    */
    static int string_compare(String s, String t, int i, int j) {
        int[] opt = new int[3]; // cost of the three options
        if (i == 0) return (j * indel(' '));
        if (j == 0) return (i * indel(' '));
        opt[MATCH] = string_compare(s, t, i - 1, j - 1) + match(s.charAt(i - 1), t.charAt(j - 1));
        opt[INSERT] = string_compare(s, t, i, j - 1) + indel(t.charAt(j - 1));
        opt[DELETE] = string_compare(s, t, i - 1, j) + indel(s.charAt(i - 1));
        return min(opt[MATCH], opt[INSERT], opt[DELETE]); // lowest cost
    }

    private static class Result {
        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        int cost = 0;
        String action;
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

    static int calculateDynamic(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[x.length()][y.length()];
    }
}
