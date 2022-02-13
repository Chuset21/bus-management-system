package com.bus_system.util;

import java.util.Optional;

public class OptionalOps {
    public static <T> String getStringValue(Optional<T> value) {
        return value.map(Object::toString).orElse("N/A");
    }
}
