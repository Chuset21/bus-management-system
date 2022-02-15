import com.bus_system.util.graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

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
}
