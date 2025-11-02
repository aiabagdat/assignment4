package graph.dagsp;

import common.Graph;
import common.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DagPathsTest {

    @Test
    void shortestPath_on_small_dag() {
        // DAG:
        // 0 -> 1 (2), 0 -> 2 (5)
        // 1 -> 2 (1), 1 -> 3 (3)
        // 2 -> 3 (1)
        Graph dag = new Graph(4, true);
        dag.addEdge(0,1,2);
        dag.addEdge(0,2,5);
        dag.addEdge(1,2,1);
        dag.addEdge(1,3,3);
        dag.addEdge(2,3,1);

        Metrics ms = new Metrics();
        long[] dist = DagSP.shortest(dag, 0, ms);

        // Ожидаемые кратчайшие:
        // 0:0, 1:2, 2:3 (0->1->2), 3:4 (0->1->2->3)
        assertEquals(0, dist[0]);
        assertEquals(2, dist[1]);
        assertEquals(3, dist[2]);
        assertEquals(4, dist[3]);

        assertTrue(ms.relax > 0);
    }

    @Test
    void longestPath_on_small_dag() {
        // Тот же граф — проверим longest (critical) от 0
        Graph dag = new Graph(4, true);
        dag.addEdge(0,1,2);
        dag.addEdge(0,2,5);
        dag.addEdge(1,2,1);
        dag.addEdge(1,3,3);
        dag.addEdge(2,3,1);

        Metrics ml = new Metrics();
        var res = DagLongestPath.longest(dag, 0, ml);

        // Максимальный путь от 0 до 3: 0->2->3 с весом 5+1=6
        assertEquals(0, res.dist[0]);
        assertEquals(2, res.dist[1]);     // 0->1
        assertEquals(5, res.dist[2]);     // 0->2 (длиннее, чем 0->1->2=3)
        assertEquals(6, res.dist[3]);     // 0->2->3

        // Родители должны восстанавливать критический путь к 3: 0 -> 2 -> 3
        assertEquals(2, res.parent[3]);
        assertEquals(0, res.parent[2]);
    }
}

