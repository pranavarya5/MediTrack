package com.airtribe.meditrack.entity;

public abstract class Person extends MedicalEntity {
    private String phoneNumber;
    private String email;

    protected Person(String id, String name, String phoneNumber, String email) {
        super(id, name);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + phoneNumber + " | " + email;
    }
}
