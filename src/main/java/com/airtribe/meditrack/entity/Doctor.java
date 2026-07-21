package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;

public class Doctor extends Person implements Payable {
    private Specialization specialization;
    private double consultationFee;

    public Doctor(String id, String name, String phoneNumber, String email, Specialization specialization, double consultationFee) {
        super(id, name, phoneNumber, email);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculatePayableAmount() {
        return consultationFee;
    }

    @Override
    public Doctor clone() {
        return new Doctor(getId(), getName(), getPhoneNumber(), getEmail(), specialization, consultationFee);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + specialization + " | fee=" + consultationFee;
    }
}
