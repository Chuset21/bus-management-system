package bus_system.command.commands;

import bus_system.command.Command;
import bus_system.command.CommandExecutor;

import java.util.Set;
import java.util.stream.Collectors;

public class Help implements Command {
    public final static Set<String> aliases = Set.of("--help", "-h");

    @Override
    public void execute(String... strings) {
        System.out.println(getDescription());
    }

    @Override
    public String getDescription() {
        return """
                    help    %s    Available commands:%s
                """.formatted(aliases,
                CommandExecutor.getCommands().values().stream().
                        filter(command -> !(command instanceof Help)).
                        map(command -> "\n\n" + command.getDescription()).
                        collect(Collectors.joining()));
    }
}
