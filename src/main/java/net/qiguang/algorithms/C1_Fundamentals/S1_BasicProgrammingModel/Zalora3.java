package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.*;

public class Zalora3 {

    public static List<Order> inputOrders;
    public static Set<Order> groupOrder = new HashSet<>();
    public static void main(String args[]) {
        Order o1 = new Order(5, 1);
        Order o2 = new Order(7, 3);
        Order o3 = new Order(6, 2);
        Order o4 = new Order(1, 2);
        Order o5 = new Order(2, 3);
        Order o6 = new Order(3, 4);

        inputOrders = new ArrayList<>(Arrays.asList(o1, o2, o3, o4, o5, o6));
        int k = 10;
        int maxVal = getMaxValueList(k, inputOrders.size());
        System.out.println("Max value : " + maxVal);
    }

    private static int getMaxValueList(int k, int size) {
        if (k <= 0) System.out.println("Wrong");
        if (size == 6) {
            System.out.println("--");
            groupOrder.clear();
        }
//        if (inputOrders.size() == 2) {
//            System.out.println("-------");
//            if (inputOrders.get(0).getNumItem() + inputOrders.get(1).getNumItem() <= k) {
//                groupOrder.add(inputOrders.get(0));
//                groupOrder.add(inputOrders.get(1));
//                return inputOrders.get(0).getVal() + inputOrders.get(1).getVal();
//            } else if (inputOrders.get(0).getNumItem() <= k && inputOrders.get(1).getNumItem() <= k) { // sai
//                if (inputOrders.get(0).getVal() >= inputOrders.get(1).getVal()) {
//                    groupOrder.add(inputOrders.get(0));
//                    return inputOrders.get(0).getVal();
//                } else {
//                    groupOrder.add(inputOrders.get(1));
//                    return inputOrders.get(1).getVal();
//                }
//            } else if (inputOrders.get(0).getNumItem() <= k) {
//                groupOrder.add(inputOrders.get(0));
//                return inputOrders.get(0).getVal();
//            } else if (inputOrders.get(1).getNumItem() <= k) {
//                groupOrder.add(inputOrders.get(1));
//                return inputOrders.get(1).getVal();
//            } else {
//                return 0;
//            }
//        }
        int current_max = 0;

        for (int i = size - 1; i >= 0 ; i--) {
            Order lastOrder = inputOrders.get(i);
            if (size == 6) {
                groupOrder.clear();
                groupOrder.add(lastOrder);
            }
            int totalVali = 0;
            if (k > lastOrder.getNumItem()) {
                totalVali = lastOrder.getVal() + getMaxValueList(k - lastOrder.numItem, size - 1); // sai cho nay, xac dinh item xoa ?
            } else if (k == lastOrder.getNumItem()) {
                totalVali = lastOrder.getVal();
                groupOrder.add(lastOrder);
            }
            if (current_max < totalVali) {
                current_max = totalVali;
            }
            if (size == 6) {
                System.out.print(i + ": " + totalVali + ": ");
                groupOrder.forEach(order -> System.out.print(order.numItem + " "));
                System.out.println();
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
