package com.auspost.datediff;

public class DateParser {

    /**
     * Parses the date string into an array of integers [day, month, year].
     * Assumes the date string has been validated.
     */
    public int[] parseDate(String dateStr) {
        String[] parts = dateStr.trim().split("\\s+");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new int[]{day, month, year};
    }
}
