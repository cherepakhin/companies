package ru.perm.v.companies.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate fromStringToDate(String str) {
        return LocalDate.parse(str, dateTimeFormatter);
    }

    public static String fromDateToString(LocalDate ddate) {
        return dateTimeFormatter.format(ddate);
    }
}
