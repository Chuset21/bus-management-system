package com.bus_system.data;

import java.io.*;
import java.util.*;

public class Trips {

    private static final Comparator<TripSegment> TRIP_SEGMENT_COMPARABLE = (o1, o2) -> {
        if (o1.arrivalTime().isEmpty()) {
            return o2.arrivalTime().isEmpty() ? 0 : -1;
        } else if (o2.arrivalTime().isEmpty()) {
            return 1;
        }
        return o1.arrivalTime().get().compareTo(o2.arrivalTime().get());
    };
    private final Map<Integer, List<TripSegment>> tripSegments;

    public Trips(String filePath) throws IOException {
        tripSegments = new TreeMap<>(); // Provides ordering by key
        getDataFromFile(filePath);
        tripSegments.forEach((k, v) -> v.sort(TRIP_SEGMENT_COMPARABLE)); // Sort by time
    }

    private void getDataFromFile(String filePath) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Skip the first line containing the names of each value
        reader.readLine();
        String row;
        while ((row = reader.readLine()) != null) {
            final List<String> values = Arrays.stream(row.split(",")).map(String::trim).toList();
            final TripSegment tripSegment = new TripSegment(values.get(0), values.get(1), values.get(2), values.get(3),
                    values.get(4), values.get(5), values.get(6), values.get(7), values.size() == 9 ? values.get(8) : "");

            if (tripSegment.arrivalTime().isPresent() && tripSegment.departureTime().isPresent() &&
                    tripSegment.tripID().isPresent()) {
                this.tripSegments.computeIfAbsent(tripSegment.tripID().get(), v -> new ArrayList<>()).add(tripSegment);
            }
        }

        reader.close();
    }

    public List<TripSegment> searchByArrivalTime(String arrivalTime) {
        if (arrivalTime == null) {
            return null;
        }
        final List<TripSegment> result = new ArrayList<>();

        for (List<TripSegment> tripSegment : tripSegments.values()) {
            final int index = Collections.binarySearch(tripSegment, new TripSegment("", arrivalTime, "00:00:00",
                            "", "", "", "", "", ""), TRIP_SEGMENT_COMPARABLE);
            if (index >= 0) {
                result.add(tripSegment.get(index));
            }
        }

        return result;
    }
}
