package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {


    // Check if string is empty
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty");
        }
    }

    // Validate email format
    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(regex)) {
            throw new InvalidInputException("Invalid email format");
        }
    }

    // Parse string to int
    public static int parseInt(String input, String fieldName) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a number");
        }
    }

    // Parse ID with validation
    public static int parseId(String input, String entityName) {
        int id = parseInt(input, entityName + " ID");
        if (id <= 0) {
            throw new InvalidInputException(entityName + " ID must be positive");
        }
        return id;
    }
}
