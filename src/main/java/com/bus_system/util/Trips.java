package com.bus_system.util;

import java.io.*;
import java.util.*;

public class Trips {
    private final Map<Integer, List<TripSegment>> tripSegments;

    public Trips(String filePath) throws IOException {
        tripSegments = new TreeMap<>(); // Provides ordering by key
        getDataFromFile(filePath);
        tripSegments.forEach((k, v) -> Collections.sort(v)); // Sort by time
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
                this.tripSegments.compute(tripSegment.tripID().get(), (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(tripSegment);
                    return v;
                });
            }
        }

        reader.close();
    }
}
