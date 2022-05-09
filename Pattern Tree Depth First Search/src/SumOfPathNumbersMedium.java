/*
Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a number. Find the total sum of all the numbers represented by all paths.

 */

public class SumOfPathNumbersMedium {

    public static int findSumOfPathNumbers(Node root){
        return findSumOfPathNumbers(root, 0);
    }

    public static int findSumOfPathNumbers(Node node, int sum){
        // empty tree
        if (node == null) {
            return 0;
        }
        // calc current path sum
        sum = 10*sum + node.val;

        // leaf node
        if (node.left == null && node.right == null){
            return sum;
        }

        // else: chunk of tree
        return findSumOfPathNumbers(node.left, sum) + findSumOfPathNumbers(node.right, sum);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(0);
        root.right = new Node(1);
        root.left.left = new Node(1);
        root.right.left = new Node(6);
        root.right.right = new Node(5);
        System.out.println("Total Sum of Path Numbers: " + findSumOfPathNumbers(root));
    }
}
