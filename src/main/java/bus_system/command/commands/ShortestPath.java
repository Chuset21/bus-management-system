package bus_system.command.commands;

import bus_system.command.Command;

import java.util.List;

public final class ShortestPath implements Command {
    public final static List<String> ALIASES = List.of("-sp", "--shortest-path");

    @Override
    public void execute(String... strings) {  // TODO Implement

    }

    @Override
    public String getDescription() {
        return "Shortest path description";  // TODO Implement
    }
}
