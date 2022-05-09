/*
Given an array of unsorted numbers, find all unique triplets in it that add up to zero.

Example 1:

Input: [-3, 0, 1, 2, -1, 1, -2]
Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
Explanation: There are four unique triplets whose sum is equal to zero.

Example 2:

Input: [-5, 2, -1, -2, 3]
Output: [[-5, 2, 3], [-2, -1, 3]]
Explanation: There are two unique triplets whose sum is equal to zero.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletSumToZeroMedium {

    /*
    Technique: defn not 2 pointer! brute force
    Time: O(n**3)
    Space:
     */
    public static List<List<Integer>> tripletSumZero(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i+1; j < nums.length - 1; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        triplets.add(triplet);
                    }
                }
            }
        }
        return triplets;
    }
    /*
    Technique: two pointers duuh!
    Time: O(n^2)
    Space: O(N) additional space for sorting and another O(n) for output array
    NOTES: DOESN'T HANDLE DUPLICATES YET
    */
    public static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr); // nlogn time
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int target = -arr[i];
            int[] temp = PairWithTargetSumEasy.findPairTwoPtrs(Arrays.copyOfRange(arr, i+1, arr.length), target);
            if (temp[0] != Integer.MIN_VALUE && temp[1] != Integer.MAX_VALUE) {
                List<Integer> r = new ArrayList<>();
                r.add(arr[i]);
                r.add(temp[0]);
                r.add(temp[1]);
                res.add(r);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = tripletSumZero(new int[]{-3, 0, 1, 2, -1, 1, -2});
        System.out.print("The triplets are: " + result + "\n");

        List<List<Integer>> result1 = tripletSumZero(new int[]{-5, 2, -1, -2, 3});
        System.out.print("The triplets are: " + result1 + '\n' + '\n');

        List<List<Integer>> result2 = searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2});
        System.out.print("The triplets are: " + result2 + "\n");

        List<List<Integer>> result3 = searchTriplets(new int[]{-5, 2, -1, -2, 3});
        System.out.print("The triplets are: " + result3 + '\n' + '\n');
    }
}
