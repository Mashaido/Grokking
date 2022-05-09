/*
NOTE IN-LINE!!!!

Given a string, find the length of the longest substring in it with no more than K distinct characters.

Example 1:

Input: String="araaci", K=2
Output: 4
Explanation: The longest substring with no more than '2' distinct characters is "araa".

Example 2:

Input: String="araaci", K=1
Output: 2
Explanation: The longest substring with no more than '1' distinct characters is "aa".

 */

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithMaximumKDistinctCharactersMedium {

    /*
    Consider First a simple case where k=1

     */
    public static int LongestSubstringKDistinct(String str) {
        int len = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            int j = i+1;
            int lenTemp = 1;

            // NOTE TO BOUNDARY CHECK FIRST!!!!
            while (j < str.length() && str.charAt(j) == str.charAt(i)) {
                lenTemp++;
                j++;
            }
            len = Math.max(len, lenTemp);
        }
        return len;
    }

    public static int LongestSubstringKDistinct(String str, int k) {
        if (k == 0) {
            return 0;
        }
        Set<Character> setOfDistinctCharsSoFar = new HashSet<>();
        int longestSubsLen = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            char charAtI = str.charAt(i);
            setOfDistinctCharsSoFar.add(charAtI);
            int trackingLen = 1;

            int j = i+1;
            while ((j < str.length()))  {
                setOfDistinctCharsSoFar.add(str.charAt(j));
                if (setOfDistinctCharsSoFar.size() <= k) {
                    trackingLen++;
                }
                j++;
            }
            longestSubsLen = Math.max(longestSubsLen, trackingLen);
            setOfDistinctCharsSoFar.clear();
        }
        return longestSubsLen;
    }

    public static void main(String[] args) {

        System.out.printf("\nThe longest substring with no more than '1' distinct character is of length %d" , LongestSubstringKDistinct("araaci"));

        System.out.printf("\nThe longest substring with no more than '1' distinct character is of length %d" , LongestSubstringKDistinct("araaci", 2));

        System.out.printf("\nThe longest substring with no more than '1' distinct character is of length %d" , LongestSubstringKDistinct("cbbebi", 3));
        System.out.printf("\nThe longest substring with no more than '1' distinct character is of length %d" , LongestSubstringKDistinct("cbbebi", 10));

    }
}
