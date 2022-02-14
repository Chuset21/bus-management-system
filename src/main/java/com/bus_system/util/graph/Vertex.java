package com.bus_system.util.graph;

import java.util.HashSet;
import java.util.Set;

final class Vertex<E> {
    private final E value;
    private final Set<Edge<E>> edges;

    Vertex(E value) {
        this.value = value;
        edges = new HashSet<>();
    }

    public E getValue() {
        return value;
    }

    public Set<Edge<E>> getEdges() {
        return edges;
    }

    public void addEdge(Edge<E> edge) {
        edges.add(edge);
    }

    public void removeEdge(Edge<E> edge) {
        edges.remove(edge);
    }
}
