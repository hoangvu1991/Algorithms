package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
    private static String[] arr = {"A","B","C","D","E","F"};
    private static List<String[]> allResults = new ArrayList<>();

    public static void main(String[] args){
        int m = 2;
        String[] result = new String[m];
//        combinations2(m, 0, result);
//        System.out.println(allResults.size());
//        allResults.stream().forEach(arr -> System.out.println(Arrays.toString(arr)));

        List<List<String>> combine = combine(m, new ArrayList<>(Arrays.asList(arr)));
        System.out.println(combine.size());
        combine.stream().forEach(c -> System.out.println(c));
    }

    static void combinations2(int choose, int startPosition, String[] result){
        if (choose == 0){
            //System.out.println(Arrays.toString(result));
            allResults.add(result.clone());
            return;
        }
        for (int i = startPosition; i <= arr.length-choose; i++){
            result[result.length - choose] = arr[i];
            combinations2(choose-1, i+1, result);
        }
    }

    static List<List<String>> combine(int k, List<String> strings) {
        if (k == strings.size()) {
            ArrayList<String> es = new ArrayList<>(strings);
            ArrayList<List<String>> lists = new ArrayList<>();
            lists.add(es);
            return lists;
        }
        if (k == 0) {
            ArrayList<List<String>> lists = new ArrayList<>();
            lists.add(new ArrayList<>());
            return lists;
        }
        String removedString = strings.remove(0);
        List<String> strings2 = new ArrayList<>(strings);
        List<List<String>> result1 = combine(k - 1, strings);
        result1.forEach(l -> l.add(removedString));

        List<List<String>> result2 = combine(k, strings2);

        result1.addAll(result2);
        return result1;
    }
}