package com.auspost.datediff;

public class DateCalculator {

    // Constants
    private static final int MIN_YEAR = 1900;

    /**
     * Calculates the difference in days between two dates.
     * Each date is represented as an array [day, month, year].
     * Returns the absolute difference in days.
     */
    public int calculateDifference(int[] date1, int[] date2) {
        int serialDate1 = toSerialDate(date1[0], date1[1], date1[2]);
        int serialDate2 = toSerialDate(date2[0], date2[1], date2[2]);
        return Math.abs(serialDate2 - serialDate1);
    }

    /**
     * Converts a date to a serial number representing the total days since 01/01/1900.
     */
    private int toSerialDate(int day, int month, int year) {
        int totalDays = 0;

        // Add days for the years
        for (int y = MIN_YEAR; y < year; y++) {
            totalDays += isLeapYear(y) ? 366 : 365;
        }

        // Add days for the months in the current year
        for (int m = 1; m < month; m++) {
            totalDays += daysInMonth(m, year);
        }

        // Add days in the current month
        totalDays += day;

        return totalDays;
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
