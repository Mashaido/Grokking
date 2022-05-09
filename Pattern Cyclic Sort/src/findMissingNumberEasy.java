/*
We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. Since the array has only ‘n’
numbers out of the total ‘n+1’ numbers, find the missing number.
 */

import java.util.PriorityQueue;

public class findMissingNumberEasy {

    public static int findMissingNumber(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int val: arr) {
            minHeap.add(val);
        }
        int tracker = minHeap.peek();
        int res = 0;
        while (!minHeap.isEmpty()) {
            res = minHeap.poll();
            if (res != tracker) {
                System.out.println("res: " + res);
                return res;
            }
            tracker++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findMissingNumber(new int[] { 4, 0, 3, 1 }));
        System.out.println(findMissingNumber(new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
    }
}
