package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.test.TestRunner;
import com.airtribe.meditrack.util.DataStore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && "--runTests".equalsIgnoreCase(args[0])) {
            TestRunner.main(new String[0]);
            return;
        }

        DataStore<Doctor> doctorStore = new DataStore<>();
        DataStore<Patient> patientStore = new DataStore<>();
        DataStore<Appointment> appointmentStore = new DataStore<>();

        DoctorService doctorService = new DoctorService(doctorStore);
        PatientService patientService = new PatientService(patientStore);
        AppointmentService appointmentService = new AppointmentService(appointmentStore, patientStore, doctorStore);

        seedSampleData(doctorService, patientService);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> listDoctors(doctorService);
                    case "2" -> listPatients(patientService);
                    case "3" -> listAppointments(appointmentService);
                    case "4" -> createAppointment(scanner, appointmentService);
                    case "5" -> cancelAppointment(scanner, appointmentService);
                    case "6" -> generateBill(scanner, appointmentService);
                    case "7" -> runSearch(scanner, doctorService, patientService);
                    case "0" -> {
                        System.out.println("Exiting MediTrack.");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== MediTrack ===");
        System.out.println("1. List doctors");
        System.out.println("2. List patients");
        System.out.println("3. List appointments");
        System.out.println("4. Create appointment");
        System.out.println("5. Cancel appointment");
        System.out.println("6. Generate bill");
        System.out.println("7. Search doctors/patients");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void seedSampleData(DoctorService doctorService, PatientService patientService) {
        Doctor doctor = doctorService.createDoctor("Dr. Mehta", "9876500001", "mehta@meditrack.com", Specialization.CARDIOLOGY, 700);
        patientService.createPatient("Riya", "9999900001", "riya@example.com", 28, "Mumbai", doctor);
    }

    private static void listDoctors(DoctorService doctorService) {
        doctorService.listDoctors().forEach(System.out::println);
        System.out.println("Average fee: " + doctorService.averageConsultationFee());
    }

    private static void listPatients(PatientService patientService) {
        patientService.listPatients().forEach(System.out::println);
    }

    private static void listAppointments(AppointmentService appointmentService) {
        appointmentService.listAppointments().forEach(System.out::println);
    }

    private static void createAppointment(Scanner scanner, AppointmentService appointmentService) {
        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine().trim();
        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine().trim();
        Appointment appointment = appointmentService.createAppointment(patientId, doctorId, LocalDateTime.now().plusDays(1));
        System.out.println("Created: " + appointment);
    }

    private static void cancelAppointment(Scanner scanner, AppointmentService appointmentService) {
        System.out.print("Appointment ID: ");
        String appointmentId = scanner.nextLine().trim();
        System.out.println("Updated: " + appointmentService.cancel(appointmentId));
    }

    private static void generateBill(Scanner scanner, AppointmentService appointmentService) {
        System.out.print("Appointment ID: ");
        String appointmentId = scanner.nextLine().trim();
        Bill bill = appointmentService.generateBill(appointmentId);
        System.out.println(bill);
    }

    private static void runSearch(Scanner scanner, DoctorService doctorService, PatientService patientService) {
        System.out.print("Search text: ");
        String query = scanner.nextLine().trim();
        List<Doctor> doctors = doctorService.searchByName(query);
        List<Patient> patients = patientService.searchPatient(query);
        System.out.println("Doctors found: " + doctors.size());
        doctors.forEach(System.out::println);
        System.out.println("Patients found: " + patients.size());
        patients.forEach(System.out::println);
    }
}
