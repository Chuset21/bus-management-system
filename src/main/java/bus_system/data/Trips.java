package bus_system.data;

import bus_system.command.ansi.ConsoleColor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

final class Trips {
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
        final BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream(filePath))));

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

    Map<Integer, List<TripSegment>> getTripSegments() {
        return Collections.unmodifiableMap(tripSegments);
    }

    public List<List<TripSegment>> searchByArrivalTime(String arrivalTime) {
        if (arrivalTime == null) {
            return null;
        }
        final List<List<TripSegment>> result = new ArrayList<>();

        for (List<TripSegment> tripSegment : tripSegments.values()) {
            final int index = Collections.binarySearch(tripSegment, new TripSegment("", arrivalTime, "00:00:00",
                    "", "", "", "", "", ""), TRIP_SEGMENT_COMPARABLE);
            if (index >= 0) {
                result.add(tripSegment);
            }
        }

        return result;
    }

    /**
     * String representation of {@link #searchByArrivalTime(String)}
     *
     * @param arrivalTime Arrival time to search for
     * @return String representation of the lists of TripSegment
     */
    public String findArrivalTimeTrips(String arrivalTime) {
        if (arrivalTime == null) {
            return null;
        }
        final StringJoiner result = new StringJoiner("\n\n");

        for (List<TripSegment> tripSegment : tripSegments.values()) {
            final int index = Collections.binarySearch(tripSegment, new TripSegment("", arrivalTime, "00:00:00",
                    "", "", "", "", "", ""), TRIP_SEGMENT_COMPARABLE);
            if (index >= 0) {
                result.add("%s: %s\n%s".formatted(
                        ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "Trip ID"),
                        ConsoleColor.colorize(
                                ConsoleColor.PURPLE_BRIGHT, tripSegment.get(index).tripID().orElse(-1).toString()),
                        tripSegment.stream().map(Object::toString).
                                map(s -> "\t" + s.replace("\n", "\n\t"))  // Adding tabs everywhere
                                .collect(Collectors.joining(",\n"))));
            }
        }

        return result.toString().
                replace("{", ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "{")).
                replace("}", ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "}"));
    }
}
