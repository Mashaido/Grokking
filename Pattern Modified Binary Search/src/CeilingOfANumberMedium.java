/*
Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’.
The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’.

Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.

Example 1:

Input: [4, 6, 10], key = 6
Output: 1
Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.

Example 2:

Input: [1, 3, 8, 10, 15], key = 12
Output: 4
Explanation: The smallest number greater than or equal to '12' is '15' having index '4'.

Example 3:

Input: [4, 6, 10], key = 17
Output: -1
Explanation: There is no number greater than or equal to '17' in the given array.

Example 4:

Input: [4, 6, 10], key = -1
Output: 0
Explanation: The smallest number greater than or equal to '-1' is '4' having index '0'.
 */

public class CeilingOfANumberMedium {
    public static int searchCeilingOfANumber(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;
        int tracker = lo + (hi-lo) /2;; // keep track of prev mid val

        while (lo <= hi) {
            int mid = lo + (hi-lo) /2;
            if (arr[mid] == key) {
                return mid;
            }
            // flipped sign from prev < to now >
            if (arr[tracker] < key && arr[mid] > key) {
                // tracker < supposed key location < arr[mid]
                return mid;
            }

            // flipped sign from prev > to now <
            if (arr[tracker] > key && arr[mid] < key) {
                // arr[mid] < supposed key location < tracker
                return tracker;
            }

            else if (arr[mid] > key) {
                // go left
                hi = mid-1;
                tracker = mid;
            }
            else if (arr[mid] < key) {
                // go right
                lo = mid+1;
                tracker = mid;
            }
        }

        if (arr[tracker] > key) {
            // supposed key location at beginning of array
            // don't consider the flip end since we're interested in ceiling only
            return tracker;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(searchCeilingOfANumber(new int[] { 4, 6, 10 }, 6));
        System.out.println(searchCeilingOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(searchCeilingOfANumber(new int[] { 4, 6, 10 }, 17));
        System.out.println(searchCeilingOfANumber(new int[] { 4, 6, 10 }, -1));
    }
}
