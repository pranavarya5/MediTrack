package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.RecordHelper;
import com.airtribe.meditrack.util.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorService {
    private final DataStore<Doctor> doctorStore;

    public DoctorService(DataStore<Doctor> doctorStore) {
        this.doctorStore = doctorStore;
    }

    public Doctor createDoctor(String name, String phone, String email, Specialization specialization, double fee) {
        String normalizedPhone = RecordHelper.normalize(phone);
        String normalizedEmail = RecordHelper.normalize(email);

        boolean duplicateFound = RecordHelper.containsDuplicate(doctorStore.findAll(), existing ->
                RecordHelper.normalize(existing.getPhoneNumber()).equals(normalizedPhone)
                        || RecordHelper.normalize(existing.getEmail()).equals(normalizedEmail));
        if (duplicateFound) {
            throw new InvalidDataException("Duplicate doctor record: email or phone already exists");
        }

        Doctor doctor = new Doctor(
                IdGenerator.getInstance().nextId("D-"),
                Validator.requireText(name, "Doctor name"),
                Validator.requireText(phone, "Phone number"),
                Validator.requireEmail(email),
                specialization,
                Validator.requirePositive(fee, "Consultation fee")
        );
        doctorStore.save(doctor.getId(), doctor);
        return doctor;
    }

    public List<Doctor> listDoctors() {
        return doctorStore.findAll().stream()
                .sorted(Comparator.comparing(Doctor::getName))
                .collect(Collectors.toList());
    }

    public Optional<Doctor> findById(String id) {
        return doctorStore.findById(id);
    }

    public List<Doctor> searchByName(String name) {
        String query = name == null ? "" : name.trim().toLowerCase();
        return doctorStore.findAll().stream()
                .filter(doctor -> doctor.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    public List<Doctor> searchBySpecialization(Specialization specialization) {
        return doctorStore.findAll().stream()
                .filter(doctor -> doctor.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }

    public double averageConsultationFee() {
        return doctorStore.findAll().stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0.0);
    }

    public Doctor updateDoctor(String id, String name, String phone, String email, Specialization specialization, double fee) {
        Doctor existing = doctorStore.findById(id)
                .orElseThrow(() -> new InvalidDataException("Doctor not found: " + id));
        existing.setName(Validator.requireText(name, "Doctor name"));
        existing.setPhoneNumber(Validator.requireText(phone, "Phone number"));
        existing.setEmail(Validator.requireEmail(email));
        existing.setSpecialization(specialization);
        existing.setConsultationFee(Validator.requirePositive(fee, "Consultation fee"));
        doctorStore.save(existing.getId(), existing);
        return existing;
    }

    public boolean deleteDoctor(String id) {
        return doctorStore.delete(id);
    }
}
