import java.util.*;

/*
Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum
of the square of all of its digits, leads us to number ‘1’. All other (not-happy) numbers will never reach
‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’.

 Example 1:

Input: 23
Output: true (23 is a happy number)
Explanations: Here are the steps to find out that 23 is a happy number:

Example 2:
Input: 12
Output: false (12 is not a happy number)
Explanations: Here are the steps to find out that 12 is not a happy number:

 */
public class HappyNumberMedium {

    /*
    not this correct technique :( instd using hashtable
     */
    public static Boolean find(int num) {
        Map<Integer, Boolean> map = new HashMap<>();
        int sumSquared = 0;
        String str = String.valueOf(num);
        String[] arr = str.split("");
        List<String> nums = new ArrayList<>();
        nums.addAll(Arrays.asList(arr));
        while (sumSquared >= 0) {
            for (int i = 0; i < nums.size(); i++) {
                sumSquared += Math.pow(Integer.valueOf(nums.get(i)), 2);
            }
            if (sumSquared == 1) {
                return true;
            }
            if (map.containsKey(sumSquared)) {
                return false;
            }
            else if (!map.containsKey(sumSquared)) {
                map.put(sumSquared, true);
            }

            // process sumSquared for next loop
            String str2 = String.valueOf(sumSquared);
            // reset sumSquared
            sumSquared = 0;
            String[] arr2 = str2.split("");
            nums = new ArrayList<>();
            nums.addAll(Arrays.asList(arr2));
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(find(23));
        System.out.println(find(12));
    }
}
