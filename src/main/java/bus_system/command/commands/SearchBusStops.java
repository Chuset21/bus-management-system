package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;
import bus_system.data.BusStop;

import java.util.List;
import java.util.stream.Collectors;

public class SearchBusStops implements Command {
    public final static List<String> ALIASES = List.of("ss", "search-stops");

    @Override
    public int execute(String... strings) {
        final List<BusStop> busStops;
        if (strings.length <= 0) {
             busStops = CommandExecutor.BUS_NETWORK.getMatchingStops("");
        } else {
            if (strings.length > 1) {
                throw new IllegalArgumentException("Please surround your argument in quotes like so \"%s\""
                        .formatted(String.join(" ", strings)));
            }

            busStops = CommandExecutor.BUS_NETWORK.getMatchingStops(strings[0]);
        }

        if (busStops.isEmpty()) {
            throw new IllegalArgumentException("Error, no stops match the given stop name portion.");
        }

        System.out.println(busStops.stream().map(Object::toString).collect(Collectors.joining(",\n")));

        return 0;
    }


    @Override
    public String getDescription() {
        return """
                    search bus stops    %s      %s
                        Optional arguments:  %s
                \t\tExamples:
                            Full Name:      %s
                            Partial Name:   %s
                            Empty Name:     %s""".formatted(
                ConsoleColor.colorize(ConsoleColor.YELLOW_BOLD, ALIASES.toString()),
                ConsoleColor.colorize(ConsoleColor.CYAN,
                        "Searches for bus stops matching a given starting portion of the name, ignores casing."),
                ConsoleColor.colorize(ConsoleColor.PURPLE_BRIGHT, "<stop name portion>"),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " \"FLAGSTOP SB ON WESTHILL DR\""),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " WATER"),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1))
        );
    }
}
