/*
Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack with a
capacity ‘C.’ The goal is to get the maximum profit out of the knapsack items. Each item can only be
selected once, as we don’t have multiple quantities of any item.

Let’s take Merry’s example, who wants to carry some fruits in the knapsack to get maximum profit.
Here are the weights and profits of the fruits:

Items: { Apple, Orange, Banana, Melon }
Weights: { 2, 3, 1, 4 }
Profits: { 4, 5, 3, 7 }
Knapsack capacity: 5

Let’s try to put various combinations of fruits in the knapsack, such that their total weight is not
more than 5:

Apple + Orange (total weight 5) => 9 profit
Apple + Banana (total weight 3) => 7 profit
Orange + Banana (total weight 4) => 8 profit
Banana + Melon (total weight 5) => 10 profit

This shows that Banana + Melon is the best combination as it gives us the maximum profit, and the total
weight does not exceed the capacity.

Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset of these
items which will give us maximum profit such that their cumulative weight is not more than a given number ‘C.’ Each item can only be selected once, which means either we put an item in the knapsack or we skip it.
 */

import java.util.*;

public class Knapsack01Medium {
    public static Map<Integer, Integer> memo = new HashMap<>();
    public static Map<Integer, Integer> tab = new HashMap<>();

    public static int[][] array = new int[5][8];

    /*
    Technique: Recursion
    Time: O(2**N)
    Space: O(N) recursion stack
     */
    public static int recursiveKnapsack(int[] profits, int[] weights, int capacity) {
        // maximum of with or without this particular choice down the tree -2**N where N is the length of weights
        // starts from the end of arrays btw
        return Math.max(
                recursiveKnapsack(profits, weights, capacity - weights[weights.length-1],
                        weights.length-2, profits[profits.length-1]),
                recursiveKnapsack(profits, weights, capacity, weights.length-2, 0));
    }

    public static int recursiveKnapsack(int[] profits, int[] weights, int remaining, int idx, int profit) {
        // base case - right after leaf node with or w/o the leaf node right above
        if (idx == -1) {
            // process if valid cumulative weight
            if (remaining >= 0 ) {
                return profit;
            }
            // weight not valid at all down this path
            return 0;
        }

        // recursive steps
        return Math.max(
                // maximum of with or without this particular choice down the tree
                recursiveKnapsack(profits, weights, remaining - weights[idx],
                        idx-1, profit + profits[idx]),
                recursiveKnapsack(profits, weights, remaining, idx-1, profit));
    }


    /*
   Technique: DP memoization
   Time: O(N*C) where C is the knapsack capacity
   Space: O(N*C) for memo array and O(N) for the recursion stack

   NOTES - since my answr (Max Profit) depends on two variables/states (bag capacity left and number of items
   or profit on left items)
   save those in memory so need to maintain two states

   i.e need to store results for every possible index i and remaining capacity c (or used-up capacity out of total capacity).
   note these are the two changing values use our 2d array.
   much clearer to draw the decision tree and observe for overlapping problems, to build intuition about how to memo
   the problem that way!
    */
    public static int memoizationKnapsack(int[] profits, int[] weights, int capacity) {
        // maximum of with or without this particular choice down the tree -2**N where N is the length of weights
        // starts from the end of arrays btw
        return Math.max(
                memoizationKnapsack(profits, weights, capacity - weights[weights.length-1],
                        weights.length-2, profits[profits.length-1], capacity),
                memoizationKnapsack(profits, weights, capacity, weights.length-2, 0, capacity));
    }

    public static int memoizationKnapsack(int[] profits, int[] weights, int remaining, int idx, int profit, int capacity) {
        // check if in our memoization table
        // **** assert idx != -1 i.e not at leaf and always valid --> check diagram in book
        // dp[idx_item][with_valid_capacity_filled_by_this_point]
        if (idx >= 0 && remaining >= 0 && array[idx][capacity-remaining] != 0) {
            return array[idx][capacity-remaining];
        }

        // ** else not in memo DP so have to put after calculating max value for this idx **

        // base case - right after leaf node with or w/o the leaf node right above
        if (idx == -1) {
            // process if valid cumulative weight
            if (remaining >= 0 ) {
                return profit;
            }
            // weight not valid at all down this path
            return -1;
        }

        // recursive steps
        // recursive steps
        int max = Math.max(
                // maximum of with or without this particular choice down the tree
                memoizationKnapsack(profits, weights, remaining - weights[idx],
                        idx-1, profit + profits[idx], capacity),
                memoizationKnapsack(profits, weights, remaining, idx-1, profit, capacity));
        if (remaining >= 0) {
            array[idx][capacity-remaining] = max;
        }

        return max;

    }


    public static void main(String[] args) {
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int[] profits2 = {4, 5, 3, 7};
        int[] weights2 = {2, 3, 1, 4};
        System.out.println("__Recursive__ " );
        int maxProfit = recursiveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = recursiveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = recursiveKnapsack(profits2, weights2, 5);
        System.out.println("Total knapsack profit ---> " + maxProfit);


        System.out.println(" " );
        System.out.println("__Memoization__ " );
        int maxProfitMemo = memoizationKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfitMemo);
//        Arrays.fill(array, 0);
        array = new int[5][8];
        maxProfitMemo = memoizationKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfitMemo);
        array = new int[5][8];
        maxProfitMemo = memoizationKnapsack(profits2, weights2, 5);
        System.out.println("Total knapsack profit ---> " + maxProfitMemo);

        System.out.println(" " );
        System.out.println("__Tabulation__ " );

    }
}
