package com.bus_system.util.graph;

import java.util.LinkedList;
import java.util.List;

final class Path<E> {
    private final List<E> pathTaken;
    private double totalCost;

    public Path() {
        pathTaken = new LinkedList<>();
        totalCost = 0;
    }

    public void addToPath(E nodeToAdd, double costToAdd) {
        addNode(nodeToAdd);
        addCost(costToAdd);
    }

    private void addNode(E nodeToAdd) {
        pathTaken.add(nodeToAdd);
    }

    private void addCost(double costToAdd) {
        totalCost += totalCost;
    }

    public void setCost(double cost) {
        this.totalCost = cost;
    }

    public List<E> getPathTaken() {
        return pathTaken;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
