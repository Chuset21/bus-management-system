package com.bus_system.util.graph;

import java.util.*;

public class Graph<E> {
    private final Map<E, Set<Edge<E>>> adjacencyMap = new HashMap<>();

    public void addEdge(E source, E destination, double weight) {
        adjacencyMap.computeIfAbsent(source, v -> new HashSet<>()).add(new Edge<>(source, destination, weight));
    }

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner("\n");

        for (Map.Entry<E, Set<Edge<E>>> entry : adjacencyMap.entrySet()) {
            for (Edge<E> e : entry.getValue()) {
                joiner.add("%s -> %s with weight of %.3f".formatted(e.source().value(), e.destination().value(), e.weight()));
            }
        }

        return joiner.toString();
    }
}
