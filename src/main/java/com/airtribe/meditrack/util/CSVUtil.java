package com.airtribe.meditrack.util;

import java.util.Arrays;
import java.util.List;

public final class CSVUtil {
    private CSVUtil() {
    }

    public static List<String> split(String line) {
        return Arrays.asList(line.split(","));
    }

    public static String join(String... values) {
        return String.join(",", values);
    }
}
