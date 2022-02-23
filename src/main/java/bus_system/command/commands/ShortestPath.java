package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;
import bus_system.data.BusStop;
import bus_system.util.path.Path;

import java.util.List;

public final class ShortestPath implements Command {
    public final static List<String> ALIASES = List.of("sp", "shortest-path");
    public static final String DESCRIPTION = """
                shortest path   %s      %s
                    Arguments:  %s
            \t\tExample:    %s""".formatted(
            ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, ALIASES.toString()),
            ConsoleColor.colorize(ConsoleColor.CYAN, "Finds the shortest path between two given stops."),
            ConsoleColor.colorize(ConsoleColor.PURPLE_BRIGHT, "<source stop>  <destination stop>"),
            ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " 646 379")
    );

    @Override
    public int execute(String... strings) {
        if (strings.length != 2) {
            throw new IllegalArgumentException("Error, please pass exactly 2 stop numbers.");
        }

        final int fromStop;
        final int destStop ;
        try {
            fromStop = Integer.parseInt(strings[0]);
            destStop = Integer.parseInt(strings[1]);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Error, please make sure that both arguments are valid integers.");
        }

        if (fromStop == destStop) {
            throw new IllegalArgumentException("Error, source and destination cannot be the same.");
        }

        if (!CommandExecutor.BUS_NETWORK.containsStop(fromStop)) {
            throw new IllegalArgumentException("Source stop '%d' doesn't exist in the bus network.".formatted(fromStop));
        } else if (!CommandExecutor.BUS_NETWORK.containsStop(destStop)) {
            throw new IllegalArgumentException("Destination stop '%d' doesn't exist in the bus network.".formatted(destStop));
        }

        final Path<BusStop> path = CommandExecutor.BUS_NETWORK.getShortestPath(fromStop, destStop);
        if (path.isEmpty()) {
            throw new IllegalArgumentException(
                    "There is no path from stop '%d' to stop '%d'".formatted(fromStop, destStop));
        }

        System.out.println(path);
        return 0;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
