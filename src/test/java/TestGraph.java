import com.bus_system.util.graph.Graph;
import org.junit.jupiter.api.Test;

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
                A -> [Edge[source=Vertex[value=A], destination=Vertex[value=C], weight=1.0], Edge[source=Vertex[value=A], destination=Vertex[value=B], weight=2.0]]
                C -> [Edge[source=Vertex[value=C], destination=Vertex[value=B], weight=1.0]]""";
        assertEquals(expected, graph.toString(), "Graph addition not as expected");
    }
}
