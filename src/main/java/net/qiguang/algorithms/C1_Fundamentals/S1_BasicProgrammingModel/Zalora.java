package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Zalora {

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
        List<Order> maxValueList = getMaxValueList(k, orders);
        System.out.println("Num Items" + ":" + "Value");
        maxValueList.stream().forEach(order -> System.out.println(order.getNumItem() + ":" + order.getVal()));
        System.out.println("Max value : " + maxValueList.stream().mapToInt(Order::getVal).sum());
    }

    private static List<Order> getMaxValueList(int k, List<Order> orders) {
        if (orders.size() == 2) {
            if (orders.get(0).getNumItem() + orders.get(1).getNumItem() <= k) {
                return orders;
            } else if (orders.get(0).getNumItem() <= k && orders.get(1).getNumItem() <= k) { // sai
                if (orders.get(0).getVal() >= orders.get(1).getVal()) {
                    return new ArrayList<>(Collections.singletonList(orders.get(0)));
                } else {
                    return new ArrayList<>(Collections.singletonList(orders.get(1)));
                }
            } else if (orders.get(0).getNumItem() <= k) {
                return new ArrayList<>(Collections.singletonList(orders.get(0)));
            } else if (orders.get(1).getNumItem() <= k) {
                return new ArrayList<>(Collections.singletonList(orders.get(1)));
            } else {
                return new ArrayList<>();
            }
        }
        Order order1 = orders.get(0);
        List<Order> removedOrders = new ArrayList<>(orders);
        orders.remove(0);
        removedOrders.remove(0);
        List<Order> maxValueList1 = getMaxValueList(k - order1.getNumItem(), orders);
        maxValueList1.add(order1);

        List<Order> maxValueList2 = getMaxValueList(k, removedOrders);

        if (maxValueList1.stream().mapToInt(Order::getVal).sum() >=
                maxValueList2.stream().mapToInt(Order::getVal).sum()) {
            return maxValueList1;
        } else {
            return maxValueList2;
        }
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
