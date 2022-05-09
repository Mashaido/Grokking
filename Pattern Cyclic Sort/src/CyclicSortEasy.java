/*
We are given an array containing n objects. Each object, when created, was assigned a unique number from the
range 1 to n based on their creation sequence. This means that the object with sequence number 3 was created
just before the object with sequence number 4.

Write a function to sort the objects in-place on their creation sequence number in O(n) and without using
any extra space. For simplicity, let’s assume we are passed an integer array containing only the sequence
numbers, though each number is actually an object.
 */

import java.util.Arrays;

public class CyclicSortEasy {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i+1) {
                swap(arr, i, arr[i]-1);
                System.out.println("hj");
//                while (arr[i] != i+1) {
//                    swap(arr, i, arr[i]-1);
//                }
            }
        }
    }

    private static void swap(int[] arr, int thisIdx, int thatIdx) {
        int thisNum = arr[thisIdx];
        int thatNum = arr[thatIdx];
        int temp = thisNum;
        thisNum = thatNum;
        thatNum = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 3, 1, 5, 4, 2 };
        sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }
}
