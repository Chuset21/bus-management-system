package bus_system.data;

import bus_system.util.OptionalOps;
import bus_system.util.Pretty;

import java.util.Optional;

import static bus_system.util.Parser.*;

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
                formatted(OptionalOps.getStringValue(stopID), OptionalOps.getStringValue(stopCode), OptionalOps.getStringValue(stopName),
                        OptionalOps.getStringValue(stopDescription), OptionalOps.getStringValue(stopLatitude), OptionalOps.getStringValue(stopLongitude),
                        OptionalOps.getStringValue(zoneID), OptionalOps.getStringValue(stopUrl), OptionalOps.getStringValue(locationType),
                        OptionalOps.getStringValue(parentStation));
    }
}
