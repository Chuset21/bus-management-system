package com.bus_system.util;

import java.util.Arrays;
import java.util.Optional;

public class OptionalOps {
    public static <T> String getStringValue(Optional<T> value) {
        return value.map(Object::toString).orElse("N/A");
    }

    public static boolean isPositive(Optional<Number> number) {
        return Math.signum(number.orElse(0).doubleValue()) >= 0;
    }

    public static boolean areAllPositive(Optional<Number>... numbers) {
        return Arrays.stream(numbers).allMatch(OptionalOps::isPositive);
    }
}
