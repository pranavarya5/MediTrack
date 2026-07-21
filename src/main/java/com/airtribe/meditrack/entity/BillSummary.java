package com.airtribe.meditrack.entity;

public final class BillSummary {
    private final String billId;
    private final String patientName;
    private final double baseAmount;
    private final double taxAmount;
    private final double totalAmount;

    public BillSummary(String billId, String patientName, double baseAmount, double taxAmount, double totalAmount) {
        this.billId = billId;
        this.patientName = patientName;
        this.baseAmount = baseAmount;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
    }

    public String getBillId() {
        return billId;
    }

    public String getPatientName() {
        return patientName;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return billId + " | " + patientName + " | base=" + baseAmount + " | tax=" + taxAmount + " | total=" + totalAmount;
    }
}
