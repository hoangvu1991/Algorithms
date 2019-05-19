package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.Arrays;

public class EditDistanceRecursive {
    private static final int MATCH = 0;
    private static final int INSERT = 1;
    private static final int DELETE = 2;
    public static final String ACTION_SUBSTITUTE = "Sub ";
    public static final String ACTION_INSERT = "Ins ";
    public static final String ACTION_DELETE = "Del ";
    public static Cell[][] m;

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

        int distance = string_compare(s2, t2);
        System.out.println(distance);
        reconstruct_path(s2, t2, s2.length() - 1, t2.length() - 1);
        System.out.println();

//        int distance2 = calculate(s2, t2);
//        System.out.println(distance2);

//        int distance3 = calculateDynamic(s2, t2);
//        System.out.println(distance3);
    }

    static int string_compare(String s, String t, int i, int j) {
        int[] opt = new int[3]; // cost of the three options
        if (i == 0) return (j * indel(' '));
        if (j == 0) return (i * indel(' '));
        opt[MATCH] = string_compare(s, t, i - 1, j - 1) + match(s.charAt(i - 1), t.charAt(j - 1));
        opt[INSERT] = string_compare(s, t, i, j - 1) + indel(t.charAt(j - 1));
        opt[DELETE] = string_compare(s, t, i - 1, j) + indel(s.charAt(i - 1));
        return min(opt[MATCH], opt[INSERT], opt[DELETE]); // lowest cost
    }

    static int string_compare(String s, String t) {
        int MAXLEN_S = s.length();
        int MAXLEN_T = t.length();
        int k; // counters
        int[] opt = new int[3]; // cost of the three options
        m = new Cell[MAXLEN_S][MAXLEN_T];
        for (int i = 0; i < MAXLEN_S; i++) {
            for (int j = 0; j < MAXLEN_T; j++) {
                m[i][j] = new Cell();
            }
        }
        for (int i = 0; i < MAXLEN_T; i++) {
            row_init(i);
        }
        for (int j = 0; j < MAXLEN_S; j++) {
            column_init(j);
        }
        for (int i = 1; i < MAXLEN_S; i++) {
            for (int j = 1; j < MAXLEN_T; j++) {
                opt[MATCH] = m[i - 1][j - 1].cost + match(s.charAt(i - 1), t.charAt(j - 1));
                opt[INSERT] = m[i][j - 1].cost + indel(t.charAt(j - 1));
                opt[DELETE] = m[i - 1][j].cost + indel(s.charAt(i - 1));
                m[i][j].cost = opt[MATCH];
                m[i][j].parent = MATCH;

                for (k = INSERT; k <= DELETE; k++) {
                    if (opt[k] < m[i][j].cost) {
                        m[i][j].cost = opt[k];
                        m[i][j].parent = k;
                    }
                }
            }
        }
        //goal_cell(s, t, & i,&j);
        return (m[MAXLEN_S - 1][MAXLEN_T - 1].cost);
    }

    static void reconstruct_path(String s, String t, int i, int j) {
        if (m[i][j].parent == -1) return;
        if (m[i][j].parent == MATCH) {
            reconstruct_path(s, t, i - 1, j - 1);
            match_out(s, t, i, j);
            return;
        }
        if (m[i][j].parent == INSERT) {
            reconstruct_path(s, t, i, j - 1);
            insert_out(t, j);
            return;
        }
        if (m[i][j].parent == DELETE) {
            reconstruct_path(s, t, i - 1, j);
            delete_out(s, i);
            return;
        }
    }

    static void match_out(String s, String t, int i, int j) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            System.out.print("M ");
        } else {
            System.out.print("S ");
        }
    }

    static void insert_out(String t, int j) {
        System.out.print("I ");
    }

    static void delete_out(String s, int i) {
        System.out.print("D ");
    }

    static void row_init(int i) {
        m[0][i].cost = i;
        if (i > 0)
            m[0][i].parent = INSERT;
        else
            m[0][i].parent = -1;
    }

    static void column_init(int i) {
        m[i][0].cost = i;
        if (i > 0)
            m[i][0].parent = DELETE;
        else
            m[i][0].parent = -1;
    }

    private static class Cell {
        int cost; // cost of reaching this cell
        int parent; // parent cell

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }
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
