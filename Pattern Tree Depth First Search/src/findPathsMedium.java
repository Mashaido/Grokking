/*
Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node
values of each path equals ‘S’.
 */

import java.util.ArrayList;
import java.util.List;

public class findPathsMedium {

    public static List<List<Integer>> findPaths(Node root, int sum){
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPaths(root, sum, currentPath, allPaths);
        return allPaths;
    }

    public static void findPaths(Node node, int sum, List<Integer> currentPath, List<List<Integer>> allPaths){
        if (node == null) {
            return;
        }
        currentPath.add(node.val);
        if (node.left == null && node.right == null && node.val == sum){
            allPaths.add(new ArrayList<Integer>(currentPath));
        }
        else {
            findPaths(node.left, sum-node.val, currentPath, allPaths);
            findPaths(node.right, sum- node.val, currentPath, allPaths);
        }

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(4);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        int sum = 23;
        List<List<Integer>> result = findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}
