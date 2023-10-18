package ru.perm.v.companies.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {
    @Test
    public void fromLocalDateToStr() {
        LocalDate testDate = LocalDate.of(1990,12,31);
        assertEquals("31/12/1990", Util.fromDateToString(testDate));
    }

    @Test
    public void fromStringToLocalDate() {
        assertEquals(LocalDate.of(1990,12, 31), Util.fromStringToDate("31/12/1990"));
    }
}