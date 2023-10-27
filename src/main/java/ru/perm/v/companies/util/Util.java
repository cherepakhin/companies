package ru.perm.v.companies.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Convert from String to LocalDate. String format dd/MM/yyyy
     */
    public static LocalDate fromStringToDate(String str) {
        return LocalDate.parse(str, dateTimeFormatter);
    }

    /**
     * Convert from LocalDate to String. String format dd/MM/yyyy
     */
    public static String fromDateToString(LocalDate ddate) {
        return dateTimeFormatter.format(ddate);
    }
}
