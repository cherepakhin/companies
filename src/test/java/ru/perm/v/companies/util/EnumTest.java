package ru.perm.v.companies.util;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EnumTest {

    @Test
    public void equalsStringName() {
        assertEquals("n", EmployeeService.SORT_COLUMN.n.name());
    }

    @Test
    public void contains() {
        String column = "n";
        EmployeeService.SORT_COLUMN sortColumn = EmployeeService.SORT_COLUMN.valueOf(column);

        assertEquals("n", sortColumn.name());
    }

    @Test
    public void notContains() {
        String column = "nnnn";
        EmployeeService.SORT_COLUMN sortColumn = null;
        Exception except = null;
        try {
            sortColumn = EmployeeService.SORT_COLUMN.valueOf(column);
        } catch (IllegalArgumentException e) {
            except = e;
        }

        assertNull(sortColumn);
        assertEquals(IllegalArgumentException.class, except.getClass());
    }

}
