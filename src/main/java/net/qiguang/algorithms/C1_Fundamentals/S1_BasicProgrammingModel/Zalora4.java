package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.*;

public class Zalora4 {

    private static Order[] inputOrders = new Order[6];
//    private static Set<Order> groupOrder = new HashSet<>();

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
    }

    private static int knapsack(Order[] orders, int k) {
        return knapsack(orders, k, 0);
    }

    private static int knapsack(Order[] order, int k, int i) {
        // Nếu xét hết các item thì return
        if (i == order.length) return 0;
        // Nếu item quá lớn thì bỏ qua và xét đến item tiếp theo
        if (k - order[i].getNumItem() < 0)
            return knapsack(order, k, i + 1);
        // Tìm max trong cả 2 trường hợp đã nêu
        int max = Math.max(
                knapsack(order, k - order[i].getNumItem(), i + 1) + order[i].getVal(),
                knapsack(order, k, i + 1));
        return max;
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
