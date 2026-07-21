package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

public final class Validator {
    private Validator() {
    }

    public static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(fieldName + " cannot be blank");
        }
        return value.trim();
    }

    public static int requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be greater than zero");
        }
        return value;
    }

    public static double requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be greater than zero");
        }
        return value;
    }

    public static String requireEmail(String value) {
        String email = requireText(value, "Email");
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidDataException("Email format is invalid");
        }
        return email;
    }
}
