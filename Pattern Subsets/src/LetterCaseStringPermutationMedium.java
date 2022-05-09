/*
Given a string, find all of its permutations preserving the character sequence but changing case.

Example 1:

Input: "ad52"
Output: "ad52", "Ad52", "aD52", "AD52"

Example 2:

Input: "ab7c"
Output: "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
 */

import java.util.ArrayList;
import java.util.List;

public class LetterCaseStringPermutationMedium {

    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> res = new ArrayList<>();
        res.add(str);

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isDigit(ch)){
                int len = res.size();
                for (int j = 0; j < len; j++) {
                    // because strings are immutable
                    String copy = res.get(j);
                    // because strings are immutable
                    String copyReplaced = copy.replace(ch, Character.toUpperCase(ch));
//                    System.out.print(copyReplaced);

                    res.add(copyReplaced);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> result = findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }
}
