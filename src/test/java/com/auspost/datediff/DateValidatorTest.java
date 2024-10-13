package com.auspost.datediff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    private final DateValidator validator = new DateValidator();

    @Test
    void testValidDates() {
        assertNull(validator.validateDate("01 10 2020"));
        assertNull(validator.validateDate("29 02 2000")); // Leap year
    }

    @Test
    void testInvalidFormat() {
        assertEquals("Not of format DD MM YYYY", validator.validateDate("01012020"));
        assertEquals("Not of format DD MM YYYY", validator.validateDate("01-10-2020"));
        assertEquals("Not of format DD MM YYYY", validator.validateDate("01/10/2020"));
        assertEquals("Not of format DD MM YYYY", validator.validateDate("1 10 2020"));    // Single digit day
        assertEquals("Not of format DD MM YYYY", validator.validateDate("01 5 2020"));    // Single digit month
        assertEquals("Not of format DD MM YYYY", validator.validateDate("1 5 2020"));     // Single digit day and month
        assertEquals("Not of format DD MM YYYY", validator.validateDate("06 05 199"));    // Three-digit year
        assertEquals("Not of format DD MM YYYY", validator.validateDate("06 05 19933"));  // Five-digit year
        assertEquals("Not of format DD MM YYYY", validator.validateDate(" 06 05 1993"));  // Leading space
        assertEquals("Not of format DD MM YYYY", validator.validateDate("06 05 1993 "));  // Trailing space
        assertEquals("Not of format DD MM YYYY", validator.validateDate("06-05-1993"));    // Hyphens instead of spaces
    }

    @Test
    void testNonNumeric() {
        assertEquals("Not of format DD MM YYYY", validator.validateDate("01 Oct 2020"));
        assertEquals("Not of format DD MM YYYY", validator.validateDate("First Second Third"));
    }

    @Test
    void testYearOutOfRange() {
        assertEquals("Year out of range (1900-2020)", validator.validateDate("01 10 1899"));
        assertEquals("Year out of range (1900-2020)", validator.validateDate("01 10 2021"));
    }

    @Test
    void testMonthOutOfRange() {
        assertEquals("Month out of range (1-12)", validator.validateDate("01 13 2020"));
        assertEquals("Month out of range (1-12)", validator.validateDate("01 00 2020"));
    }

    @Test
    void testDayOutOfRange() {
        assertEquals("Day out of range for month 2 (1-28)", validator.validateDate("29 02 2019")); // Not a leap year
        assertEquals("Day out of range for month 4 (1-30)", validator.validateDate("31 04 2020"));
        assertEquals("Day out of range for month 1 (1-31)", validator.validateDate("32 01 2020"));
    }

    @Test
    void testEmptyDate() {
        assertEquals("Date is empty", validator.validateDate(""));
        assertEquals("Date is empty", validator.validateDate("   "));
    }
}
