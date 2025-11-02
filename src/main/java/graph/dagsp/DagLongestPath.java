package graph.dagsp;

import common.Graph;
import common.Metrics;
import graph.topo.TopologicalSort;
import java.util.*;

public class DagLongestPath {

    public static class Result {
        public long[] dist;
        public int[] parent;
    }

    public static Result longest(Graph dag, int src, Metrics m){
        List<Integer> order = TopologicalSort.kahn(dag, new Metrics());
        long NEG_INF = (long)-1e18;

        long[] dist = new long[dag.n];
        int[] parent = new int[dag.n];

        Arrays.fill(dist, NEG_INF);
        Arrays.fill(parent, -1);

        dist[src] = 0;

        m.start();
        for(int u : order){
            if(dist[u] == NEG_INF) continue;
            for(var e : dag.adj.get(u)){
                long nd = dist[u] + e.w;
                if(nd > dist[e.v]){
                    dist[e.v] = nd;
                    parent[e.v] = u;
                    m.relax++;
                }
            }
        }
        m.stop();

        Result r = new Result();
        r.dist = dist;
        r.parent = parent;
        return r;
    }

    public static List<Integer> reconstructPath(int[] parent, int start, int end){
        List<Integer> path = new ArrayList<>();
        for(int v = end; v != -1; v = parent[v]) path.add(v);
        Collections.reverse(path);
        return path;
    }
}
