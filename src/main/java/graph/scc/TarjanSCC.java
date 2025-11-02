package graph.scc;

import common.Graph;
import common.Metrics;
import java.util.*;

public class TarjanSCC {
    int time=0, comp=0;
    Graph g;
    Metrics m;
    int[] disc, low, compId;
    boolean[] stack;
    Deque<Integer> st = new ArrayDeque<>();
    public List<List<Integer>> comps = new ArrayList<>();

    public TarjanSCC(Graph g, Metrics m){
        this.g=g; this.m=m;
        int n=g.n;
        disc=new int[n]; Arrays.fill(disc,-1);
        low=new int[n];
        compId=new int[n]; Arrays.fill(compId,-1);
        stack=new boolean[n];
    }

    public int[] run(){
        m.start();
        for(int i=0;i<g.n;i++)
            if(disc[i]==-1) dfs(i);
        m.stop();
        return compId;
    }

    void dfs(int u){
        m.dfs++;
        disc[u]=low[u]=time++;
        st.push(u); stack[u]=true;

        for(var e: g.adj.get(u)){
            m.edges++;
            int v=e.v;
            if(disc[v]==-1){
                dfs(v);
                low[u]=Math.min(low[u], low[v]);
            } else if(stack[v]){
                low[u]=Math.min(low[u], disc[v]);
            }
        }

        if(low[u]==disc[u]){
            List<Integer> compList = new ArrayList<>();
            while(true){
                int x=st.pop();
                stack[x]=false;
                compId[x]=comp;
                compList.add(x);
                if(x==u) break;
            }
            comps.add(compList);
            comp++;
        }
    }
}
