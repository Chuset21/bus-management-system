package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.ansi.ConsoleColors;

import java.util.List;

public final class Exit implements Command {
    public final static List<String> ALIASES = List.of("-e", "--exit");

    @Override
    public void execute(String... strings) {
        System.out.printf(
                "%s%n", ConsoleColors.colorize(ConsoleColors.WHITE_BRIGHT, "Thank you for using this program!"));
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return """
                    exit    %s          %s
                """.formatted(
                ConsoleColors.colorize(ConsoleColors.YELLOW_BOLD, ALIASES.toString()),
                ConsoleColors.colorize(ConsoleColors.CYAN, "Exits the program."));
    }
}
