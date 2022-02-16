package bus_system.util;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Function;

public class Parser {
    // Suppresses default constructor, ensuring non-instantiability.
    private Parser() {

    }

    public static <T> Optional<T> parse(String value, Function<String, T> function) {
        return value == null || value.isBlank() ? Optional.empty() : Optional.ofNullable(function.apply(value.trim()));
    }

    public static <T extends Number> Optional<T> parseIfPositive(String value, Function<String, T> function) {
        if (value == null || value.isBlank()) {
            return Optional.empty();
        }

        final String trimmedValue = value.trim();
        if (trimmedValue.startsWith("-")) {
            return Optional.empty();
        }

        return Optional.of(function.apply(trimmedValue));
    }

    public static Optional<String> validate(String value) {
        return value == null || value.isBlank() ? Optional.empty() : Optional.of(value.trim());
    }

    public static LocalTime parseTime(String time) {
        try {
            return LocalTime.parse(time.length() != 8 ? '0' + time : time);
        } catch (DateTimeException e) {
            return null;
        }
    }
}
