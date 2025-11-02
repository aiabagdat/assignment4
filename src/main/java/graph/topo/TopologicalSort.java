package graph.topo;

import common.Graph;
import common.Metrics;
import java.util.*;

public class TopologicalSort {
    public static List<Integer> kahn(Graph g, Metrics m){
        int n=g.n;
        int[] in=new int[n];
        for(int u=0;u<n;u++)
            for(var e:g.adj.get(u))
                in[e.v]++;

        Deque<Integer> q=new ArrayDeque<>();
        for(int i=0;i<n;i++)
            if(in[i]==0){q.add(i); m.pushes++;}

        List<Integer> order=new ArrayList<>();
        while(!q.isEmpty()){
            int u=q.remove(); m.pops++;
            order.add(u);
            for(var e:g.adj.get(u)){
                if(--in[e.v]==0){
                    q.add(e.v); m.pushes++;
                }
            }
        }
        return order;
    }
}
