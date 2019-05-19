package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
    private static String[] arr = {"A","B","C","D","E","F"};
    private static List<String[]> allResults = new ArrayList<>();

    public static void main(String[] args){
        int m = 3;
        String[] result = new String[m];
        combinations2(m, 0, result);
        allResults.stream().forEach(arr -> System.out.println(Arrays.toString(arr)));
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
}