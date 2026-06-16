package dev.webshop.utils;

import java.math.BigDecimal;

public class Check {

    private Check() {}

    public static void checkNonNegative(Long value, String field) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(field + " niet geldig");
        }
    }

    public static void checkNonNegative(BigDecimal value, String field) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(field + " niet geldig");
        }
    }

    public static void checkNonNegativeOrZero(Long value, String field) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(field + " niet geldig");
        }
    }

    public static void checkNotBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " niet geldig");
        }
    }
}