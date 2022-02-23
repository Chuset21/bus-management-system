package bus_system.command.commands;

import bus_system.command.Command;

import java.util.List;

public class SearchTrip implements Command {
    public final static List<String> ALIASES = List.of("-st", "--search-trip");

    @Override
    public void execute(String... strings) {  // TODO Implement

    }


    @Override
    public String getDescription() {
        return "Description of Search Trip"; // TODO Implement
    }
}