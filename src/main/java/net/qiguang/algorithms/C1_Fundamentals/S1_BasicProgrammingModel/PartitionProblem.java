package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

public class PartitionProblem {
    private boolean partitionPossible(int requiredSum, int[] set) {
        // each column indicates the set we are looking at and each row indicates the sum we are looking for
        boolean[][] solution = new boolean[requiredSum + 1][set.length + 1];

        int numRows = requiredSum + 1, numCols = set.length + 1;

        // we can always find an empty subset with sum = 0
        for (int j = 0; j < numCols; j++) {
            solution[0][j] = true;
        }

        // no sum except 0 could be obtained using an empty subset
        for (int i = 1; i < numRows; i++) {
            solution[i][0] = false;
        }


        for (int j = 1; j < numCols; j++) {
            for (int i = 0; i < numRows; i++) {
                /*
                 * Rule 1 - If solution[i][j-1] is 'true' then solution[i][j] is also 'true'. This is because -
                 * if there exists a subset(with sum = 'i') of set formed by first 'j-1' elements (of given set)
                 * then that same subset will also be a subset for the set formed by first 'j' elements.
                 *
                 * Rule 2 - If solution[i][j-1] is 'true' then solution[(i + set[j-1])][j] is also 'true'.
                 * This is because if there exists a subset(with sum = 'i') of set formed by first 'j-1' elements
                 * (of given set) then inserting 'j'th element into that subset results in a new subset
                 * which will have sum = 'i' + value of 'j'th element. This sum is represented by the row = 'i'+set[j-1]'.
                 * Remember indexing scheme used here is 0 based and therefore value of 'j'th element
                 * is represented by set[j-1].
                 */
                if (solution[i][j - 1] == true) {
                    solution[i][j] = true;
                    if (((i + set[j - 1]) < numRows)) {
                        solution[(i + set[j - 1])][j] = true;
                    }
                }

                /*
                 * If solution[i][j] is not set to 'true' using above two rules
                 * then set it to 'false'. Because there is no other case other than
                 * above two cases in which solution[i][j] would be 'true'.
                 */
                else if (solution[i][j] != true) {
                    solution[i][j] = false;
                }

            }
        }

        // see if the 'requiredSum' could be obtained by any subset of the given set
        return solution[numRows - 1][numCols - 1];
    }

    public boolean partitionExists(int[] set) {
        int sum = 0;
        for (int i = 0; i < set.length; i++) {
            sum += set[i];
        }

        /*
         * if sum of all the elements in given set is odd,
         * there is no way they can be partitioned to have equal sum for both partitions
         */
        if ((sum % 2) != 0) {
            return false;
        }

        // else we just need to find a subset which has sum = (sum of all elements in the set)/2
        return partitionPossible(sum / 2, set);
    }


    public static void main(String[] args) {
        PartitionProblem solution = new PartitionProblem();

        int[] set = {7, 5, 3, 5};

        System.out.println(solution.partitionExists(set));
    }
}
