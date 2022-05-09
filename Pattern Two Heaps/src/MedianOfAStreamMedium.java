/*
Design a class to calculate the median of a number stream. The class should have the following two methods:

insertNum(int num): stores the number in the class
findMedian(): returns the median of all numbers inserted in the class
If the count of numbers inserted in the class is even, the median will be the average of the middle two
numbers.

Example 1:
1. insertNum(3)
2. insertNum(1)
3. findMedian() -> output: 2
4. insertNum(5)
5. findMedian() -> output: 3
6. insertNum(4)
7. findMedian() -> output: 3.5

 */

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfAStreamMedium {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public void insertNum(int num) {
        if (minHeap.isEmpty()) {
                minHeap.add(num);
        }
        else {
            int compMin = minHeap.peek();
            if (num >= compMin) {
                minHeap.add(num);
            }
            else {
                maxHeap.add(num);
            }
        }
        rebalance(minHeap, maxHeap);
    }

    public double findMedian() {
        if (minHeap == null || maxHeap == null) {
            return -1;
        }
        if (minHeap.size() == maxHeap.size()) {
            // return average of top elts in each
            return (minHeap.peek() + maxHeap.peek())/2.0;
        }
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }
        return maxHeap.peek();
    }

    public void rebalance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (minHeap != null && minHeap.size() - maxHeap.size() > 1) {
            while (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.add(minHeap.poll());
            }
        }
    }

    public static void main(String[] args) {
        MedianOfAStreamMedium medianOfAStream = new MedianOfAStreamMedium();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}
