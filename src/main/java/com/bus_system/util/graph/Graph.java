package com.bus_system.util.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<E> {
    private final Map<E, Set<Edge<E>>> adjacencyMap = new HashMap<>();

    public void addEdge(E source, E destination, double weight) {
        adjacencyMap.computeIfAbsent(source, v -> new HashSet<>()).add(new Edge<>(source, destination, weight));
    }

    @Override
    public String toString() {
        return adjacencyMap.entrySet().stream().map(e -> e.getKey().toString() + " -> " + e.getValue()).
                collect(Collectors.joining("\n"));
    }
}
