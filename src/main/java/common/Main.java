package common;

import io.JsonGraphIO;
import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import graph.dagsp.DagSP;
import graph.dagsp.DagLongestPath;

public class Main {
    public static void main(String[] args) throws Exception{
        var g = JsonGraphIO.load(args[0]);
        Metrics m = new Metrics();

        // ---- SCC ----
        TarjanSCC t = new TarjanSCC(g, m);
        int[] comp = t.run();
        System.out.println("SCC count = " + t.comps.size());
        System.out.println("DFS=" + m.dfs + ", edges=" + m.edges + ", time=" + m.ms() + "ms");

        // ---- Build condensation DAG ----
        Graph dag = buildCondensation(g, comp, t.comps.size());

        // ---- Topological sort ----
        Metrics mt = new Metrics();
        var order = TopologicalSort.kahn(dag, mt);
        System.out.println("Topo order: " + order);
        System.out.println("pushes=" + mt.pushes + ", pops=" + mt.pops);

        // ---- Shortest path ----
        Metrics ms = new Metrics();
        var dist = DagSP.shortest(dag, comp[0], ms);
        System.out.println("Shortest dist:");
        for(int i = 0; i < dist.length; i++)
            System.out.println(i + ": " + dist[i]);
        System.out.println("Relax=" + ms.relax + ", time=" + ms.ms() + "ms");

        // ---- Longest path (critical path) ----
        Metrics ml = new Metrics();
        var longRes = DagLongestPath.longest(dag, comp[0], ml);

        long maxDist = Long.MIN_VALUE;
        int endNode = -1;
        for(int i = 0; i < longRes.dist.length; i++){
            if(longRes.dist[i] > maxDist){
                maxDist = longRes.dist[i];
                endNode = i;
            }
        }

        var path = DagLongestPath.reconstructPath(longRes.parent, comp[0], endNode);

        System.out.println("Critical path length (longest) = " + maxDist);
        System.out.println("Critical path nodes: " + path);
        System.out.println("Longest relax = " + ml.relax + ", time=" + ml.ms() + "ms");
    }

    static Graph buildCondensation(Graph g, int[] comp, int C){
        Graph dag = new Graph(C, true);
        boolean[][] seen = new boolean[C][C];
        for(int u = 0; u < g.n; u++){
            for(var e : g.adj.get(u)){
                int cu = comp[u], cv = comp[e.v];
                if(cu != cv && !seen[cu][cv]){
                    dag.addEdge(cu, cv, e.w);
                    seen[cu][cv] = true;
                }
            }
        }
        return dag;
    }
}
