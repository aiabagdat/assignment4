package graph.dagsp;

import common.Graph;
import common.Metrics;
import graph.topo.TopologicalSort;
import java.util.*;

public class DagSP {
    public static long[] shortest(Graph dag, int src, Metrics m){
        List<Integer> order = TopologicalSort.kahn(dag, new Metrics());
        long INF=(long)1e18;
        long[] dist=new long[dag.n];
        Arrays.fill(dist,INF);
        dist[src]=0;
        m.start();
        for(int u:order){
            if(dist[u]==INF) continue;
            for(var e:dag.adj.get(u)){
                long nd=dist[u]+e.w;
                if(nd<dist[e.v]){
                    dist[e.v]=nd;
                    m.relax++;
                }
            }
        }
        m.stop();
        return dist;
    }
}
