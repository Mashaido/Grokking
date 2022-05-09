import java.util.*;

/*
Recap:
for unweighted graphs just use bfs
for weighted graphs:
    dijkstra's for non-negative weighted graphs O(|E|log|V|)
    bellman ford for both positive negative weighted graphs O(|E||V-1|)
    Note: bellman ford won't work for weighted graphs with negative-edge cycles
          though can use it to detect negative-edge cycles in the weighted graph

source sink -one vertex to another vertex
single source -one vertex to every other vertices
all paths -btwn all pair of vertices
 */

public class BellmanFord {
//    public static Map<Integer, List<int[]>> graph = new HashMap<>();
    public static int[] distance = new int[5];
    public static boolean singleSource(int[][] weights, int V, int start) {
        /*
        1. initialize all vertices to a distance of infinity except source at 0
        2. relax E edges V-1 times
            i.e for u-v if d[u] + cost[u,v] < d[v] then d[v] = d[u] + cost[u,v]
            else skip
        3. relax once more for just in case there's a negative-edge cycle i.e if we find a new shortest path for any node
         */

        // create graph, assuming on directed graph
//        create(weights);

        // step 1
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        // step 2
        while (V > 1) {
            for (int[] edge: weights) {
                int u = edge[0];
                int v = edge[1];
                int d = edge[2];

                if (distance[u] != Integer.MAX_VALUE && distance[u] + d < distance[v]) {
                    distance[v] = distance[u] + d;
                }
            }
            V--;
        }

        // step 3
        for (int[] edge: weights) {
            int u = edge[0];
            int v = edge[1];
            int d = edge[2];

            if (distance[u] != Integer.MAX_VALUE && distance[u] + d < distance[v]) {
                System.out.println("Graph contains negative weight cycle");
                return false;
            }
        }
        System.out.println(Arrays.toString(distance));
        return true;
    }

//    public static void create(int[][] weights) {
//        for (int[] key: weights) {
//            if (!graph.containsKey(key[0])) {
//                graph.put(key[0], new ArrayList<>());
//            }
//            // u | {v, distance}
//            // 0 | {2, 4}
//            graph.get(key[0]).add(new int[] {key[1], key[2]});
////            graph.get(key[0]).get() = key[1];
////            graph.get(key[0])[1] = key[2];
//        }
//    }

    public static void main(String[] args) {
        int[][] weights = {{0,1,-1}, {0,2,4}, {1,2,3}, {1,3,2}, {1,4,2}, {3,2,5}, {3,1,1}, {4,3,-3}};
        boolean result = singleSource(weights, 5, 0);
        System.out.println(result);
    }
}
