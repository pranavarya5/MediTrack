package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Searchable;

public abstract class MedicalEntity implements Searchable, Cloneable {
    private String id;
    private String name;

    protected MedicalEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(String query) {
        String normalizedQuery = normalize(query);
        return normalize(id).contains(normalizedQuery) || normalize(name).contains(normalizedQuery);
    }

    public abstract MedicalEntity clone();

    @Override
    public String toString() {
        return id + " | " + name;
    }
}
