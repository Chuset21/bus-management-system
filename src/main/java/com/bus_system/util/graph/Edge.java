package com.bus_system.util.graph;

record Edge<E>(Vertex<E> source, Vertex<E> destination, double weight) implements Comparable<Edge<E>> {
    @Override
    public int compareTo(Edge<E> o) {
        if (!this.source.equals(o.source)) {
            throw new UnsupportedOperationException("Cannot compare two edges with different sources");
        }
        return Double.compare(this.weight, o.weight);
    }
}
