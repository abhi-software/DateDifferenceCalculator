package com.auspost.datediff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {

    private final DateParser parser = new DateParser();

    @Test
    void testParseDate() {
        int[] date1 = parser.parseDate("01 10 2020");
        assertArrayEquals(new int[]{1, 10, 2020}, date1);

        int[] date2 = parser.parseDate("29 02 2000");
        assertArrayEquals(new int[]{29, 2, 2000}, date2);
    }

    @Test
    void testParseDateWithLeadingZeros() {
        int[] date = parser.parseDate("01 03 2019");
        assertArrayEquals(new int[]{1, 3, 2019}, date);
    }

    @Test
    void testParseDateWithoutLeadingZeros() {
        int[] date = parser.parseDate("1 3 2019");
        assertArrayEquals(new int[]{1, 3, 2019}, date);
    }
}
