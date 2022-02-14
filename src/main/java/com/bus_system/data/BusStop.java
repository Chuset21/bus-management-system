package com.bus_system.data;

import com.bus_system.util.Pretty;

import java.util.Optional;

import static com.bus_system.util.OptionalOps.getStringValue;
import static com.bus_system.util.Parser.*;

public record BusStop(Optional<Integer> stopID, Optional<Integer> stopCode, Optional<String> stopName,
                      Optional<String> stopDescription, Optional<Double> stopLatitude, Optional<Double> stopLongitude,
                      Optional<String> zoneID, Optional<String> stopUrl, Optional<Integer> locationType,
                      Optional<String> parentStation) implements Pretty {

    public BusStop(String stopID, String stopCode, String stopName, String stopDescription, String stopLatitude,
                   String stopLongitude, String zoneID, String stopUrl, String locationType, String parentStation) {
        this(parseIfPositive(stopID, Integer::parseInt), parseIfPositive(stopCode, Integer::parseInt),
                validate(stopName), validate(stopDescription), parse(stopLatitude, Double::parseDouble),
                parse(stopLongitude, Double::parseDouble), validate(zoneID), validate(stopUrl),
                parseIfPositive(locationType, Integer::parseInt), validate(parentStation));
    }

    @Override
    public String toPrettyString() {
        return ("""
                {
                    Stop ID: %s,
                    Stop Code: %s,
                    Stop Name: %s,
                    Stop Description: %s,
                    Stop Latitude: %s,
                    Stop Longitude: %s,
                    Zone ID: %s,
                    Stop URL: %s,
                    Location Type: %s,
                    Parent station: %s
                }""").
                formatted(getStringValue(stopID), getStringValue(stopCode), getStringValue(stopName),
                        getStringValue(stopDescription), getStringValue(stopLatitude), getStringValue(stopLongitude),
                        getStringValue(zoneID), getStringValue(stopUrl), getStringValue(locationType),
                        getStringValue(parentStation));
    }
}
