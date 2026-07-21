package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;

public class Bill implements Payable {
    private final String billId;
    private final Appointment appointment;
    private final double baseAmount;

    public Bill(String billId, Appointment appointment, double baseAmount) {
        this.billId = billId;
        this.appointment = appointment;
        this.baseAmount = baseAmount;
    }

    public String getBillId() {
        return billId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getTaxAmount() {
        return baseAmount * Constants.TAX_RATE;
    }

    public BillSummary toSummary() {
        return new BillSummary(billId, appointment.getPatient().getName(), baseAmount, getTaxAmount(), calculatePayableAmount());
    }

    @Override
    public double calculatePayableAmount() {
        return baseAmount + getTaxAmount();
    }

    @Override
    public String toString() {
        return toSummary().toString();
    }
}
