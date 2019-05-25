package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Zalora2 {

    public static void main(String args[]) {
        Order o1 = new Order(5, 1);
        Order o2 = new Order(7, 3);
        Order o3 = new Order(6, 2);
        Order o4 = new Order(1, 2);
        Order o5 = new Order(2, 3);
        Order o6 = new Order(3, 4);

//        List<Integer> integers = new ArrayList<>(Collections.singletonList(1));
//        integers.add(2);

        List<Order> orders = new ArrayList<>(Arrays.asList(o1, o2, o3, o4, o5, o6));
        int k = 10;
        int maxVal = getMaxValueList(k, orders);
        System.out.println("Max value : " + maxVal);
    }

    private static int getMaxValueList(int k, List<Order> orders) {
        if (k <= 0) System.out.println("Wrong");
        if (orders.size() == 2) {
            if (orders.get(0).getNumItem() + orders.get(1).getNumItem() <= k) {
                return orders.get(0).getVal() + orders.get(1).getVal();
            } else if (orders.get(0).getNumItem() <= k && orders.get(1).getNumItem() <= k) { // sai
                if (orders.get(0).getVal() >= orders.get(1).getVal()) {
                    return orders.get(0).getVal();
                } else {
                    return orders.get(1).getVal();
                }
            } else if (orders.get(0).getNumItem() <= k) {
                return orders.get(0).getVal();
            } else if (orders.get(1).getNumItem() <= k) {
                return orders.get(1).getVal();
            } else {
                return 0;
            }
        }
        int current_max = 0;
        for (int i = 0; i < orders.size(); i++) {
            Order orderi = orders.get(i);
            List<Order> removedOrdersi = new ArrayList<>(orders);
            removedOrdersi.remove(i);
            int totalVali = 0;
            if (k > orderi.getNumItem()) {
                totalVali = orderi.getVal() + getMaxValueList(k - orderi.getNumItem(), removedOrdersi);
            } else if (k == orderi.getNumItem()) {
                totalVali = orderi.getVal();
            }
            if (current_max < totalVali) {
                current_max = totalVali;
            }
            if (orders.size() == 6) {
                System.out.println(i + ": " + current_max);
            }
        }
        return current_max;
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
