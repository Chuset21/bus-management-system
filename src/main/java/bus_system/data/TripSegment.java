package bus_system.data;

import bus_system.util.OptionalOps;
import bus_system.util.Parser;
import bus_system.util.Pretty;

import java.time.LocalTime;
import java.util.Optional;

public record TripSegment(Optional<Integer> tripID, Optional<LocalTime> arrivalTime, Optional<LocalTime> departureTime,
                          Optional<Integer> stopID, Optional<Integer> stopSequence, Optional<Integer> stopHeadsign,
                          Optional<Integer> pickupType, Optional<Integer> dropOffType,
                          Optional<Double> distTravelled) implements Pretty {

    public TripSegment(String tripID, String arrivalTime, String departureTime, String stopID, String stopSequence,
                       String stopHeadsign, String pickupType, String dropOffType, String distTravelled) {
        this(Parser.parseIfPositive(tripID, Integer::parseInt), Parser.parse(arrivalTime, Parser::parseTime),
                Parser.parse(departureTime, Parser::parseTime), Parser.parseIfPositive(stopID, Integer::parseInt),
                Parser.parseIfPositive(stopSequence, Integer::parseInt), Parser.parseIfPositive(stopHeadsign, Integer::parseInt),
                Parser.parseIfPositive(pickupType, Integer::parseInt), Parser.parseIfPositive(dropOffType, Integer::parseInt),
                Parser.parseIfPositive(distTravelled, Double::parseDouble));
    }

    @Override
    public String toPrettyString() {
        return ("""
                {
                    Trip ID: %s,
                    Arrival Time: %s,
                    Departure Time: %s,
                    Stop ID: %s,
                    Stop Sequence: %s,
                    Stop Headsign: %s,
                    Pickup Type: %s,
                    Drop Off Type: %s,
                    Distance Travelled: %s
                }""").
                formatted(OptionalOps.getStringValue(tripID), OptionalOps.getStringValue(arrivalTime), OptionalOps.getStringValue(departureTime),
                        OptionalOps.getStringValue(stopID), OptionalOps.getStringValue(stopSequence), OptionalOps.getStringValue(stopHeadsign),
                        OptionalOps.getStringValue(pickupType), OptionalOps.getStringValue(dropOffType), OptionalOps.getStringValue(distTravelled));
    }
}
