package com.bus_system.util;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Function;

public record TripSegment(Optional<Integer> tripID, Optional<LocalTime> arrivalTime, Optional<LocalTime> departureTime,
                          Optional<Integer> stopID, Optional<Integer> stopSequence, Optional<Integer> stopHeadsign,
                          Optional<Integer> pickupType, Optional<Integer> dropOffType,
                          Optional<BigDecimal> distTravelled) {

    public TripSegment(String tripID, String arrivalTime, String departureTime, String stopID, String stopSequence,
                       String stopHeadsign, String pickupType, String dropOffType, String distTravelled) {
        this(parse(tripID, Integer::parseInt), parse(arrivalTime, TripSegment::parseString),
                parse(departureTime, TripSegment::parseString), parse(stopID, Integer::parseInt),
                parse(stopSequence, Integer::parseInt), parse(stopHeadsign, Integer::parseInt),
                parse(pickupType, Integer::parseInt), parse(dropOffType, Integer::parseInt),
                parse(distTravelled, BigDecimal::new));
    }

    private static LocalTime parseString(String time) {
        try {
            return LocalTime.parse(time.length() != 8 ? '0' + time : time);
        } catch (DateTimeException e) {
            return null;
        }
    }

    private static <T> Optional<T> parse(String value, Function<String, T> function) {
        return value == null || value.isBlank() ? Optional.empty() : Optional.ofNullable(function.apply(value));
    }

    private static <T> String getStringValue(Optional<T> value) {
        return value.map(Object::toString).orElse("N/A");
    }

    @Override
    public String toString() {
        return "Trip ID: %s, Arrival Time: %s, Departure Time: %s, Stop ID: %s, Stop Sequence: %s, Stop Headsign: %s, Pickup Type: %s, Drop Off Type: %s, Distance Travelled: %s".
                formatted(getStringValue(tripID), getStringValue(arrivalTime), getStringValue(departureTime),
                        getStringValue(stopID), getStringValue(stopSequence), getStringValue(stopHeadsign),
                        getStringValue(pickupType), getStringValue(dropOffType), getStringValue(distTravelled));
    }
}
