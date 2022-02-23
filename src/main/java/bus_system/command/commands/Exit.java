package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.ansi.ConsoleColor;

import java.util.List;

public final class Exit implements Command {
    public final static List<String> ALIASES = List.of("-e", "--exit");

    @Override
    public void execute(String... strings) {
        System.out.printf(
                "%s%n", ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "Thank you for using this program!"));
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "\texit    %s          %s".formatted(
                ConsoleColor.colorize(ConsoleColor.YELLOW_BOLD, ALIASES.toString()),
                ConsoleColor.colorize(ConsoleColor.CYAN, "Exits the program."));
    }
}
