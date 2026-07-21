package com.airtribe.meditrack.interfaces;

public interface Searchable {
    boolean matches(String query);

    default String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }
}
