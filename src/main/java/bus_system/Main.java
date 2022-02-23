package bus_system;

import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT,
                "Loading application, please wait and don't input anything as your inputs will be buffered.\n"));
        CommandExecutor.execute("help");

        final Scanner scanner = new Scanner(System.in);
        int returnCode;
        do {
            System.out.print(ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT,"Insert command: "));
            returnCode = CommandExecutor.execute(scanner.nextLine());
        } while (returnCode == 0);

        scanner.close();
    }
}
