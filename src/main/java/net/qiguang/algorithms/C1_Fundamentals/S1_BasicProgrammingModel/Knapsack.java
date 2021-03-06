package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.*;

public class Knapsack {

    private static Order[] inputOrders = new Order[6];

    public static void main(String args[]) {
        Order o1 = new Order(5, 1);
        Order o2 = new Order(7, 3);
        Order o3 = new Order(6, 2);
        Order o4 = new Order(1, 2);
        Order o5 = new Order(2, 3);
        Order o6 = new Order(3, 4);

        inputOrders = new Order[]{o1, o2, o3, o4, o5, o6};
        int k = 10;
        int maxVal = knapsack(inputOrders, k);
        System.out.println(maxVal);
        int maxVal2 = knapsackTopDown(inputOrders, k);
        System.out.println(maxVal2);
        int maxVal3 = knapsackBottomUp(inputOrders, k);
        System.out.println(maxVal3);
    }

    private static int knapsack(Order[] orders, int k) {
        return knapsack(orders, k, 0);
    }

    private static int knapsack(Order[] order, int k, int i) {
        // Nếu xét hết các item thì return
        if (i == order.length) return 0;
        // Nếu item quá lớn thì bỏ qua và xét đến item tiếp theo
        int excludeI = knapsack(order, k, i + 1);
        if (k - order[i].getNumItem() < 0)
            return excludeI;
        int inscludeI = knapsack(order, k - order[i].getNumItem(), i + 1) + order[i].getVal();
        //System.out.println("insclude " + i + " : " + inscludeI);
        // Tìm max trong cả 2 trường hợp đã nêu
        return Math.max(inscludeI, excludeI);
    }

    // Top down dynamic programming solution.
    // Cache values in a HashMap - the cache may be sparse
    public static int knapsackTopDown(Order[] items, int k) {
        // Map: i -> k -> value
        List<Map<Integer, Integer>> cache = new ArrayList<>();
        return knapsackTopDown(items, k, 0, cache);
    }

    // Overloaded recursive function
    private static int knapsackTopDown(Order[] items, int k, int i, List<Map<Integer, Integer>> cache) {
        if (i == items.length) {
            return 0;
        }
        // Check if the value is in the cache
        if (!cache.contains(i)) {
            cache.add(i, new HashMap<>());
        }
        Integer cached = cache.get(i).get(k);
        if (cached != null) {
            return cached;
        }
        // Compute the item and add it to the cache
        int toReturn;
        if (k - items[i].getNumItem() < 0) {
            toReturn = knapsackTopDown(items, k, i + 1, cache);
        } else {
            toReturn = Math.max(knapsackTopDown(items, k - items[i].getNumItem(), i + 1, cache) + items[i].getVal(),
                    knapsackTopDown(items, k, i + 1, cache));
        }
        cache.get(i).put(k, toReturn);
        return toReturn;
    }

    // Iterative bottom up solution.
    public static int knapsackBottomUp(Order[] orders, int k) {
        // Initialize cache
        int[][] cache = new int[orders.length + 1][k + 1];
        // For each order and numItem, compute the max value of the orders up to that order that doesn't go over k numberItem
        for (int i = 1; i <= orders.length; i++) {
            for (int j = 0; j <= k; j++) {
                if (orders[i - 1].getNumItem() > j) {
                    cache[i][j] = cache[i - 1][j];
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j],
                            cache[i - 1][j - orders[i - 1].getNumItem()] + orders[i - 1].getVal());
                }
            }
        }
        return cache[orders.length][k];
    }

    private static class Order {
        int numItem;
        int val;

        Order(int numItem, int val) {
            this.numItem = numItem;
            this.val = val;

        }

        public int getNumItem() {
            return numItem;
        }

        public void setNumItem(int numItem) {
            this.numItem = numItem;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}
