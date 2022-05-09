/*
Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the
given tree.
 */
public class PathWithGivenSequenceMedium {
    public static boolean findPath(Node head, int[] seq) {
        // both empty array and tree
        if (head == null && seq.length == 0) {
            return true;
        }
        // remaining either or
        if (head == null || seq.length == 0) {
            return false;
        }
        return findPath(head, seq, 0);
    }

    public static boolean findPath(Node node, int[] seq, int i) {
        // terminating (leaf) condition if not on path
        if (node == null) {
            return false;
        }
        // terminating (leaf) condition if on path
        if (node.left == null && node.right == null && node.val == seq[i]) {
            return true;
        }

        // terminating (path) conditioning
        if (node.val != seq[i]) {
            return false;
        }

        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recursive call return true
        return findPath(node.left, seq, i+1) || findPath(node.right, seq, i+1);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(0);
        root.right = new Node(1);
        root.left.left = new Node(1);
        root.right.left = new Node(6);
        root.right.right = new Node(5);

        System.out.println("Tree has path sequence: " + findPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " + findPath(root, new int[] { 1, 1, 6 }));
    }
}
