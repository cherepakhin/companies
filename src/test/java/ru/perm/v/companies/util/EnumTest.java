package ru.perm.v.companies.util;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumTest {

    @Test
    public void equalsStringName() {
        assertEquals("n", EmployeeService.SORT_COLUMN.n.name());
    }
}
