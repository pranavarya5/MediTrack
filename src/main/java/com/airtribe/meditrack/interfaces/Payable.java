package com.airtribe.meditrack.interfaces;

public interface Payable {
    double calculatePayableAmount();

    default String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }
}
