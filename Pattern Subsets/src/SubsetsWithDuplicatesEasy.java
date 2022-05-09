/*
Given a set of numbers that might contain duplicates, find all of its distinct subsets.

Example 1:

Input: [1, 3, 3]
Output: [], [1], [3], [1,3], [3,3], [1,3,3]

Example 2:

Input: [1, 5, 3, 3]
Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3], [3,3], [1,3,3], [3,3,5], [1,5,3,3]

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDuplicatesEasy {

    /*
    same as SubsetsEasy just sort nums at the beginning and when appending new copies check if unique
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> emptySubset = new ArrayList<>();
        // add the empty subset first
        subsets.add(emptySubset);
        result.add(emptySubset);
        // for each elt add it to existing elt in subsets as we expand it to new ones
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int n = subsets.size();
            // NOTE THAT OUT OF MEMORY ERROR IF USING j < subsets.size()
            for (int j = 0; j < n; j++) {
                // create new entries by adding to prev ones
                // do this by making copies of prev ones then adding to them current number
                List<Integer> copy = new ArrayList<>(subsets.get(j));
                copy.add(current);
                subsets.add(copy);
                if (subsets.size() == 2) {
                    result.add(copy);
                }
                if (subsets.size() == 2 && !copy.equals(subsets.get(j))) {
                    result.add(copy);
                }
                if (subsets.size() > 2 && !copy.equals(subsets.get(j-1))) {
                    System.out.println("her");
                    result.add(copy);
                }
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetsWithDuplicatesEasy.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetsWithDuplicatesEasy.findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

    }
}
