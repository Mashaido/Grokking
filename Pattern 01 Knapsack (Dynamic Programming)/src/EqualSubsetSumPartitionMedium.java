/*
Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both
subsets is equal.

Example 1:

Input: {1, 2, 3, 4}
Output: True
Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
Example 2:

Input: {1, 1, 3, 4, 7}
Output: True
Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
Example 3:

Input: {2, 3, 4, 6}
Output: False
Explanation: The given set cannot be partitioned into two subsets with equal sum.
 */

import java.util.HashMap;
import java.util.Map;

public class EqualSubsetSumPartitionMedium {

    public static Map<Integer, Boolean> map = new HashMap<>();

    /*
    Technique: Recursion
    Time: O(2**N)
    Space: O(N) recursion stack
     */
    public static boolean canPartitionrecursive(int[] nums) {
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        int halfSum = sum/2;

        // edge cases
        if (nums.length < 2 || sum % 3 == 0) {
            return false;
        }
        return canPartitionrecursive(nums, 0, 0, halfSum);
    }

    public static boolean canPartitionrecursive(int[] nums, int idx, int sum, int halfSum) {
        // base case at leaf
        if (idx == nums.length - 1) {
            return sum+nums[idx] == halfSum || sum == halfSum;
        }

        // recursive steps
        // with or w/0 this item in cumulative sum i.e add num to sum path or not
        return canPartitionrecursive(nums, idx+1, sum+nums[idx], halfSum) || canPartitionrecursive(nums, idx+1, sum, halfSum);
    }

    /*
   Technique: Memoization
   Time: O(N)
   Space: O(N) recursion stack

   NOTE that once you draw and observe the decision tree diag, you notice that the right hand side is a complete
   complement of the left hand side of the entire tree from root node to leaves.
   therefore use a dp[] to track these seen boolean values
    */
//    public static boolean canPartitionmemoization(int[] nums) {
//        int sum = 0;
//        for (int num: nums) {
//            sum += num;
//        }
//        int halfSum = sum/2;
//
//        // edge cases
//        if (nums.length < 2 || sum % 3 == 0) {
//            return false;
//        }
//        return canPartitionmemoization(nums, 0, 0, halfSum);
//    }
//
//    public static boolean canPartitionmemoization(int[] nums, int idx, int sum, int halfSum) {
//        // if in mem return
//        if (map.containsKey(idx)) {
//            return map.get(idx);
//        }
//        // base case at leaf
//        boolean bool;
//        if (idx == nums.length - 1) {
//            bool = sum+nums[idx] == halfSum || sum == halfSum;
////            map.put(idx, bool);
//            return bool;
//        }
//
//        // recursive steps
//        // with or w/0 this item in cumulative sum i.e add num to sum path or not
//        bool = canPartitionmemoization(nums, idx+1, sum+nums[idx], halfSum) || canPartitionmemoization(nums, idx+1, sum, halfSum);
//        map.put(idx, bool);
//        return bool;
//    }


    public static void main(String[] args) {
        EqualSubsetSumPartitionMedium ps = new EqualSubsetSumPartitionMedium();
        System.out.println("__Recursive__ " );
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartitionrecursive(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartitionrecursive(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartitionrecursive(num));

        System.out.println(" " );
        System.out.println("__Memoization__ " );
        num = new int[]{1, 2, 3, 4};
//        System.out.println(ps.canPartitionmemoization(num));
//        map.clear();
//        num = new int[]{1, 1, 3, 4, 7};
//        System.out.println(ps.canPartitionmemoization(num));
//        map.clear();
//        num = new int[]{2, 3, 4, 6};
//        System.out.println(ps.canPartitionmemoization(num));

    }
}
