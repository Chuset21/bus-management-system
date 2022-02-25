package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.ansi.ConsoleColor;

import java.util.List;

public final class Exit implements Command {
    public final static List<String> ALIASES = List.of("e", "exit");
    public static final String DESCRIPTION = "exit    %s         %s".formatted(
            ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, ALIASES.toString()),
            ConsoleColor.colorize(ConsoleColor.CYAN, "Exits the program."));

    @Override
    public int execute(String... strings) {
        System.out.printf(
                "%s%n", ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "Thank you for using this program!"));
        return 1;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
