package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientService {
    private final DataStore<Patient> patientStore;

    public PatientService(DataStore<Patient> patientStore) {
        this.patientStore = patientStore;
    }

    public Patient createPatient(String name, String phone, String email, int age, String address, Doctor primaryDoctor) {
        Patient patient = new Patient(
                IdGenerator.getInstance().nextId("P-"),
                Validator.requireText(name, "Patient name"),
                Validator.requireText(phone, "Phone number"),
                Validator.requireEmail(email),
                Validator.requirePositive(age, "Age"),
                Validator.requireText(address, "Address"),
                primaryDoctor
        );
        patientStore.save(patient.getId(), patient);
        return patient;
    }

    public List<Patient> listPatients() {
        return patientStore.findAll().stream()
                .sorted(Comparator.comparing(Patient::getName))
                .collect(Collectors.toList());
    }

    public Optional<Patient> findById(String id) {
        return patientStore.findById(id);
    }

    public List<Patient> searchPatient(String query) {
        String normalized = query == null ? "" : query.trim().toLowerCase();
        return patientStore.findAll().stream()
                .filter(patient -> patient.getId().toLowerCase().contains(normalized)
                        || patient.getName().toLowerCase().contains(normalized)
                        || String.valueOf(patient.getAge()).contains(normalized))
                .collect(Collectors.toList());
    }

    public Patient updatePatient(String id, String name, String phone, String email, int age, String address, Doctor primaryDoctor) {
        Patient existing = patientStore.findById(id)
                .orElseThrow(() -> new InvalidDataException("Patient not found: " + id));
        existing.setName(Validator.requireText(name, "Patient name"));
        existing.setPhoneNumber(Validator.requireText(phone, "Phone number"));
        existing.setEmail(Validator.requireEmail(email));
        existing.setAge(Validator.requirePositive(age, "Age"));
        existing.setAddress(Validator.requireText(address, "Address"));
        existing.setPrimaryDoctor(primaryDoctor);
        patientStore.save(existing.getId(), existing);
        return existing;
    }

    public boolean deletePatient(String id) {
        return patientStore.delete(id);
    }
}
