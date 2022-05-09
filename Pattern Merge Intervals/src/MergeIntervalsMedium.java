/*

Given a list of intervals, merge all the overlapping intervals to produce a list that has only
mutually exclusive intervals.

Example 1:

Intervals: [[1,4], [2,5], [7,9]]
Output: [[1,5], [7,9]]
Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into
one [1,5].

Example 2:
Intervals: [[6,7], [2,4], [5,9]]
Output: [[2,4], [5,9]]
Explanation: Since the intervals [6,7] and [5,9] overlap, we merged them into one [5,9].

Example 3:
Intervals: [[1,4], [2,6], [3,5]]
Output: [[1,6]]
Explanation: Since all the given intervals overlap, we merged them into one.

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeIntervalsMedium {

    /*
    Technique:
    Time: O(nlogn)
    Space: O(n) additional space
     */
    public static List<Interval> merge_old(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }
        // sort intervals by start time
        Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
        List<Interval> merged = new ArrayList<>();

        int pairPtr = 0;
        while (pairPtr < intervals.size()) {
            int startA = intervals.get(pairPtr).start;
            int endA = intervals.get(pairPtr).end;

            int nextPairPtr = pairPtr + 1;
            Interval interval = new Interval(startA, endA);
            while (nextPairPtr < intervals.size()){
                int startB = intervals.get(nextPairPtr).start;
                int endB = intervals.get(nextPairPtr).end;
                // check if interval B starts before end of interval A
                if (startB < endA) {
                    interval.end = Math.max(endA, endB);
                    endA = interval.end;
                    pairPtr++;
                }
                nextPairPtr++;
            }
            merged.add(interval);
            pairPtr++;
        }
        return merged;
    }

    public static int[][] merge(int[][] arr) {
        int[][] res = new int[arr.length][2];
        int resPtr = 0;
        int pairPtr = 0;
        while (pairPtr < arr.length) {
            int startPrevElt = arr[pairPtr][0];
            int endPrevElt = arr[pairPtr][1];
            int temp = resPtr;

            int nextPairPtr = pairPtr + 1;

            while (nextPairPtr < arr.length) {
                int startCurrElt = arr[nextPairPtr][0];
                int endCurrElt = arr[nextPairPtr][1];

                if (startCurrElt <= startPrevElt && endCurrElt >= endPrevElt) {
                    // curr start && end takes precedence
                    // prev completely swallowed by curr
                    res[resPtr][0] = startCurrElt;
                    res[resPtr][1] = endCurrElt;
                    resPtr++;
//                    nextPairPtr++;
                }
                else if (startCurrElt > startPrevElt && endCurrElt < endPrevElt) {
                    // curr completely swallowed by prev
                    res[resPtr][0] = startPrevElt;
                    res[resPtr][1] = endPrevElt;
                    resPtr++;
//                    nextPairPtr++;
                }
                else if (startCurrElt <= startPrevElt && endCurrElt <= endPrevElt) {
                    // curr slid over to prev's left, one-sided
                    res[resPtr][0] = startCurrElt;
                    res[resPtr][1] = endPrevElt;
                    resPtr++;
//                    nextPairPtr++;
                }
                else if (startCurrElt > startPrevElt && startCurrElt < endPrevElt && endCurrElt > endPrevElt) {
                    // curr slid over to prev's right, one-sided
//                    System.out.println(resPtr + "prev is " + startPrevElt);
                    res[resPtr][0] = startPrevElt;
                    res[resPtr][1] = endCurrElt;
                    System.out.println(Arrays.deepToString(res));

                    resPtr++;
                    nextPairPtr++;
                }
//                 nextPairPtr++;
            }
            // else no overlap in prev pair with any next pair
            if (temp == resPtr++) {
                // record non-overlap prev pair
                res[resPtr][0] = arr[pairPtr][0];
                res[resPtr][1] = arr[pairPtr][1];
                // to avoid incrementing pairPtr twice i.e only if it hasn't gone through while loop
                pairPtr++;
                resPtr++;
            }
        }

        return res;
    }

    public static List<Interval> merge(Interval[] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
        List<Interval> merged = new ArrayList<>();
        int i = 0;
        int j = i+1;
        while (i < intervals.length - 1 && j < intervals.length) {
            Interval a = intervals[i];
            Interval b = intervals[j];
            if (b.start <= a.end) {
                // push further looking for furthest right overlapping intv
                int x = j;
                int y = j+1;
                while (x < intervals.length - 1 && y < intervals.length && intervals[y].start <= intervals[x].end) {
                    // keep incrementing first
                    x++;
                    y++;
                }
                if (y != j+1) {
                    j = x;
                }
                mergeIntervals(intervals[i], intervals[j], merged);
                i++;
                j++;

            }
            else {
                merged.add(a);
                i++;
                j++;
                System.out.println(i);

            }
        }


        // last ith and jth
//        Interval a = intervals[i];
//        Interval b = intervals[j];
        if (intervals[j].start <= intervals[i].end) {
            mergeIntervals(intervals[i], intervals[j], merged);
        }
        else {
            merged.add(intervals[i]);
            merged.add(intervals[j]);
        }

        return merged;
    }

    private static void mergeIntervals(Interval a, Interval b, List<Interval> merged) {
        Interval m = new Interval();
        m.start = a.start;

        if (a.end >= b.end) {
            // 'a' completely swallows 'b'
            m.end = a.end;
        }
        else {
            // b's end surpasses a's
            m.end = b.end;
        }
        merged.add(m);
    }

    public static void main(String[] args) {

//        List<Interval> intervals = new ArrayList<>();
//        intervals.add(new Interval (1,4));
//        intervals.add(new Interval(2,5));
//        intervals.add(new Interval(7,9));
//        System.out.print("Merged intervals: ");
//        for (Interval interval : MergeIntervalsMedium.merge_old(intervals))
//            System.out.print("[" + interval.start + "," + interval.end + "] ");
//        System.out.println();
//
//        intervals = new ArrayList<>();
//        intervals.add(new Interval (6,7));
//        intervals.add(new Interval(2,4));
//        intervals.add(new Interval(5,9));
//        System.out.print("Merged intervals: ");
//        for (Interval interval : MergeIntervalsMedium.merge_old(intervals))
//            System.out.print("[" + interval.start + "," + interval.end + "] ");
//        System.out.println();
//
//        intervals = new ArrayList<>();
//        intervals.add(new Interval (1,4));
//        intervals.add(new Interval(2,6));
//        intervals.add(new Interval(3,5));
//        System.out.print("Merged intervals: ");
//        for (Interval interval : MergeIntervalsMedium.merge_old(intervals))
//            System.out.print("[" + interval.start + "," + interval.end + "] ");
//        System.out.println();

        Interval[] intervals = new Interval[] {new Interval (1,4), new Interval(2,5), new Interval(7,9)};
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervalsMedium.merge(intervals))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        intervals = new Interval[] {new Interval (6,7), new Interval(2,4), new Interval(5,9)};
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervalsMedium.merge(intervals))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        intervals = new Interval[] {new Interval (1,4), new Interval(2,6), new Interval(3,5)};
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervalsMedium.merge(intervals))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

    }
}
