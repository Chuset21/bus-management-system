package bus_system.command;

public interface Command {
    int execute(String... strings);
    String getDescription();
}
