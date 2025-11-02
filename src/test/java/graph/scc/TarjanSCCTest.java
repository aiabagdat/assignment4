package graph.scc;

import common.Graph;
import common.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TarjanSCCTest {

    @Test
    void twoCycles_plus_bridge_between_them() {
        // SCC #1: 0 <-> 1 <-> 2 <-> 0
        // SCC #2: 3 <-> 4
        // Edge: 2 -> 3 (DAG connection between SCCs)
        Graph g = new Graph(5, true);
        g.addEdge(0,1,1); g.addEdge(1,2,1); g.addEdge(2,0,1);
        g.addEdge(3,4,1); g.addEdge(4,3,1);
        g.addEdge(2,3,1);

        Metrics m = new Metrics();
        TarjanSCC tj = new TarjanSCC(g, m);
        int[] comp = tj.run();

        // Ожидаем 2 компоненты: {0,1,2} и {3,4}
        assertEquals(2, tj.comps.size());

        // Все 0,1,2 в одной компоненте; 3,4 — в другой
        int c012 = comp[0];
        assertEquals(c012, comp[1]);
        assertEquals(c012, comp[2]);
        assertNotEquals(c012, comp[3]);
        assertEquals(comp[3], comp[4]);

        // Немного sanity по метрикам: хотя бы один DFS был
        assertTrue(m.dfs >= 1);
    }
}
