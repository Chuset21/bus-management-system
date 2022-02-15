package com.bus_system.util.path;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ConcretePath<E> implements Path<E> {
    private final List<E> pathTaken;
    private double totalCost;

    public ConcretePath() {
        pathTaken = new LinkedList<>();
        totalCost = 0;
    }

    @Override
    public Path<E> addToPath(E nodeToAdd, double costToAdd) {
        addNode(nodeToAdd);
        addCost(costToAdd);
        return this;
    }

    private void addNode(E nodeToAdd) {
        pathTaken.add(nodeToAdd);
    }

    @Override
    public boolean isEmpty() {
        return pathTaken.isEmpty();
    }

    private void addCost(double costToAdd) {
        totalCost += totalCost;
    }

    @Override
    public Path<E> setCost(double cost) {
        this.totalCost = cost;
        return this;
    }

    @Override
    public List<E> getPathTaken() {
        return pathTaken;
    }

    @Override
    public Optional<Double> getTotalCost() {
        return Optional.of(totalCost);
    }
}
