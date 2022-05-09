/*
Given an array of characters where each character represents a fruit tree, you are given two baskets, and your goal
is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one
type of fruit.

You can start with any tree, but you can’t skip a tree once you have started. You will pick one fruit from each
tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.

Write a function to return the maximum number of fruits in both baskets.

Example 1:

Input: Fruit=['A', 'B', 'C', 'A', 'C']
Output: 3
Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']

Example 2:

Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
Output: 5
Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']

*/

import java.util.HashMap;
import java.util.Map;

public class FruitsIntoBasketsMedium {

    public static int findLength(char[] arr) {
        int maxCounter = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        int leftptr = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i])+1);
                int valueCount = 0;
                for (int val: map.values()) {
                    valueCount += val;
                }
                maxCounter = Math.max(maxCounter, valueCount);
            }
            else {
                if (map.size() == 2) {
                    int valueCount = 0;
                    for (int val: map.values()) {
                        valueCount += val;
                    }
                    maxCounter = Math.max(maxCounter, valueCount);
                    // adjust window by one elt right
                    map.remove(arr[i-2]);
                    map.put(arr[i], 1);
                }
                else {
                    map.put(arr[i], 1);
                    int valueCount = 0;
                    for (int val: map.values()) {
                        valueCount += val;
                    }
                    maxCounter = Math.max(maxCounter, valueCount);
                }
            }
        }
        return maxCounter;
    }

    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " + findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " + findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }

}
