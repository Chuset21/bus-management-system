package com.bus_system.data;

import com.bus_system.util.TernarySearchTree;
import com.bus_system.util.graph.Graph;
import com.bus_system.util.path.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusNetwork {
    private final Graph<BusStop> stopGraph = new Graph<>();
    private final TernarySearchTree searchTree = new TernarySearchTree();
    private final Map<Integer, BusStop> stopMap = new HashMap<>();

    public BusNetwork() {
        // TODO
    }

    public Path<BusStop> getShortestPath(BusStop fromStop, BusStop toStop) {
        return stopGraph.getShortestPath(fromStop, toStop);
    }

    public Path<BusStop> getShortestPath(Integer fromStopID, Integer toStopID) {
        return getShortestPath(stopMap.get(fromStopID), stopMap.get(toStopID));
    }

    public boolean containsStop(BusStop stop) {
        return stopGraph.containsVertex(stop);
    }

    public boolean containsStop(Integer stopID) {
        return containsStop(stopMap.get(stopID));
    }

    public List<BusStop> getMatchingStops(String stopName) {
        final List<Integer> stopIDs = searchTree.search(stopName);
        final List<BusStop> result = new ArrayList<>(stopIDs.size());

        for (int id : stopIDs) {
            result.add(stopMap.get(id));
        }

        return result;
    }
}
