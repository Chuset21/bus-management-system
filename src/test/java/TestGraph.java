import bus_system.util.graph.Graph;
import bus_system.util.path.Path;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGraph {

    @Test
    public void testConstructor() {
        new Graph<>();
    }

    @Test
    public void testAddEdge() {
        final Graph<Character> graph = new Graph<>();
        graph.addEdge('A', 'B', 2);
        graph.addEdge('C', 'B', 1);
        graph.addEdge('A', 'C', 1);

        final String expected = """
                A -> C with weight of 1.000
                A -> B with weight of 2.000
                C -> B with weight of 1.000""";
        assertEquals(expected, graph.toString(), "Graph addition not as expected");
    }

    @Test
    public void testGetVertexSet() {
        final Graph<Character> graph = new Graph<>();
        graph.addEdge('A', 'B', 2);
        graph.addEdge('C', 'B', 1);
        graph.addEdge('A', 'C', 1);

        assertEquals(new HashSet<>(List.of('A', 'B', 'C')), graph.getVertexSet());
    }

    @Test
    public void testGetShortestPath() {
        final Graph<Integer> graph = new Graph<>();
        graph.addEdge(2, 0, 7);
        graph.addEdge(2, 1, 3);
        graph.addEdge(0, 1, 9);
        graph.addEdge(1, 3, 5);
        graph.addEdge(1, 6, 7);
        graph.addEdge(3, 5, 4);
        graph.addEdge(3, 7, 5);
        graph.addEdge(5, 7, 4);
        graph.addEdge(7, 6, 2);
        graph.addEdge(4, 7, 3);
        graph.addEdge(4, 2, 8);

        Path<Integer> path = graph.getShortestPath(2, 6);

        List<Integer> expectedPath = new LinkedList<>();
        expectedPath.add(2);
        expectedPath.add(1);
        expectedPath.add(6);

        assertEquals(expectedPath, path.getPathTaken());
        assertEquals(10, path.getTotalCost().orElse(-1d));

        path = graph.getShortestPath(4, 6);

        expectedPath.clear();
        expectedPath.add(4);
        expectedPath.add(7);
        expectedPath.add(6);

        assertEquals(expectedPath, path.getPathTaken());
        assertEquals(5, path.getTotalCost().orElse(-1d));

        path = graph.getShortestPath(5, 1);

        expectedPath.clear();

        assertEquals(expectedPath, path.getPathTaken());
        assertEquals(Optional.empty(), path.getTotalCost());

        path = graph.getShortestPath(10, 1);

        assertEquals(expectedPath, path.getPathTaken());
        assertEquals(Optional.empty(), path.getTotalCost());
    }
}
