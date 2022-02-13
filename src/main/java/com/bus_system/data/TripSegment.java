package com.bus_system.data;

import com.bus_system.util.Pretty;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Optional;

import static com.bus_system.util.OptionalOps.getStringValue;
import static com.bus_system.util.Parser.parse;

public record TripSegment(Optional<Integer> tripID, Optional<LocalTime> arrivalTime, Optional<LocalTime> departureTime,
                          Optional<Integer> stopID, Optional<Integer> stopSequence, Optional<Integer> stopHeadsign,
                          Optional<Integer> pickupType, Optional<Integer> dropOffType,
                          Optional<Double> distTravelled) implements Pretty {

    public TripSegment(String tripID, String arrivalTime, String departureTime, String stopID, String stopSequence,
                       String stopHeadsign, String pickupType, String dropOffType, String distTravelled) {
        this(parse(tripID, Integer::parseInt), parse(arrivalTime, TripSegment::parseTime),
                parse(departureTime, TripSegment::parseTime), parse(stopID, Integer::parseInt),
                parse(stopSequence, Integer::parseInt), parse(stopHeadsign, Integer::parseInt),
                parse(pickupType, Integer::parseInt), parse(dropOffType, Integer::parseInt),
                parse(distTravelled, Double::parseDouble));
    }

    private static LocalTime parseTime(String time) {
        try {
            return LocalTime.parse(time.length() != 8 ? '0' + time : time);
        } catch (DateTimeException e) {
            return null;
        }
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
