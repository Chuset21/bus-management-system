package bus_system;

import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;

import java.util.Scanner;

public final class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        ConsoleColor.forceAnsi = forceAnsi(args);

        System.out.println(ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT,
                "\nLoading application, please wait and don't input anything as your inputs will be buffered.\n"));
        CommandExecutor.execute("help");

        int returnCode;
        do {
            System.out.print(ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT,"Insert command: "));
            returnCode = CommandExecutor.execute(SCANNER.nextLine());
        } while (returnCode == 0);

        SCANNER.close();
    }

    private static boolean forceAnsi(String[] args) {
        if (args == null || args.length < 1) {
            return false;
        }

        return "force-ansi".equalsIgnoreCase(args[0].trim());
    }
}
