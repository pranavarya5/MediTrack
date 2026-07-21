package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppointmentService {
    private final DataStore<Appointment> appointmentStore;
    private final DataStore<Patient> patientStore;
    private final DataStore<Doctor> doctorStore;

    public AppointmentService(DataStore<Appointment> appointmentStore, DataStore<Patient> patientStore, DataStore<Doctor> doctorStore) {
        this.appointmentStore = appointmentStore;
        this.patientStore = patientStore;
        this.doctorStore = doctorStore;
    }

    public Appointment createAppointment(String patientId, String doctorId, LocalDateTime appointmentTime) {
        Patient patient = patientStore.findById(patientId)
                .orElseThrow(() -> new InvalidDataException("Patient not found: " + patientId));
        Doctor doctor = doctorStore.findById(doctorId)
                .orElseThrow(() -> new InvalidDataException("Doctor not found: " + doctorId));
        Appointment appointment = new Appointment(
                IdGenerator.getInstance().nextId("A-"),
                patient.clone(),
                doctor.clone(),
                appointmentTime,
                AppointmentStatus.PENDING
        );
        appointmentStore.save(appointment.getId(), appointment);
        return appointment;
    }

    public List<Appointment> listAppointments() {
        return appointmentStore.findAll().stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentTime))
                .collect(Collectors.toList());
    }

    public Optional<Appointment> findById(String id) {
        return appointmentStore.findById(id);
    }

    public Appointment confirm(String id) {
        Appointment appointment = getRequiredAppointment(id);
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        return appointment;
    }

    public Appointment cancel(String id) {
        Appointment appointment = getRequiredAppointment(id);
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointment;
    }

    public Bill generateBill(String appointmentId) {
        Appointment appointment = getRequiredAppointment(appointmentId);
        if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new InvalidDataException("Cancelled appointment cannot be billed");
        }
        return new Bill(IdGenerator.getInstance().nextId("B-"), appointment, appointment.getDoctor().getConsultationFee());
    }

    public String appointmentTimeText(String appointmentId) {
        return getRequiredAppointment(appointmentId).getAppointmentTime() == null ? "" : DateUtil.format(getRequiredAppointment(appointmentId).getAppointmentTime());
    }

    private Appointment getRequiredAppointment(String id) {
        return appointmentStore.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found: " + id));
    }
}
