package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.ansi.ConsoleColor;

import java.util.List;

public class SearchBusStops implements Command {
    public final static List<String> ALIASES = List.of("sb", "search-stop");

    @Override
    public int execute(String... strings) {  // TODO Implement
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
                        "Searches for a bus stop given a starting portion of the name, ignores casing."),
                ConsoleColor.colorize(ConsoleColor.PURPLE_BRIGHT, "<stop name portion>"),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " \"FLAGSTOP SB ON WESTHILL DR\""),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1) + " WATER"),
                ConsoleColor.colorize(ConsoleColor.BLUE_BRIGHT, ALIASES.get(ALIASES.size() - 1))
        );
    }
}
