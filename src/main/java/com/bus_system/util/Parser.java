package com.bus_system.util;

import java.util.Optional;
import java.util.function.Function;

public class Parser {
    public static <T> Optional<T> parse(String value, Function<String, T> function) {
        return value == null || value.isBlank() ? Optional.empty() : Optional.ofNullable(function.apply(value));
    }
}
