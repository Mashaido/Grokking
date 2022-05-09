/*
Given a sorted array, create a new array containing squares of all the number of the input array
in the sorted order.

Example 1:

Input: [-2, -1, 0, 2, 3]
Output: [0, 1, 4, 4, 9]

Example 2:

Input: [-3, -1, 0, 1, 2]
Output: [0 1 1 4 9]

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SquaringASortedArrayEasy {
    /*
    Technique: defn not 2 pointer!
    Time: O(nlogn)
    Space: O(n) additional space
     */
    public static List<Integer> squareArray(int[] nums) {
        List<Integer> squared = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            squared.add((int) Math.pow(nums[i], 2));
        }
        Collections.sort(squared);
        return squared;
    }

    /*
    Technique: 2 pointer!
    Time: O(n)
    Space: O(n) additional space
     */
    public static int[] makeSquares(int[] nums) {
        int i = 1;
        int j = 2;
        int[] res = new int[nums.length];
        int ptr = 0;
        while (i >= 0 && j < nums.length) {
            double iSquare = Math.pow(nums[i], 2);
            double jSquare = Math.pow(nums[j], 2);
            if (iSquare == jSquare) {
                res[ptr++] = (int) iSquare;
                res[ptr++] = (int) jSquare;
                i--;
                j++;
            }
            else if (iSquare < jSquare) {
                res[ptr++] = (int) iSquare;
                i--;
            }
            else if (jSquare < iSquare) {
                res[ptr++] = (int) jSquare;
                j++;
            }
        }
        if (j < nums.length) {
            while (j < nums.length) {
                res[ptr++] = (int) Math.pow(nums[j], 2);
                j++;
            }
        }
        if (i >= 0) {
            while (i >= 0) {
                res[ptr++] =  (int) Math.pow(nums[i], 2);
                i--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> result = squareArray(new int[]{-2, -1, 0, 2, 3});
        System.out.print("The squared result is: " + result + "\n");

        List<Integer> result1 = squareArray(new int[]{-3, -1, 0, 1, 2});
        System.out.print("The squared result is: " + result1 + '\n' + '\n');

        int[] result2 = makeSquares(new int[]{-2, -1, 0, 2, 3});
        System.out.print("The squared result is: " + Arrays.toString(result2) + "\n");

        int[] result3 = makeSquares(new int[]{-3, -1, 0, 1, 2});
        System.out.print("The squared result is: " + Arrays.toString(result3) + '\n' + '\n');
    }
}
