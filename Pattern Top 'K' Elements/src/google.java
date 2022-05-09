public class google {

    /*
    You want to spend some time in your N-day long vacation on a movie marathon. It's an event that takes place over a number of consecutive days. On each of those days you want to go to the cinema to watch a movie, but you don't want to see any movie more than once. The cinema in your town shows one movie per day. Knowing the schedule for the N days of your vacation, you want to plan the longest possible marathon.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers (which represent the IDs of the movies played on each of the N days of your vacation), returns the length of the longest marathon that doesn't feature any movie more than once.

For example, given array A = [1, 2, 1, 3, 4, 3, 5] the function should return 4. The longest marathon starts on the second day of your vacation and lasts for four days, featuring movies [2, 1, 3, 4].

Given array A = [1, 1, 1, 2] the function should return 2, as the longest marathon starts on the third day of your vacation and lasts for two days (featuring movies [1, 2]).

Write an efficient algorithm for the following assumptions:
N is an integer within the range [1..100,000];
each element of array A is an integer within the range [0..1,000,000,000].
     */


    import java.util.HashSet;
import java.util.Set;


    class Solution {

        public static int solution(int[] A){
            // edge case I
            if (A == null) {
                return -1;
            }

            int N = A.length;
            int maxWindowLen = Integer.MIN_VALUE;

            // edge case II
            if (N == 0) {
                return 0;
            }

            // edge case III
            if (N == 1) {
                return 1;
            }

            // keep track of uniques
            Set<Integer> set = new HashSet<>();

            // sliding window
            for (int i = 0; i < N; i++) {
                int windowLen = 1;
                set.add(A[i]);

                // stopping criteria
                int j = i+1;
                while (j < N) {
                    if (!set.contains(A[j])) {
                        // add to set and increment tracker
                        set.add(A[j]);
                        windowLen++;
                        j++;
                    }
                    else {
                        // the end, get out!
                        break;
                    }
                }
                // update new maxWindowLen
                maxWindowLen = Math.max(maxWindowLen, windowLen);

                // empty out set
                set.clear();
            }


            System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
            return maxWindowLen;
        }
    }
}





