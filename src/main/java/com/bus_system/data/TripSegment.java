package com.bus_system.data;

import com.bus_system.util.Parser;
import com.bus_system.util.Pretty;

import java.time.LocalTime;
import java.util.Optional;

import static com.bus_system.util.OptionalOps.getStringValue;
import static com.bus_system.util.Parser.parse;
import static com.bus_system.util.Parser.parseIfPositive;

public record TripSegment(Optional<Integer> tripID, Optional<LocalTime> arrivalTime, Optional<LocalTime> departureTime,
                          Optional<Integer> stopID, Optional<Integer> stopSequence, Optional<Integer> stopHeadsign,
                          Optional<Integer> pickupType, Optional<Integer> dropOffType,
                          Optional<Double> distTravelled) implements Pretty {

    public TripSegment(String tripID, String arrivalTime, String departureTime, String stopID, String stopSequence,
                       String stopHeadsign, String pickupType, String dropOffType, String distTravelled) {
        this(parseIfPositive(tripID, Integer::parseInt), parse(arrivalTime, Parser::parseTime),
                parse(departureTime, Parser::parseTime), parseIfPositive(stopID, Integer::parseInt),
                parseIfPositive(stopSequence, Integer::parseInt), parseIfPositive(stopHeadsign, Integer::parseInt),
                parseIfPositive(pickupType, Integer::parseInt), parseIfPositive(dropOffType, Integer::parseInt),
                parseIfPositive(distTravelled, Double::parseDouble));
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
                formatted(getStringValue(tripID), getStringValue(arrivalTime), getStringValue(departureTime),
                        getStringValue(stopID), getStringValue(stopSequence), getStringValue(stopHeadsign),
                        getStringValue(pickupType), getStringValue(dropOffType), getStringValue(distTravelled));
    }
}
