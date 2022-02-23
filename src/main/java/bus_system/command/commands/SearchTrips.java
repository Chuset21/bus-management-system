package bus_system.command.commands;

import bus_system.command.Command;

import java.util.List;

public final class SearchTrips implements Command {
    public final static List<String> ALIASES = List.of("st", "search-trips");

    @Override
    public int execute(String... strings) {  // TODO Implement
        return 0;
    }


    @Override
    public String getDescription() {
        return "Description of Search Trip"; // TODO Implement
    }
}