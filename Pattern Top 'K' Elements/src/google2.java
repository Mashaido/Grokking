public class google2 {
    /*
    You are given a string and your goal is to shorten it using the following operation: you can remove a contiguous fragment composed of one particular letter from a string. For example, from "aabbccb" you can remove "aa", "bb" or "cc", but not "bbcc" (which is composed of two different letters) or "bbb" (which is not a contiguous fragment).

You are allowed to perform this operation twice in succession (meaning that the second operation would be performed on a string that has already been shortened by the first operation). What is the length of the shortest string that can be obtained in such a way?

Write a function:

class Solution { public int solution(String S); }

that, given a string S, returns the length of the shortest string which can be obtained by removing a contiguous fragment composed of one particular letter twice.

Examples:

1. Given S = "xaccaaabb", the function should return 3. First you can remove the fragment "cc", to obtain "xaaaabb". Then you can remove the fragment "aaaa", and obtain "xbb".

2. Given S = "aabbcaaa", the function should return 3. First you can remove the fragment "aaa" and obtain "aabbc". Then you can remove the fragment "bb", resulting in "aac". You may alternatively remove the fragment "aa", obtaining "bbc".

3. Given S = "aabaa", the function should return 0. You can remove the fragment "b" from the middle, resulting in "aaaa". Then you can remove all remaining letters, as they already form a contiguous fragment composed of the letter 'a'.

Write an efficient algorithm for the following assumptions:

the length of S is within the range [1..200,000];
string S consists only of lowercase letters (a-z).
     */

    import java.util.ArrayList;
import java.util.List;

import java.util.PriorityQueue;

    class Solution {

        public static int solution(String S) {

            List<Character> characters = new ArrayList<>();
            List<Integer> counts = new ArrayList<>();

            int N = S.length();
            // edge case I
            if (N == 0) {
                return -1;
            }

            // edge case II
            if (N <= 2) {
                return 0;
            }

            int i = 0;
            while (i < N) {
                char ch = S.charAt(i);
                int count = 1;

                int j = i+1;
                while (j < N) {
                    char chj = S.charAt(j);
                    if (chj == ch) {
                        count++;
                        j++;
                    }
                    else {
                        i = j-1;
                        break;
                    }
                }
                // record char counts
                characters.add(ch);
                counts.add(count);

                i++;

            }

            // track largest 2 counts-of-chars
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            minHeap.add(counts.get(0));
            minHeap.add(counts.get(1));
            // keep track of len to subtrack from
            int sub = counts.get(0) + counts.get(1);
            // this is for the case of in-betweens
            int altSub = Integer.MIN_VALUE;

            for (int k = 2; k < counts.size(); k++) {
                // keep track of altenative case that has in-betweens and compare at the very end
                if (characters.get(k) == characters.get(k-2) && characters.get(k) != characters.get(k-1)) {
                    int newSum = counts.get(k) + counts.get(k-1) + counts.get(k-2);
                    altSub = Math.max(altSub, newSum);
                }

                if (counts.get(k) > minHeap.peek()) {
                    sub = sub - minHeap.peek();
                    minHeap.poll();
                    minHeap.add(counts.get(k));
                    sub = sub + counts.get(k);

                }
            }
            // case where altSub wasn't updated
            if (altSub < 0) {
                return N-sub;
            }

            System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
            return Math.min(N-sub, N-altSub);
        }
    }


}
