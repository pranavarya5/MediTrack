package com.airtribe.meditrack.entity;

public class Patient extends Person {
    private int age;
    private String address;
    private Doctor primaryDoctor;

    public Patient(String id, String name, String phoneNumber, String email, int age, String address, Doctor primaryDoctor) {
        super(id, name, phoneNumber, email);
        this.age = age;
        this.address = address;
        this.primaryDoctor = primaryDoctor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Doctor getPrimaryDoctor() {
        return primaryDoctor;
    }

    public void setPrimaryDoctor(Doctor primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    }

    @Override
    public Patient clone() {
        return new Patient(getId(), getName(), getPhoneNumber(), getEmail(), age, address, primaryDoctor == null ? null : primaryDoctor.clone());
    }

    @Override
    public String toString() {
        return super.toString() + " | age=" + age + " | " + address;
    }
}
