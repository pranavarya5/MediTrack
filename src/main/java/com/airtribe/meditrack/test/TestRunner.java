package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.DataStore;

import java.time.LocalDateTime;

public class TestRunner {
    public static void main(String[] args) {
        DataStore<Doctor> doctorStore = new DataStore<>();
        DataStore<Patient> patientStore = new DataStore<>();
        DataStore<Appointment> appointmentStore = new DataStore<>();

        DoctorService doctorService = new DoctorService(doctorStore);
        PatientService patientService = new PatientService(patientStore);
        AppointmentService appointmentService = new AppointmentService(appointmentStore, patientStore, doctorStore);

        Doctor doctor = doctorService.createDoctor("Dr. Sharma", "9876543210", "sharma@example.com", Specialization.GENERAL_MEDICINE, 500);
        Patient patient = patientService.createPatient("Aman", "9000000000", "aman@example.com", 24, "Delhi", doctor);
        Appointment appointment = appointmentService.createAppointment(patient.getId(), doctor.getId(), LocalDateTime.now().plusDays(1));
        appointmentService.confirm(appointment.getId());
        Bill bill = appointmentService.generateBill(appointment.getId());

        System.out.println("Doctors: " + doctorService.listDoctors().size());
        System.out.println("Patients: " + patientService.listPatients().size());
        System.out.println("Appointments: " + appointmentService.listAppointments().size());
        System.out.println(bill);
    }
}
