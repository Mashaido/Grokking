/*
Given a set of distinct numbers, find all of its permutations.

Example 1:

Input: [1,3,5]
Output: [1,3,5], [1,5,3], [3,1,5], [3,5,1], [5,1,3], [5,3,1]

*/

import java.util.*;

public class PermutationsMedium {
    public static List<List<Integer>> findPermutations0(int[] nums) {
//        Queue<List<Integer>> permutations = new PriorityQueue<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 1) {
            return res;
        }
        // add the empty permutation to permutations
        permutations.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int n = permutations.size();
            for (int j = 0; j < n; j++) {
                List<Integer> oldPermutation = permutations.poll();
                // create a new permutation by adding the current number at every position
                for (int k = 0; k <= oldPermutation.size(); k++) {
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    newPermutation.add(k, nums[i]);
                    if (newPermutation.size() == nums.length) {
                        res.add(newPermutation);
                    }
                    else {
                        permutations.add(newPermutation);
                    }
                }
            }
        }
        return res;
    }
    public static List<List<Integer>> findPermutations(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

//        res.add(new ArrayList<>());
        List<Integer> first = new ArrayList<>();
        first.add(arr[0]);
        res.add(first);
        for (int i = 1; i < arr.length; i++) {
            int resLen = res.size();
            for (int j = 0; j < resLen; j++) {
                int copyLen = res.get(j).size();
                for (int k = 0; k <= copyLen; k++) {
                    List<Integer> copy = new ArrayList<>(res.get(j));
                    copy.add(k, arr[i]);
                    res.add(copy);

                    if (copy.size() == arr.length) {
                        result.add(copy);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findPermutations(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }
}
