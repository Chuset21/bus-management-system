package bus_system;

import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;

import java.util.Scanner;

public final class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String RAINBOW_MESSAGE;

    static {
        final StringBuilder stringBuilder = new StringBuilder();
        final String rawText = "Your console supports ansi colors";

        for (int i = 0; i < rawText.length(); i++) {
            stringBuilder.append("\033[0;9%dm".formatted(i % 6 + 1)).append(rawText.charAt(i));
        }

        RAINBOW_MESSAGE = stringBuilder.append(ConsoleColor.RESET.getValue()).toString();
    }

    public static void main(String[] args) {
        System.out.printf("""
                %s
                If the above text has no funny characters and you'd like colors please input 'y', input anything else otherwise:\040""",
                RAINBOW_MESSAGE);
        ConsoleColor.supportsAnsi = "y".equalsIgnoreCase(SCANNER.nextLine().trim());
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
}
