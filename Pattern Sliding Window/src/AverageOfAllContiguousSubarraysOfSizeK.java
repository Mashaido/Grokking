import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AverageOfAllContiguousSubarraysOfSizeK {

    /*

    Technique: sliding window i.e subtract the element going out of the sliding window and add the new element getting included in the sliding window
    Time: O(n)
    Space: O(1) no additional space

    Notes:
    -take note of Double vs Integer in results

     */
    public static ArrayList<Double> findAverages(int k, int[] arr) {
        ArrayList<Double> lst = new ArrayList<>();
        double sum = 0;
        if (k >= arr.length) {
            return lst;
        }
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int i = 1;
        int j = k + i - 1;
        lst.add(sum/k);
        sum -= arr[0];
        while (j < arr.length) {
            sum += arr[j];
            lst.add(sum/k);
            sum -= arr[i];
            i++;
            j++;
        }
        return lst;
    }

    public static void main(String[] args) {
        ArrayList<Double> result = AverageOfAllContiguousSubarraysOfSizeK.findAverages(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });

        System.out.println("Averages of subarrays of size K: " + result);
    }
}
