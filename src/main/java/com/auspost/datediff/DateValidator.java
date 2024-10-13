package com.auspost.datediff;

import java.util.regex.Pattern;

public class DateValidator {

    // Constants
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2020;
    
    // Regex pattern for DD MM YYYY (exactly two digits for day and month, four digits for year)
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{2} \\d{2} \\d{4}$");

    /**
     * Validates a date string in DD MM YYYY format.
     * Returns null if valid, otherwise returns an error description.
     */
    public String validateDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            return "Date is empty";
        }

        // Check format using regex
        if (!DATE_PATTERN.matcher(dateStr).matches()) {
            return "Not of format DD MM YYYY";
        }

        String[] parts = dateStr.trim().split("\\s+");
        if (parts.length != 3) {
            // This condition is redundant due to regex, but kept for extra safety
            return "Incorrect format. Expected DD MM YYYY";
        }

        int day;
        int month;
        int year;

        try {
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return "Non-numeric values found";
        }

        // Year range check
        if (year < MIN_YEAR || year > MAX_YEAR) {
            return "Year out of range (" + MIN_YEAR + "-" + MAX_YEAR + ")";
        }

        // Month range check
        if (month < 1 || month > 12) {
            return "Month out of range (1-12)";
        }

        // Day range check
        int maxDay = daysInMonth(month, year);
        if (day < 1 || day > maxDay) {
            return "Day out of range for month " + month + " (" + 1 + "-" + maxDay + ")";
        }

        return null; // No errors, date is valid
    }

    /**
     * Returns the number of days in a given month and year.
     */
    private int daysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * Checks if a year is a leap year.
     */
    private boolean isLeapYear(int year) {
        if (year % 4 != 0)
            return false;
        else if (year % 100 != 0)
            return true;
        else
            return year % 400 == 0;
    }
}
