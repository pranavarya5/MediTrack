package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DataStore<T> {
    private final Map<String, T> storage = new HashMap<>();

    public void save(String id, T value) {
        storage.put(id, value);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean delete(String id) {
        return storage.remove(id) != null;
    }

    public boolean contains(String id) {
        return storage.containsKey(id);
    }

    public int size() {
        return storage.size();
    }
}
