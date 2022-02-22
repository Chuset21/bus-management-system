package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColors;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Help implements Command {
    public final static List<String> ALIASES = List.of("-?", "-h", "--help");

    @Override
    public void execute(String... strings) {
        System.out.print(getDescription());
    }

    @Override
    public String getDescription() {
        return """
                %s
                
                    help    %s      %s%s
                """.formatted(
                ConsoleColors.colorize(ConsoleColors.GREEN_BOLD, "Available commands:"),
                ConsoleColors.colorize(ConsoleColors.YELLOW_BOLD, ALIASES.toString()),
                ConsoleColors.colorize(ConsoleColors.CYAN, "Lists all possible commands."),
                new HashSet<>(CommandExecutor.getCommands().values()).stream().
                        filter(command -> !(command instanceof Help)).
                        map(command -> "\n\n" + command.getDescription()).
                        collect(Collectors.joining()));
    }
}
