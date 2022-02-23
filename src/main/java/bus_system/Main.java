package bus_system;

import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT,
                "Loading application, please wait and don't input anything as your inputs will be buffered.\n"));
        CommandExecutor.execute("help");

        final Scanner scanner = new Scanner(System.in);
        int returnCode;
        do {
            final List<String> tokens = Arrays.stream(scanner.nextLine().split("[\s\t]+")).map(String::trim).toList();
            returnCode = CommandExecutor.execute(tokens.get(0), tokens.subList(1, tokens.size()).toArray(new String[0]));
        } while (returnCode == 0);

        scanner.close();
    }
}
