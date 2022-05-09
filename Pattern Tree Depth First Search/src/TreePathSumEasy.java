/*
Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf such that the sum of all the node
values of that path equals ‘S’.

 */
public class TreePathSumEasy {
    public static boolean hasPath(Node root, int sum) {
        // terminating conditions
        // A. if this root is null
        if (root == null) {
            return false;
        }
        // B. if we're at leaf node
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }

        // else
        // keep traversing, passing down subtracted sum
        return hasPath(root.left, sum-root.val) || hasPath(root.right, sum-root.val);
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        System.out.println("Tree has path: " + hasPath(root, 23));
        System.out.println("Tree has path: " + hasPath(root, 16));
    }
}
