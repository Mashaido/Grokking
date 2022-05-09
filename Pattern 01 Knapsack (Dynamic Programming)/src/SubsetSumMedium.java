/*
Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.

Example 1:#
Input: {1, 2, 3, 7}, S=6
Output: True
The given set has a subset whose sum is '6': {1, 2, 3}

Example 2:#
Input: {1, 2, 7, 1, 5}, S=10
Output: True
The given set has a subset whose sum is '10': {1, 2, 7}

Example 3:#
Input: {1, 3, 4, 8}, S=6
Output: False
The given set does not have any subset whose sum is equal to '6'.
 */

public class SubsetSumMedium {
    static int[][] dp = new int[4][4];

    public static boolean canPartitionrecursive(int[] nums, int sum) {
        // base conditions
        if (nums.length == 0 || sum == 0) {
            return false;
        }

        return canPartitionrecursive(nums, sum, 0);
    }

    public static  boolean canPartitionrecursive(int[] nums, int remainingSum, int idx) {

        // I ... base case at leaf
        if (idx == nums.length-1) {
            // with or w/o
            return remainingSum-nums[idx] == 0 || remainingSum == 0;
        }

        // II ... or recursive steps ... with or w/o this num in subset path
        return canPartitionrecursive(nums, remainingSum-nums[idx], idx+1) || canPartitionrecursive(nums, remainingSum, idx+1);
    }



    public static void main(String[] args) {
        int[] num = { 1, 2, 3, 7 };
        System.out.println(canPartitionrecursive(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(canPartitionrecursive(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(canPartitionrecursive(num, 6));
    }
}
