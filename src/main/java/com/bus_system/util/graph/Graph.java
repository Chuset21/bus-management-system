package com.bus_system.util.graph;

import com.bus_system.util.path.ConcretePath;
import com.bus_system.util.path.EmptyPath;
import com.bus_system.util.path.Path;

import java.util.*;

public class Graph<E> {
    private final Map<E, Set<Edge<E>>> adjacencyMap = new HashMap<>();
    private final Set<E> vertexSet = new HashSet<>();

    public void addEdge(E source, E destination, double weight) {
        adjacencyMap.computeIfAbsent(source, v -> new HashSet<>()).add(new Edge<>(source, destination, weight));
        vertexSet.add(source);
        vertexSet.add(destination);
    }

    private static class Node<E> implements Comparator<Node<E>> {
        public final E prev;
        public final double cost;

        public Node() {
            this(null, -1);
        }

        public Node(E prev, double cost) {
            this.prev = prev;
            this.cost = cost;
        }

        @Override
        public int compare(Node<E> node1, Node<E> node2) {
            return Double.compare(node1.cost, node2.cost);
        }
    }

    public Set<E> getVertexSet() {
        return Collections.unmodifiableSet(vertexSet);
    }

    public Path<E> getShortestPath(E source, E destination) {
        if (!vertexSet.contains(source) || !vertexSet.contains(destination)) {
            return new EmptyPath<>();
        }

        final Map<E, Node<E>> nodeMap = dijkstra(source);
        if (!nodeMap.containsKey(destination)) {
            return new EmptyPath<>();
        }

        return buildPath(source, destination, nodeMap);
    }

    private Path<E> buildPath(E source, E destination, Map<E, Node<E>> nodeMap) {
        final Path<E> result = new ConcretePath<>();

        E current = destination;
        while (!source.equals(current)) {
            result.prependToPath(current);
            Node<E> node = nodeMap.get(current);
            if (node == null) {
                return new EmptyPath<>();
            }
            current = node.prev;
        }

        return result.prependToPath(source).setCost(nodeMap.get(destination).cost);
    }

    private Map<E, Node<E>> dijkstra(E source) {
        final Set<E> knownSet = new HashSet<>(vertexSet.size());
        final Map<E, Node<E>> nodeMap = new HashMap<>(vertexSet.size());
        final Queue<Node<E>> priorityQueue = new PriorityQueue<>(vertexSet.size(), new Node<>());

        if (!adjacencyMap.containsKey(source)) {  // If the source doesn't have any edges
            return nodeMap;
        }

        for (E vertex : vertexSet) {
            nodeMap.put(vertex, new Node<>(null, Double.POSITIVE_INFINITY));
        }
        nodeMap.put(source, new Node<>(null, 0d));
        priorityQueue.add(new Node<>(source, 0d));

        while (knownSet.size() != vertexSet.size() && !priorityQueue.isEmpty()) {
            final E val = priorityQueue.remove().prev;

            if (!knownSet.contains(val)) {
                knownSet.add(val);
                processNeighbours(val, knownSet, nodeMap, priorityQueue);
            }
        }

        return nodeMap;
    }

    private void processNeighbours(E value, Set<E> knownSet, Map<E, Node<E>> nodeMap, Queue<Node<E>> queue) {
        double edgeDistance;
        double newDistance;

        final Set<Edge<E>> edges = adjacencyMap.getOrDefault(value, Collections.emptySet());
        for (Edge<E> edge : edges) {
            if (!knownSet.contains(edge.destination())) {
                edgeDistance = edge.weight();
                newDistance = nodeMap.get(value).cost + edgeDistance;

                if (newDistance < nodeMap.get(edge.destination()).cost) {
                    nodeMap.put(edge.destination(), new Node<>(edge.source(), newDistance));
                }

                queue.add(new Node<>(edge.destination(), nodeMap.get(edge.destination()).cost));
            }
        }
    }

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner("\n");

        for (Map.Entry<E, Set<Edge<E>>> entry : adjacencyMap.entrySet()) {
            for (Edge<E> e : entry.getValue()) {
                joiner.add("%s -> %s with weight of %.3f".formatted(e.source(), e.destination(), e.weight()));
            }
        }

        return joiner.toString();
    }
}
