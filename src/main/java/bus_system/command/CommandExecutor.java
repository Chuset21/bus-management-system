package bus_system.command;

import bus_system.command.ansi.ConsoleColor;
import bus_system.command.commands.Exit;
import bus_system.command.commands.Help;
import bus_system.command.commands.SearchBusStops;
import bus_system.command.commands.ShortestPath;
import bus_system.data.BusNetwork;

import java.io.IOException;
import java.util.*;

public final class CommandExecutor {
    private static final String ERROR_MESSAGE =
            ConsoleColor.colorize(ConsoleColor.RED_BOLD, "Error, please provide a valid command.\n") +
            "To see all valid commands try " +
            ConsoleColor.colorize(ConsoleColor.YELLOW_BOLD, "'--help'");
    public static final BusNetwork BUS_NETWORK;

    static {
        try {
            BUS_NETWORK = new BusNetwork("src/main/resources/stop_times.txt",
                    "src/main/resources/stops.txt", "src/main/resources/transfers.txt");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load bus network.");
        }
    }

    private static final Map<String, Command> COMMANDS;

    static {
        COMMANDS = new HashMap<>();

        final Help help = new Help();
        Help.ALIASES.forEach(a -> COMMANDS.put(a, help));

        final Exit exit = new Exit();
        Exit.ALIASES.forEach(a -> COMMANDS.put(a, exit));

        final ShortestPath shortestPath = new ShortestPath();
        ShortestPath.ALIASES.forEach(a -> COMMANDS.put(a, shortestPath));

        final SearchBusStops searchBusStops = new SearchBusStops();
        SearchBusStops.ALIASES.forEach(a -> COMMANDS.put(a, searchBusStops));
    }

    // Suppresses default constructor, ensuring non-instantiability.
    private CommandExecutor() {

    }

    public static Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(COMMANDS);
    }

    public static int execute(String commandName, String... args) {
        final Command command = COMMANDS.get(commandName);
        if (command == null) {
            System.out.println(ERROR_MESSAGE);
        } else {
            try {
                return command.execute(args);
            } catch (Exception e) {  // This is how errors will be handled
                System.out.println(ConsoleColor.colorize(ConsoleColor.RED_BOLD, e.getMessage()));
            }
        }
        return 0;
    }

    public static int execute(String string) {
        // Splits the string by spaces unless surrounded by quotation marks
        final List<String> tokens = Arrays.stream(string.split("[\\s\\t]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)")).
                map(s -> s.replace("\"", "")).map(String::trim).toList();
        return execute(tokens.get(0), tokens.subList(1, tokens.size()).toArray(new String[0]));
    }
}