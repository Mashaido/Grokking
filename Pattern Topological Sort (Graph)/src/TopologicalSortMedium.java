/*
Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its
vertices such that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the
ordering.

Given a directed graph, find the topological ordering of its vertices.

Example 1:

Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
Output: Following are the two valid topological sorts for the given graph:
1) 3, 2, 0, 1
2) 3, 2, 1, 0

Example 2:

Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
Output: Following are all valid topological sorts for the given graph:
1) 4, 2, 3, 0, 1
2) 4, 3, 2, 0, 1
3) 4, 3, 2, 1, 0
4) 4, 2, 3, 1, 0
5) 4, 2, 0, 3, 1

Example 3:
Input: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]
Output: Following are all valid topological sorts for the given graph:
1) 5, 6, 3, 4, 0, 1, 2
2) 6, 5, 3, 4, 0, 1, 2
3) 5, 6, 4, 3, 0, 2, 1
4) 6, 5, 4, 3, 0, 1, 2
5) 5, 6, 3, 4, 0, 2, 1
6) 5, 6, 3, 4, 1, 2, 0

There are other valid topological ordering of the graph too.

 */

import java.util.*;

public class TopologicalSortMedium {
    static List<Graph.Vertex> sources = new ArrayList<>();
    static Map<Graph.Vertex, Integer> counts = new HashMap<>();

    /*
    NOTE: for topological sort to work, Graph should be a DAG i.e Directed Acyclic Graph
    i.e without cycles

    NOTE: topological sort can use a variation of dfs, pretty similar just modify to ensure
    that you push and print out a node that's a source i.e without incoming edges
     */
    public static List<Integer> sort(int[][] edges) {
        Graph.createGraph(edges);
        create();

        // start with a node that has no incoming edges
        int root = findSources().get(0).value;

        List<Integer> paths = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] seen = new boolean[5];

        stack.push(root);
        seen[root] = true;


        while (!stack.isEmpty()) {
            Graph.Vertex vertex = new Graph.Vertex(stack.pop());
            paths.add(vertex.value);
            counts.remove(vertex);

            // visit all edges i.e this vertex children
            for (Graph.Vertex v: Graph.graph.get(vertex)) {
                // 1st decrement the inbound edges to its children to reflect deletion of node
                counts.put(v, counts.get(v)-1);

                // 2nd if this child is now a source vertex, process it next
                if (isSource(v)) {
                    // to avoid repetitions
                    if (!seen[v.value]) {
                        stack.push(v.value);
                        seen[v.value] = true;
                    }
                }
            }
        }

        return paths;

//        List<Integer> paths = new ArrayList<>();
//
//        // find sources i.e vertices/nodes with no incoming edges
//        while (!Graph.graph.isEmpty()) {
//            List<Graph.Vertex> source = findSources();
//            sources.addAll(source);
//            // keep interim track of children
//            List<Graph.Vertex> childrenList = new ArrayList<>();
//            for (int i = 0; i < source.size(); i++) {
//                // add this vertex's children from graph to children list
//                childrenList.addAll(Graph.graph.get(source.get(i)));
//                // deduct count
//                for (Graph.Vertex child: childrenList) {
//                    counts.put(child, counts.get(child)-1);
//                    if (isSource(child)) {
//                        sources.add(child);
//                        // safe delete from counts
//                        counts.remove(child);
//                    }
//                }
//                // safe delete this vertex
//                Graph.graph.remove(source.get(i));
//                // safe delete from counts
//                counts.remove(source.get(i));
//            }
//        }
//
//        return paths;
    }

    // keeps track of whether this vertex has inbounds {>=1} or not {0}
    // NOTE: can use this to detect cycles
    private static void create() {
        // add keys 1st
        for (Graph.Vertex vertex: Graph.graph.keySet()) {
            counts.put(vertex, 0);
        }

        // add values --these all have inbounds
        // takes care of duplicates i.e a vertex having two inbounds
        for (List<Graph.Vertex> vertices: Graph.graph.values()) {
            for (Graph.Vertex vertex: vertices) {
                // count number of inbounds
                int count = 0;
                ///// NOTE instd of looping again (higher complexity) just add one by one, as keys in counts
                // i.e get existing value-count and add 1 to it
                for (List<Graph.Vertex> verts: Graph.graph.values()) {
                    for (Graph.Vertex vert: vertices) {
                        if (vert.value == vertex.value) {
                            count++;
                        }
                    }
                }
                counts.put(vertex, count);
            }
        }
    }

    public static boolean isSource(Graph.Vertex vertex) {
        return counts.get(vertex) == 0;
    }

    public static List<Graph.Vertex> findSources() {
        List<Graph.Vertex> sources = new ArrayList<>();
        for (Graph.Vertex vertex: counts.keySet()){
            if (counts.get(vertex) == 0) {
                sources.add(vertex);
            }
        }
        return sources;
    }

    public static void main(String[] args) {
        List<Integer> result = sort(new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
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
