/*
Given an array of sorted numbers, remove all duplicates from it.
You should not use any extra space; after removing the duplicates in-place return the new length
of the array.


Example 1:

Input: [2, 3, 3, 3, 6, 9, 9]
Output: 4
Explanation: The first four elements after removing the duplicates will be [2, 3, 6, 9].

Example 2:

Input: [2, 2, 2, 11]
Output: 2
Explanation: The first two elements after removing the duplicates will be [2, 11].
*/

import java.util.HashMap;
import java.util.List;

public class RemoveDuplicatesEasy {
    /*
    Technique: 2 pointer
    Time: O(n)
    Space: O(1) no additional space
    NOTES: TAKE ADVANTAGE OF SORTED ARRAY TO DO THE COMPL OF "IF THIS IS NOT EQ TO THE NEXT"
 */
    public static int removeDuplicates(int[] nums) {
        int len = 0;

        // edge cases
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 0;
        int j = i+1;
        while (i < nums.length-1 && j < nums.length) {
            if (nums[j] != nums[i]) {
                len++;
            }
            i++;
            j++;
        }
        len++;
        return len;
    }

    public static int removeDuplicatesHT(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            }
            else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        return map.size();
    }

    public static int removeDuplicatesHTSimplfd(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }
        return map.size();
    }

    public static void main(String[] args) {
        int result = removeDuplicates(new int[]{2, 3, 3, 3, 6, 9, 9});
        System.out.print("The remaining length is: " + result + "\n");

        int result0 = removeDuplicates(new int[]{2, 2, 2, 11});
        System.out.print("The remaining length is: " + result0 + '\n');

        int result1 = removeDuplicates(new int[]{1, 2, 3, 4, 6});
        System.out.print("The remaining length is: " + result1 + '\n' + '\n');

        int result2 = removeDuplicatesHT(new int[]{2, 3, 3, 3, 6, 9, 9});
        System.out.print("The remaining length is: " + result2 + "\n");

        int result3 = removeDuplicatesHT(new int[]{2, 2, 2, 11});
        System.out.print("The remaining length is: " + result3 + '\n');

        int result4 = removeDuplicatesHT(new int[]{1, 2, 3, 4, 6});
        System.out.print("The remaining length is: " + result4 + '\n' + '\n');

        int result5 = removeDuplicatesHTSimplfd(new int[]{2, 3, 3, 3, 6, 9, 9});
        System.out.print("The remaining length is: " + result5 + "\n");

        int result6 = removeDuplicatesHTSimplfd(new int[]{2, 2, 2, 11});
        System.out.print("The remaining length is: " + result6 + '\n');

        int result7 = removeDuplicatesHTSimplfd(new int[]{1, 2, 3, 4, 6});
        System.out.print("The remaining length is: " + result7 + '\n' + '\n');


    }
}
