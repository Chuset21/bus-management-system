package bus_system.command;

public interface Command {
    void execute(String... strings);
    String getDescription();
}
