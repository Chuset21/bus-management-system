package com.bus_system.util.graph;

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

    public Set<E> getVertexSet() {
        return Collections.unmodifiableSet(vertexSet);
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
