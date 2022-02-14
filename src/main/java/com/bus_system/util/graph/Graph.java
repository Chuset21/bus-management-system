package com.bus_system.util.graph;

import java.util.*;

public class Graph<E> {
    private final Map<E, Set<Edge<E>>> adjacencyMap = new HashMap<>();

    public void addEdge(E source, E destination, double weight) {
        adjacencyMap.compute(source, (k, v) -> {
            if (v == null) {
                v = new HashSet<>();
            }
            v.add(new Edge<>(source, destination, weight));
            return v;
        });
    }
}
