package bus_system.data;

import bus_system.util.graph.Graph;
import bus_system.util.path.Path;
import bus_system.util.TernarySearchTree;

import java.io.*;
import java.util.*;

public class BusNetwork {
    private final Graph<BusStop> stopGraph = new Graph<>();
    private final TernarySearchTree searchTree = new TernarySearchTree();
    private final Map<Integer, BusStop> stopMap = new HashMap<>();
    private final Trips trips;

    public BusNetwork(String stopTimesFilePath, String stopFilePath, String transferFilePath) throws IOException {
        trips = new Trips(stopTimesFilePath);
        getStopsFromFile(stopFilePath);
        getTransferFromFile(transferFilePath);
        addEdgesFromTrips();
    }

    private void getStopsFromFile(String stopFilePath) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(stopFilePath));

        // Skip the first line containing the names of each value
        reader.readLine();
        String row;
        while ((row = reader.readLine()) != null) {
            final List<String> values = Arrays.stream(row.split(",")).map(String::trim).toList();

            final String stopName = moveInfo(values.get(2));
            final BusStop stop = new BusStop(values.get(0), values.get(1), stopName, values.get(3), values.get(4),
                    values.get(5), values.get(6), values.get(7), values.get(8), values.size() == 10 ? values.get(9) : "");

            if (stop.stopID().isPresent()) {
                addStop(stop);
                searchTree.insert(stopName, stop.stopID().get());
            }
        }

        reader.close();
    }

    private void getTransferFromFile(String transferFilePath) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(transferFilePath));

        // Skip the first line containing the names of each value
        reader.readLine();
        String row;
        while ((row = reader.readLine()) != null) {
            final List<String> values = Arrays.stream(row.split(",")).map(String::trim).toList();

            stopGraph.addEdge(stopMap.get(Integer.parseInt(values.get(0))), stopMap.get(Integer.parseInt(values.get(1))),
                    "0".equals(values.get(2).trim()) ? 2 : Integer.parseInt(values.get(3)) / 100d);
        }

        reader.close();
    }

    private void addEdgesFromTrips() {
        final Map<Integer, List<TripSegment>> tripSegmentsMap = trips.getTripSegments();

        for (List<TripSegment> tripSegments : tripSegmentsMap.values()) {
            for (int i = 1; i < tripSegments.size(); i++) {
                final Optional<Integer> fromTS = tripSegments.get(i - 1).stopID();
                final Optional<Integer> toTS = tripSegments.get(i).stopID();

                if (fromTS.isPresent() && toTS.isPresent()) {
                    stopGraph.addEdge(stopMap.get(fromTS.get()), stopMap.get(toTS.get()), 1);
                }
            }
        }
    }

    private void addStop(BusStop stop) {
        if (stop.stopID().isPresent()) {
            stopMap.put(stop.stopID().get(), stop);
        }
    }

    private String moveInfo(String input) {
        final String substring = input.substring(0, 2).trim();
        final String lowerCaseSub = substring.toLowerCase(Locale.ROOT);

        if (lowerCaseSub.equals("eb") || lowerCaseSub.equals("sb") ||
            lowerCaseSub.equals("nb") || lowerCaseSub.equals("wb")) {
            return input.substring(3).trim() + " " + substring;
        }

        return input;
    }

    public List<List<TripSegment>> searchByArrivalTime(String arrivalTime) {
        return trips.searchByArrivalTime(arrivalTime);
    }

    public String findArrivalTimeTrips(String arrivalTime) {
        return trips.findArrivalTimeTrips(arrivalTime);
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
