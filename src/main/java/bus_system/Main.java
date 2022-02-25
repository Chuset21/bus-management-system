package bus_system;

import bus_system.command.CommandExecutor;
import bus_system.command.ansi.ConsoleColor;

import java.util.Optional;
import java.util.Scanner;

public final class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ANSI_ENABLE_ARG = "enable-ansi";
    private static final String VALID_ARGUMENTS_MESSAGE = """
            Valid arguments include:
                %s=auto
                %s=true    or  %s=on
                %s=false   or  %s=off""".formatted(
            ANSI_ENABLE_ARG, ANSI_ENABLE_ARG, ANSI_ENABLE_ARG, ANSI_ENABLE_ARG, ANSI_ENABLE_ARG);

    public static void main(String[] args) {
        try {
            enableAnsi(args);
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.exit(-1);
        }

        System.out.println(ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT,
                "\nLoading application, please wait and don't input anything as your inputs will be buffered.\n"));
        CommandExecutor.execute("help");

        int returnCode;
        do {
            System.out.print(ConsoleColor.colorize(ConsoleColor.WHITE_BRIGHT, "Insert command: "));
            returnCode = CommandExecutor.execute(SCANNER.nextLine().trim());
        } while (returnCode == 0);

        SCANNER.close();
    }

    private static void enableAnsi(String[] args) {
        if (args != null) {
            if (args.length == 1) {
                getAnsiOption(args[0].trim()).ifPresent(ConsoleColor::enableAnsi);
            } else if (args.length > 1) {
                throw new IllegalArgumentException("""
                        Please only provide one argument.
                        %s""".formatted(VALID_ARGUMENTS_MESSAGE));
            }
        }
    }

    private static Optional<Boolean> getAnsiOption(String string) {
        if (string.startsWith(ANSI_ENABLE_ARG + '=')) {
            final String option = string.replace(ANSI_ENABLE_ARG + '=', "").trim();
            return switch (option) {
                case "auto" -> Optional.empty();
                case "true", "on" -> Optional.of(true);
                case "false", "off" -> Optional.of(false);
                default -> throw new IllegalArgumentException("""
                        '%s' unrecognised option for argument %s.
                        %s""".
                        formatted(option, ANSI_ENABLE_ARG, VALID_ARGUMENTS_MESSAGE));
            };
        } else {
            throw new IllegalArgumentException("""
                    '%s' unrecognised optional argument.
                    %s""".
                    formatted(string, VALID_ARGUMENTS_MESSAGE));
        }
    }
}
