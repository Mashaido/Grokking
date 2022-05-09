/*
Given two lists of intervals, find the intersection of these two lists.
Each list consists of disjoint intervals sorted on their start time.

Example 1:

Input: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]
Output: [2, 3], [5, 6], [7, 7]
Explanation: The output list contains the common intervals between the two lists.

Example 2:

Input: arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]]
Output: [5, 7], [9, 10]
Explanation: The output list contains the common intervals between the two lists.

 */

import java.util.ArrayList;
import java.util.List;

public class IntervalsIntersectionMedium {

    public static List<Interval> intersects(List<Interval> intervals1, List<Interval> intervals2) {
        List<Interval> intersection = new ArrayList<>();
        for (Interval intervalA: intervals1) {
            for (Interval intervalB: intervals2) {
                // check for overlap
                if (overlaps(intervalA, intervalB)) {
                    Interval interval = new Interval(Math.max(intervalA.start, intervalB.start),
                            Math.min(intervalA.end, intervalB.end));
                    intersection.add(interval);
                }
            }
        }
        return intersection;
    }

    public static boolean overlaps(Interval interval1, Interval interval2) {
        if (interval1.start <= interval2.start) {
            return interval2.start < interval1.end;
        }
        else {
            return interval2.end >= interval1.start;
//            return interval1.start < interval2.end;
        }
    }

    public static void main(String[] args) {
        List<Interval> intervals1 = new ArrayList<>();
        List<Interval> intervals2 = new ArrayList<>();

        intervals1.add(new Interval (1,3));
        intervals1.add(new Interval(5,6));
        intervals1.add(new Interval(7,9));

        intervals2.add(new Interval(2,3));
        intervals2.add(new Interval(5,7));

        List<Interval> intersection = IntervalsIntersectionMedium.intersects(intervals1, intervals2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : intersection)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        intervals1 = new ArrayList<>();
        intervals2 = new ArrayList<>();

        intervals1.add(new Interval (1,3));
        intervals1.add(new Interval(5,7));
        intervals1.add(new Interval(9,12));

        intervals2.add(new Interval(5,10));

        intersection = IntervalsIntersectionMedium.intersects(intervals1, intervals2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : intersection)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
    }
}
