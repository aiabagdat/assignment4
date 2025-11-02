package common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphBasicsTest {

    @Test
    void addEdge_and_graphIsDirected_flagOK() {
        Graph g = new Graph(3, true);
        g.addEdge(0, 1, 5);
        g.addEdge(1, 2, 7);

        assertEquals(3, g.n);
        assertTrue(g.directed);

        // 0 -> 1 (w=5)
        assertEquals(1, g.adj.get(0).size());
        assertEquals(1, g.adj.get(0).get(0).v);
        assertEquals(5, g.adj.get(0).get(0).w);

        // 1 -> 2 (w=7)
        assertEquals(1, g.adj.get(1).size());
        assertEquals(2, g.adj.get(1).get(0).v);
        assertEquals(7, g.adj.get(1).get(0).w);

        // 2 has no outgoing
        assertTrue(g.adj.get(2).isEmpty());
    }
}
