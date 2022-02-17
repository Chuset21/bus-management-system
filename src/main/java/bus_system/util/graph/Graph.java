package bus_system.util.graph;

import bus_system.util.path.ConcretePath;
import bus_system.util.path.EmptyPath;
import bus_system.util.path.Path;

import java.util.*;

public class Graph<E> {
    private final Map<E, Set<Connection<E>>> adjacencyMap = new HashMap<>();
    private final Set<E> vertexSet = new HashSet<>();

    public void addEdge(E source, E destination, double weight) {
        adjacencyMap.computeIfAbsent(source, v -> new HashSet<>()).add(new Connection<>(destination, weight));
        vertexSet.add(source);
        vertexSet.add(destination);
    }

    public boolean containsVertex(E vertex) {
        return vertexSet.contains(vertex);
    }

    public Set<E> getVertexSet() {
        return Collections.unmodifiableSet(vertexSet);
    }

    public Path<E> getShortestPath(E source, E destination) {
        if (!vertexSet.contains(source) || !vertexSet.contains(destination)) {
            return new EmptyPath<>();
        }

        final Map<E, Connection<E>> nodeMap = dijkstra(source);
        if (!nodeMap.containsKey(destination)) {
            return new EmptyPath<>();
        }

        return buildPath(source, destination, nodeMap);
    }

    private Path<E> buildPath(E source, E destination, Map<E, Connection<E>> nodeMap) {
        final Path<E> result = new ConcretePath<>();

        E current = destination;
        while (!source.equals(current)) {
            result.prependToPath(current);
            Connection<E> node = nodeMap.get(current);
            if (node == null) {
                return new EmptyPath<>();
            }
            current = node.connection();
        }

        return result.prependToPath(source).setCost(nodeMap.get(destination).weight());
    }

    private Map<E, Connection<E>> dijkstra(E source) {
        final Set<E> knownSet = new HashSet<>(vertexSet.size());
        final Map<E, Connection<E>> connectionMap = new HashMap<>(vertexSet.size());
        final Queue<Connection<E>> priorityQueue = new PriorityQueue<>(vertexSet.size(), new Connection<>());

        if (!adjacencyMap.containsKey(source)) {  // If the source doesn't have any edges
            return connectionMap;
        }

        for (E vertex : vertexSet) {
            connectionMap.put(vertex, new Connection<>(null, Double.POSITIVE_INFINITY));
        }
        connectionMap.put(source, new Connection<>(null, 0d));
        priorityQueue.add(new Connection<>(source, 0d));

        while (knownSet.size() != vertexSet.size() && !priorityQueue.isEmpty()) {
            final E value = priorityQueue.remove().connection();

            if (!knownSet.contains(value)) {
                knownSet.add(value);
                processNeighbours(value, knownSet, connectionMap, priorityQueue);
            }
        }

        return connectionMap;
    }

    private void processNeighbours(E value, Set<E> knownSet, Map<E, Connection<E>> nodeMap, Queue<Connection<E>> queue) {
        final Set<Connection<E>> connections = adjacencyMap.getOrDefault(value, Collections.emptySet());
        for (Connection<E> connection : connections) {
            if (!knownSet.contains(connection.connection())) {
                final double edgeDistance = connection.weight();
                final double newDistance = nodeMap.get(value).weight() + edgeDistance;

                if (newDistance < nodeMap.get(connection.connection()).weight()) {
                    nodeMap.put(connection.connection(), new Connection<>(value, newDistance));
                }

                queue.add(new Connection<>(connection.connection(), nodeMap.get(connection.connection()).weight()));
            }
        }
    }

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner("\n");

        for (Map.Entry<E, Set<Connection<E>>> entry : adjacencyMap.entrySet()) {
            for (Connection<E> c : entry.getValue()) {
                joiner.add("%s -> %s with weight of %.3f".formatted(entry.getKey(), c.connection(), c.weight()));
            }
        }

        return joiner.toString();
    }
}
