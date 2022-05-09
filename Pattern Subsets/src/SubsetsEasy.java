/*
Given a set with distinct elements, find all of its distinct subsets.

Example 1:

Input: [1, 3]
Output: [], [1], [3], [1,3]

Example 2:

Input: [1, 5, 3]
Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]

 */

import java.util.ArrayList;
import java.util.List;

public class SubsetsEasy {

    // NOTES
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> emptySubset = new ArrayList<>();
        // add the empty subset first
        subsets.add(emptySubset);
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
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetsEasy.findSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetsEasy.findSubsets(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetsEasy.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
