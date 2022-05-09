import java.util.*;

public class Graph {

    public static class Vertex {
        String label; // city or person
        int value;

        Vertex(String label) {
            this.label = label;
        }
        Vertex(int value) {
            this.value = value;
        }
    }

    // representing our graph using an adjacency list Vertex: Vertices i.e Edges in btwn
    static Map<Vertex, List<Vertex>> graph = new HashMap<>();

    // create or update
    public static void createGraph(int[][] edges) {
        for (int[] edge: edges) {
            Vertex vertex = new Vertex(edge[0]);
            Vertex child = new Vertex(edge[1]);

            if (!graph.containsKey(vertex)) {
                graph.put(vertex, new ArrayList<>());
            }
            graph.get(vertex).add(child);
        }
//        for (Vertex key: graph.keySet()) {
//            for (Vertex list: graph.get(key)) {
//                System.out.println(key.value);
//                System.out.println(list.value);
//            }
//        }
        System.out.println("CREATED!");
    }


    // search or traverse O(V+E) bc we process every node once
    public static List<Integer> dfs(int root) {
        List<Integer> paths = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] seen = new boolean[5];

        stack.push(root);
        // might not need this if this node has no incoming edges
        seen[root] = true;


        while (!stack.isEmpty()) {
            Vertex vertex = new Vertex(stack.pop());
            paths.add(vertex.value);

            // visit all edges
            if (!graph.containsKey(vertex)) {
                System.out.println(paths);
                System.out.println(vertex.value);
            }
            for (Vertex v: graph.get(vertex)) {
                // to avoid repetitions
                if (!seen[v.value]) {
                    stack.push(v.value);
                    seen[v.value] = true;
                }
            }
        }

        return paths;
    }

    // search or traverse
    public static List<Integer> bfs(int root) {
        List<Integer> paths = new ArrayList<>();
        Queue<Integer> queue = new PriorityQueue<>();
        boolean[] seen = new boolean[5];

        queue.add(root);

        while (!queue.isEmpty()) {
            Vertex vertex = new Vertex(queue.poll());
            paths.add(vertex.value);

            // visit all edges
            // need take care of null cases btw (either in the map, graph or tree-node)
            for (Vertex v: graph.get(vertex)) {
                // to avoid repetitions
                if (!seen[v.value]) {
                    queue.add(v.value);
                    seen[v.value] = true;
                }
            }
        }

        return paths;
    }

    public static void main(String[] args) {
        createGraph(new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        List<Integer> result = dfs(3);
        System.out.println(result);

//        result = sort(new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },
//                new int[] { 2, 1 }, new int[] { 3, 1 } });
//        System.out.println(result);
//
//        result = sort(new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
//                new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
//        System.out.println(result);
    }
}
