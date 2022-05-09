/*
Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any contiguous subarray of
size ‘k’.

Example 1:

Input: [2, 1, 5, 1, 3, 2], k=3
Output: 9
Explanation: Subarray with maximum sum is [5, 1, 3].

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaximumSumSubarrayOfSizeKEasy {

     /*

    Technique: maintain a max heap together with sliding window
    Time: O(n)
    Space: O(1) no additional space

    Notes:
    -take note of Double vs Integer in results

     */
    public static int findMaximumSum(int k, int[] arr) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        if (k >= arr.length) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        maxHeap.add(sum);
        sum -= arr[0];

        int i = 1;
        int j = k + i -1;
        while (j < arr.length) {
            sum += arr[j];
            maxHeap.add(sum);
            sum -= arr[i];
            i++;
            j++;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int result = findMaximumSum(3, new int[] { 2, 1, 5, 1, 3, 2 });
        System.out.println("Subarray with maximum sum is: " + result);
        int result2 = findMaximumSum(2, new int[] { 2, 3, 4, 1, 5 });
        System.out.println("Subarray with maximum sum is: " + result2);
    }
}
