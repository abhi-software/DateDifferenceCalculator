package com.auspost.datediff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateCalculatorTest {

    private final DateCalculator calculator = new DateCalculator();

    @Test
    void testCalculateDifferenceSameDate() {
        int[] date1 = {1, 1, 1900};
        int[] date2 = {1, 1, 1900};
        assertEquals(0, calculator.calculateDifference(date1, date2));
    }

    @Test
    void testCalculateDifferenceForward() {
        int[] date1 = {1, 3, 2020};
        int[] date2 = {5, 3, 2020};
        assertEquals(4, calculator.calculateDifference(date1, date2));
    }

    @Test
    void testCalculateDifferenceBackward() {
        int[] date1 = {5, 3, 2020};
        int[] date2 = {1, 3, 2020};
        assertEquals(4, calculator.calculateDifference(date1, date2));
    }

    @Test
    void testCalculateDifferenceLeapYear() {
        int[] date1 = {28, 2, 2000};
        int[] date2 = {1, 3, 2000};
        assertEquals(2, calculator.calculateDifference(date1, date2)); // 29 Feb 2000 is valid
    }

    @Test
    void testCalculateDifferenceMultipleYears() {
        int[] date1 = {1, 1, 1900};
        int[] date2 = {1, 1, 1901};
        assertEquals(365, calculator.calculateDifference(date1, date2));
    }

    @Test
    void testCalculateDifferenceAcrossMonths() {
        int[] date1 = {31, 1, 1900};
        int[] date2 = {1, 3, 1900};
        assertEquals(29, calculator.calculateDifference(date1, date2)); // February 1900 has 28 days (not a leap year)
    }
}
