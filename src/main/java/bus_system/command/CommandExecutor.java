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
//    private static final ErrorCommand ERROR = new ErrorCommand();

    static {
        COMMANDS = new HashMap<>();
        Help.ALIASES.forEach(a -> COMMANDS.put(a, new Help()));
    }

    public static Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(COMMANDS);
    }

//    public static void execute(String command, String... args) {
//        COMMANDS.getOrDefault(command, ERROR).execute(args);
//    }
}
