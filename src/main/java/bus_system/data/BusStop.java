package bus_system.data;

import bus_system.command.ansi.ConsoleColor;
import bus_system.util.OptionalOps;

import java.util.Optional;

import static bus_system.util.Parser.*;

public record BusStop(Optional<Integer> stopID, Optional<Integer> stopCode, Optional<String> stopName,
                      Optional<String> stopDescription, Optional<Double> stopLatitude, Optional<Double> stopLongitude,
                      Optional<String> zoneID, Optional<String> stopUrl, Optional<Integer> locationType,
                      Optional<String> parentStation) {

    public BusStop(String stopID, String stopCode, String stopName, String stopDescription, String stopLatitude,
                   String stopLongitude, String zoneID, String stopUrl, String locationType, String parentStation) {
        this(parseIfPositive(stopID, Integer::parseInt), parseIfPositive(stopCode, Integer::parseInt),
                validate(stopName), validate(stopDescription), parse(stopLatitude, Double::parseDouble),
                parse(stopLongitude, Double::parseDouble), validate(zoneID), validate(stopUrl),
                parseIfPositive(locationType, Integer::parseInt), validate(parentStation));
    }

    @Override
    public String toString() {
        return ("""
                %s
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
                %s""").
                formatted(
                        ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "{"),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(stopID)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(stopCode)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(stopName)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(stopDescription)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(stopLatitude)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(stopLongitude)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(zoneID)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(stopUrl)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(locationType)),
                        ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, OptionalOps.getStringValue(parentStation)),
                        ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "}"));
    }
}
