package com.airtribe.meditrack.billing;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.util.IdGenerator;

public final class BillFactory {
    private BillFactory() {
    }

    public static Bill createBill(Appointment appointment, double baseAmount) {
        return new Bill( IdGenerator.getInstance().nextId("B-"), appointment, baseAmount);
    }
}
