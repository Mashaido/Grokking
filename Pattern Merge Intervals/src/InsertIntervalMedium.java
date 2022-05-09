/*

Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct
position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.

Example 1:

Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
Output: [[1,3], [4,7], [8,12]]
Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].

Example 2:

Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
Output: [[1,3], [4,12]]
Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].

Example 3:

Input: Intervals=[[2,3],[5,7]], New Interval=[1,4]
Output: [[1,4], [5,7]]
Explanation: After insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].

*/

import java.util.ArrayList;
import java.util.List;

public class InsertIntervalMedium {

    public static List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        List<Interval> merged = new ArrayList<>();
        // linear search or binary search proper place to insert newInterval
        // or just add newInterval
        intervals.add(newInterval);
        // then call merged func

        return MergeIntervalsMedium.merge_old(intervals);
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertIntervalMedium.insertInterval(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}
