package com.bus_system.util.graph;

import java.util.List;
import java.util.Optional;

interface Path<E> {
    Path<E> addToPath(E nodeToAdd, double costToAdd);
    Path<E> setCost(double cost);
    boolean isEmpty();
    List<E> getPathTaken();
    Optional<Double> getTotalCost();
}
