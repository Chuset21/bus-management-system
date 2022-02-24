package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;

import java.util.List;
import java.util.stream.Collectors;

public final class Help implements Command {
    public final static List<String> ALIASES = List.of("?", "h", "help");
    private static String DESCRIPTION = null;

    @Override
    public int execute(String... strings) {
        System.out.print(getDescription());
        return 0;
    }

    @Override
    public String getDescription() {
        if (DESCRIPTION == null) {
            DESCRIPTION = """
                    %s
                                    
                        help    %s      %s
                        
                        %s
                    """.formatted(
                    ConsoleColor.colorize(ConsoleColor.GREEN_BOLD, "Available commands:"),
                    ConsoleColor.colorize(ConsoleColor.YELLOW_BRIGHT, ALIASES.toString()),
                    ConsoleColor.colorize(ConsoleColor.CYAN, "Lists all possible commands."),
                    CommandExecutor.COMMANDS.values().stream().distinct().
                            filter(command -> !(command instanceof Help)).
                            map(Command::getDescription).
                            collect(Collectors.joining("\n\n")));
        }

        return DESCRIPTION;
    }
}
