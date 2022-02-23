package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;

import java.util.List;

public final class ShortestPath implements Command {
    public final static List<String> ALIASES = List.of("-sp", "--shortest-path");

    @Override
    public void execute(String... strings) {  // TODO Implement fully
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

        if (!CommandExecutor.BUS_NETWORK.containsStop(fromStop)) {
            throw new IllegalArgumentException("Source stop '%d' doesn't exist in the bus network.".formatted(fromStop));
        } else if (!CommandExecutor.BUS_NETWORK.containsStop(destStop)) {
            throw new IllegalArgumentException("Destination stop '%d' doesn't exist in the bus network.".formatted(destStop));
        }
    }

    @Override
    public String getDescription() {
        return """
                    shortest path   %s      %s
                        Arguments:  %s
                \t\tExample:    %s""".formatted(
                ConsoleColor.colorize(ConsoleColor.YELLOW_BOLD, ALIASES.toString()),
                ConsoleColor.colorize(ConsoleColor.CYAN, "Finds the shortest path between two given stops."),
                ConsoleColor.colorize(ConsoleColor.PURPLE_BRIGHT, "<source stop>  <destination stop>"),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " 646 379")
        );
    }
}
