package com.airtribe.meditrack.util;

import java.util.List;
import java.util.function.Predicate;

public final class RecordHelper {
    private RecordHelper() {
    }

    public static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }

    public static <T> boolean containsDuplicate(List<T> records, Predicate<T> matcher) {
        if (records == null) {
            return false;
        }
        return records.stream().anyMatch(matcher);
    }
}
