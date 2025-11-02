package graph.topo;

import common.Graph;
import common.Metrics;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    @Test
    void simpleDag_orderRespectsEdges() {
        // 0 -> 1 -> 2, 0 -> 2
        Graph dag = new Graph(3, true);
        dag.addEdge(0,1,1);
        dag.addEdge(1,2,1);
        dag.addEdge(0,2,1);

        Metrics mt = new Metrics();
        List<Integer> order = TopologicalSort.kahn(dag, mt);

        // Длина порядка должна равняться числу вершин
        assertEquals(3, order.size());

        // Индексы в порядке
        int p0 = order.indexOf(0);
        int p1 = order.indexOf(1);
        int p2 = order.indexOf(2);

        assertTrue(p0 < p1);
        assertTrue(p1 < p2);
        assertTrue(p0 < p2);

        // sanity: были операции с очередью
        assertTrue(mt.pushes > 0);
        assertTrue(mt.pops > 0);
    }
}

