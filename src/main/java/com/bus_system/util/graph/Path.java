package com.bus_system.util.graph;

import java.util.List;

interface Path<E> {
    Path<E> addToPath(E nodeToAdd, double costToAdd);
    Path<E> setCost(double cost);
    boolean isEmpty();
    List<E> getPathTaken();
    double getTotalCost();
}
