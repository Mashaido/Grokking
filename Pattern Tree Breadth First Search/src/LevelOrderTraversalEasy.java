/*
Given a binary tree, populate an array to represent its level-by-level traversal.
You should populate the values of all nodes of each level from left to right in separate sub-arrays.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LevelOrderTraversalEasy {
    public static List<Integer> traverse(Node root) {
        List<Integer> res = new ArrayList<>();
        Queue<Node> queue = new PriorityQueue<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node parent = queue.poll();
            res.add(parent.val);

            if (parent.left != null) {
                queue.add(parent.left);
            }
            if (parent.right != null) {
                queue.add(parent.right);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
//        List<List<Integer>> result = traverse(root);
        List<Integer> result = traverse(root);
        System.out.println("Level order traversal: " + result);
    }
}
