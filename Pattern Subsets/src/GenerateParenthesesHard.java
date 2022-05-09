import java.util.ArrayList;
import java.util.List;

/*
For a given number ‘N’, write a function to generate all combination of ‘N’ pairs of balanced parentheses.

Example 1:

Input: N=2
Output: (()), ()()


Example 2:

Input: N=3
Output: ((())), (()()), (())(), ()(()), ()()()

 */
public class GenerateParenthesesHard {
    public static List<String> generateValidParentheses(int num) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append('(');
            sb.append(')');
        }
        res.add(sb.toString());

        sb = new StringBuilder();
        for (int i = 0; i < num/2; i++) {
            sb.append('(');
            sb.append('(');
            sb.append(')' );
            sb.append(')');
        }
        res.add(sb.toString());
        return res;
    }

    public static void main(String[] args) {
        List<String> result = generateValidParentheses(2);
        System.out.println("All combinations of balanced parentheses are: " + result);

        result = generateValidParentheses(3);
        System.out.println("All combinations of balanced parentheses are: " + result);
    }
}
