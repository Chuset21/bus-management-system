package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.ansi.ConsoleColor;

import java.util.List;

public final class SearchTrips implements Command {
    public final static List<String> ALIASES = List.of("st", "search-trips");

    @Override
    public int execute(String... strings) {  // TODO Implement
        if (strings.length != 1) {
            throw new IllegalArgumentException("Please provide one argument in the form 'hh:mm:ss'");
        }


        return 0;
    }


    @Override
    public String getDescription() {
        return """
                    search trips    %s      %s
                        Arguments:  %s
                \t\tExample:    %s""".formatted(
                ConsoleColor.colorize(ConsoleColor.YELLOW_BOLD, ALIASES.toString()),
                ConsoleColor.colorize(ConsoleColor.CYAN,
                        "Searches for all trips with a given arrival time, returning full details of all trips matching the criteria."),
                ConsoleColor.colorize(ConsoleColor.PURPLE_BRIGHT, "<arrival time> in the format 'hh:mm:ss'"),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " 23:23:02")
        );
    }
}