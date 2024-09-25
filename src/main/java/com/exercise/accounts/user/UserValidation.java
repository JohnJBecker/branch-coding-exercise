package com.exercise.accounts.user;

import java.util.regex.Pattern;

public class UserValidation {

    // This regex pattern checks for valid usernames based on the specified rules. It ensures:
    // - The username is between 1 and 39 characters long.
    // - It starts with an alphanumeric character.
    // - It does not contain consecutive dashes.
    // - It does not start or end with a dash.
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?!.*--)(?!-)[a-zA-Z0-9][a-zA-Z0-9-]{0,38}(?<!-)$");

    public static boolean isValidUsername(String username) {
        // Check if username matches the pattern and is not empty
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }
}
