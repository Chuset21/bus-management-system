package bus_system.command;

import bus_system.command.commands.Help;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CommandExecutor {
    // Suppresses default constructor, ensuring non-instantiability.
    private CommandExecutor() {

    }

    private static final Map<String, Command> COMMANDS;

    static {
        COMMANDS = new HashMap<>();

        final Help help = new Help();
        Help.ALIASES.forEach(a -> COMMANDS.put(a, help));
    }

    public static Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(COMMANDS);
    }

    public static void execute(String commandName, String... args) {
        final Command command = COMMANDS.get(commandName);
        if (command == null) {
            System.err.println("Please provide a valid command.\nFor more information try '--help'.");
        } else {
            try {
                command.execute(args);
            } catch (Exception e) {  // This is how errors will be handled
                System.err.println(e.getMessage());
            }
        }
    }
}
