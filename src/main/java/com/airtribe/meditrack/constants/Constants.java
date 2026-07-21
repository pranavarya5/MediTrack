package com.airtribe.meditrack.constants;

public final class Constants {
    public static final double TAX_RATE;
    public static final String APP_NAME;
    public static final String DOCTOR_CSV_PATH;
    public static final String PATIENT_CSV_PATH;
    public static final String APPOINTMENT_CSV_PATH;

    static {
        TAX_RATE = 0.18d;
        APP_NAME = "MediTrack";
        DOCTOR_CSV_PATH = "src/main/resources/doctors.csv";
        PATIENT_CSV_PATH = "src/main/resources/patients.csv";
        APPOINTMENT_CSV_PATH = "src/main/resources/appointments.csv";
    }

    private Constants() {
    }
}
