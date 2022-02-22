package bus_system.command;

import bus_system.command.ansi.ConsoleColors;
import bus_system.command.commands.Exit;
import bus_system.command.commands.Help;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CommandExecutor {
    private static final String ERROR_MESSAGE = ConsoleColors.colorize(ConsoleColors.RED_BOLD, "Error, please provide a valid command.\n") +
                                                "To see all valid commands try " +
                                                ConsoleColors.colorize(ConsoleColors.YELLOW_BOLD, "'--help'\n");

    private static final Map<String, Command> COMMANDS;

    // Suppresses default constructor, ensuring non-instantiability.
    private CommandExecutor() {

    }

    static {
        COMMANDS = new HashMap<>();

        final Help help = new Help();
        Help.ALIASES.forEach(a -> COMMANDS.put(a, help));

        final Exit exit = new Exit();
        Exit.ALIASES.forEach(a -> COMMANDS.put(a, exit));
    }

    public static Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(COMMANDS);
    }

    public static void execute(String commandName, String... args) {
        final Command command = COMMANDS.get(commandName);
        if (command == null) {
            System.out.println(ERROR_MESSAGE);
        } else {
            try {
                command.execute(args);
            } catch (Exception e) {  // This is how errors will be handled
                System.err.println(e.getMessage());
            }
        }
    }
}

class Test {
    public static void main(String[] args) {
        CommandExecutor.execute("-h");
        CommandExecutor.execute("8");
        CommandExecutor.execute("-e");
    }
}