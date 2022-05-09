/*
Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the
given target.

Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the
given target.

Example 1:

Input: [1, 2, 3, 4, 6], target=6
Output: [1, 3]
Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6

Example 2:

Input: [2, 5, 9, 11], target=11
Output: [0, 2]
Explanation: The numbers at index 0 and 2 add up to 11: 2+9=11
*/

import java.util.*;

/*
    Technique: linear search a patner for each number
    Time: O(n**2)
    Space: O(2) additional space
 */
public class PairWithTargetSumEasy {
    public static List<Integer> findPair(int[] nums, int target) {
        List<Integer> pair = new ArrayList<>();
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    pair.add(i);
                    pair.add(j);
                    return pair;
                }
            }
        }
        return pair;
    }

    /*
    Technique: binary search
    Time: O(nlogn)
    Space: O(2) additional space
     */
    public static List<Integer> findPairBinarySearch(int[] nums, int target) {
        List<Integer> pair = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int lo = i+1;
            int hi = nums.length - 1;
            while (lo <= hi) {
                int mid = (hi+lo)/2;
                if (target - nums[i] == nums[mid]) {
                    pair.add(i);
                    pair.add(mid);
                    return pair;
                }
                else if (target - nums[i] > nums[mid]) {
                    // go left
                    lo = mid + 1;
                }
                else {
                    // go right
                    hi = mid - 1;
                }
            }
        }
        return pair;
    }

    /*
    Technique: hash table
    Time: O(n)
    Space: O(n) + O(2) additional space worst case scenario
     */
    public static List<Integer> findPairHashtable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> pair = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (!map.containsKey(complement)) {
                map.put(nums[i], i);
            }
            else {
                pair.add(map.get(complement));
                pair.add(i);
                return pair;
            }
        }
        return pair;
    }
    /*
    Technique: two pointers duuh!
    Time: O(n)
    Space: no additional space
    */
    public static int[] findPairTwoPtrs(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while(i < j) {
            int sum = nums[i] + nums[j];
            if (sum > target) {
                // decrement j ptr
                j--;
            }
            else if (sum < target) {
                // increment i ptr
                i++;
            }
            else {
                // uncomment for PairWithTargetSumeasy
//                return new int[]{i,j};
                // only for TripletSumToZeroMedium
                return new int[] {nums[i], nums[j]};
            }
        }
        return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
    }

    public static void main(String[] args) {
        List<Integer> result = findPair(new int[] { 1, 2, 3, 4, 6 },  6);
        System.out.print("Here are the indices of findPair: " + result + "\n");

        List<Integer> result0 = findPairBinarySearch(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.print("Here are the indices of findPairBinarySearch: " + result0 + '\n');

        List<Integer> result4 = findPairHashtable(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.print("Here are the indices of findPairHashtable: " + result4 + '\n');

        int[] result5 = findPairTwoPtrs(new int[] { 1, 2, 3, 4, 6 },  6);
        System.out.print("Here are the indices of findPair: " + Arrays.toString(result5) + "\n"+ '\n');

        List<Integer> result1 = findPair(new int[] { 2, 5, 9, 11 },  11);
        System.out.print("Here are the indices of findPair: " + result1 + "\n");

        List<Integer> result2 = findPairBinarySearch(new int[] { 2, 5, 9, 11 }, 11);
        System.out.print("Here are the indices of findPairBinarySearch: " + result2 + '\n');

        List<Integer> result3 = findPairHashtable(new int[] { 2, 5, 9, 11 }, 11);
        System.out.print("Here are the indices of findPairHashtable: " + result3 + "\n");

        int[] result6 = findPairTwoPtrs(new int[] { 2, 5, 9, 11 },  11);
        System.out.print("Here are the indices of findPair: " + Arrays.toString(result6));
    }
}
