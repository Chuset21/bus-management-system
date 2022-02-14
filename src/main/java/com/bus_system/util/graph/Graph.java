package com.bus_system.util.graph;

import java.util.*;

public class Graph<E> {
    private final Map<E, Set<Vertex<E>>> vertexMap = new HashMap<>();

    public void addVertex(E source) {
        vertexMap.compute(source, (k, set) -> {
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(new Vertex<>(source));
            return set;
        });
    }

    public void addEdge(E source, E destination, double weight) {

    }
}
