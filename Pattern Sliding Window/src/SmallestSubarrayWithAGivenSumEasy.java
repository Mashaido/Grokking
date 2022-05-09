/*
Given an array of positive numbers and a positive number ‘S,’ find the length of the smallest contiguous subarray
whose sum is greater than or equal to ‘S’. Return 0 if no such subarray exists.

Example 1:

Input: [2, 1, 5, 2, 3, 2], S=7
Output: 2
Explanation: The smallest subarray with a sum greater than or equal to '7' is [5, 2].

 */

import java.util.Arrays;

public class SmallestSubarrayWithAGivenSumEasy {
    /*

    Technique: use dp matrix to compute and store dfs sums
    Time: O(nxn)
    Space: O(nxn) additional space

     */

    public static int minSizeSubArraySum(int s, int[] arr) {
        int len = arr.length;
        int rows = len;
        int cols = len;
        int[][] dp = new int[rows][cols];

        for (int col = 0; col < cols; col++) {
            dp[0][col] = arr[col];
        }
        for (int col = 0; col < cols-1; col++) {
            int ptr = col + 1;
            for (int row = 1; row < rows; row++) {
                if (ptr == len) {
                    // out of bounds
                    while (row < rows) {
                        dp[row][col] = 0;
                        row++;
                    }
                }
                else {
                    dp[row][col] = dp[row-1][col] + arr[ptr];
                    ptr++;
                }
            }
        }
        for (int row = 1; row < rows; row++) {
            dp[row][cols-1] = 0;
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (dp[row][col] >= s) {
                    return row + 1;
                }
            }
        }
        // otw
        return 0;
    }

    /*

    Technique: use sliding window
    Time: O(n + n) => O(n)
    Space: O(1) no additional space

    */

    public static int minSizeSubArraySumOptimized(int s, int[] arr) {
        int len = Integer.MAX_VALUE;
        int sum = 0;
        int windowStart = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            while (sum >= s) {
                // record len
                len = Math.min(len, i-windowStart+1);
                // adjust window from left side
                sum -= arr[windowStart];
                windowStart++;
            }

        }
        return len;
    }

    public static void main(String[] args) {
        int sum = 7;
        int result = minSizeSubArraySumOptimized(sum, new int[] { 2, 1, 5, 2, 3, 2 });
        System.out.printf("\n The smallest subarray with a sum greater than or equal to %d is of length %d" , sum, result);

        int result1 = minSizeSubArraySumOptimized(sum, new int[] { 2, 1, 5, 2, 8 });
        System.out.printf("\n The smallest subarray with a sum greater than or equal to %d is of length %d" , sum, result1);

        int sum1 = 8;
        int result2 = minSizeSubArraySumOptimized(sum1, new int[] { 3, 4, 1, 1, 6 });
        System.out.printf("\n The smallest subarray with a sum greater than or equal to %d is of length %d" , sum1, result2);

    }
}
