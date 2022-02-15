package com.bus_system.util.graph;

import java.util.Collections;
import java.util.List;

class EmptyPath<E> implements Path<E>{
    @Override
    public Path<E> addToPath(E nodeToAdd, double costToAdd) {
        final Path<E> result = new ConcretePath<>();
        return result.addToPath(nodeToAdd, costToAdd);
    }

    @Override
    public Path<E> setCost(double cost) {
        final Path<E> result = new ConcretePath<>();
        return result.setCost(cost);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public List<E> getPathTaken() {
        return Collections.emptyList();
    }

    @Override
    public double getTotalCost() {
        return 0;
    }
}
