package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;
import bus_system.util.Parser;

import java.util.List;

public final class SearchTrips implements Command {
    public final static List<String> ALIASES = List.of("st", "search-trips");
    public static final String DESCRIPTION = """
                search trips    %s      %s
                    Arguments:  %s
            \t\tExamples:
                        Full format:        %s
                        Partial format:     %s""".formatted(
            ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, ALIASES.toString()),
            ConsoleColor.colorize(ConsoleColor.CYAN,
                    "Searches for all trips with a given arrival time, returning full details of all trips matching the criteria."),
            ConsoleColor.colorize(ConsoleColor.PURPLE_BRIGHT, "<arrival time> in the format 'hh:mm:ss'"),
            ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " 07:23:02"),
            ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " 7:23:2")
    );

    @Override
    public int execute(String... strings) {
        if (strings.length != 1) {
            throw new IllegalArgumentException("Please provide one argument in the form 'hh:mm:ss'");
        }

        if (!strings[0].matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            throw new IllegalArgumentException("Time must be in the format 'hh:mm:ss'");
        }

        final String validatedTime = Parser.mutateToValidTime(strings[0]);
        final String arrivalTimeTrips = CommandExecutor.BUS_NETWORK.findArrivalTimeTrips(validatedTime);
        if (arrivalTimeTrips.isBlank()) {
            throw new IllegalArgumentException("There are no trips arriving at '%s'".formatted(validatedTime));
        }

        System.out.println(arrivalTimeTrips);
        return 0;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}