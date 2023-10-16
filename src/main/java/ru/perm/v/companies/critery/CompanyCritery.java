package ru.perm.v.companies.critery;

import java.util.Objects;

public class CompanyCritery {
    private String shortname = "";

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyCritery)) return false;
        CompanyCritery that = (CompanyCritery) o;
        return Objects.equals(shortname, that.shortname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortname);
    }
}
