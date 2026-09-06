import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 *
 * Demonstrates four core data structures:
 *   1. Binary Search Tree  -> Patient Records (keyed by Patient ID)
 *   2. Queue               -> Emergency Patient Queue (FIFO)
 *   3. Stack                -> Treatment History (LIFO)
 *   4. Singly Linked List   -> Each patient's Visit History
 */
public class HospitalManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientBST patientRecords = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentHistory = new TreatmentStack();

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" Welcome to the Mini Hospital Emergency Management System");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> patientRecordsMenu();
                case 2 -> emergencyQueueMenu();
                case 3 -> treatmentHistoryMenu();
                case 4 -> visitHistoryMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ----------------------- MAIN MENU -----------------------

    private static void printMainMenu() {
        System.out.println("\n================ MAIN MENU ================");
        System.out.println("1. Patient Records (Binary Search Tree)");
        System.out.println("2. Emergency Patient Queue (Queue)");
        System.out.println("3. Treatment History (Stack)");
        System.out.println("4. Patient Visit History (Singly Linked List)");
        System.out.println("0. Exit");
        System.out.println("============================================");
    }

    // ----------------------- 1. PATIENT BST MENU -----------------------

    private static void patientRecordsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Records (BST) ---");
            System.out.println("1. Insert New Patient");
            System.out.println("2. Search Patient by ID");
            System.out.println("3. Delete Patient by ID");
            System.out.println("4. Display All Patients (In-Order)");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> insertPatient();
                case 2 -> {
                    int id = readInt("Enter Patient ID to search: ");
                    Patient p = patientRecords.search(id);
                    System.out.println(p != null ? "Patient found:\n" + p : "Patient with ID " + id + " not found.");
                }
                case 3 -> {
                    int id = readInt("Enter Patient ID to delete: ");
                    boolean deleted = patientRecords.delete(id);
                    System.out.println(deleted ? "Patient deleted successfully." : "Patient with ID " + id + " not found.");
                }
                case 4 -> patientRecords.displayInOrder();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void insertPatient() {
        int id = readInt("Enter Patient ID: ");
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        boolean inserted = patientRecords.insert(patient);
        System.out.println(inserted ? "Patient added successfully." : "A patient with ID " + id + " already exists.");
    }

    // ----------------------- 2. EMERGENCY QUEUE MENU -----------------------

    private static void emergencyQueueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Emergency Patient Queue ---");
            System.out.println("1. Enqueue Patient (must already exist in Patient Records)");
            System.out.println("2. Dequeue Next Patient for Treatment");
            System.out.println("3. Display Waiting Queue");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> {
                    int id = readInt("Enter Patient ID to add to queue: ");
                    Patient p = patientRecords.search(id);
                    if (p == null) {
                        System.out.println("No patient with ID " + id + " found in records. Please add them first.");
                    } else {
                        emergencyQueue.enqueue(p);
                    }
                }
                case 2 -> {
                    Patient next = emergencyQueue.dequeue();
                    if (next != null) {
                        System.out.println("Now treating: " + next);
                        System.out.print("Enter treatment date (e.g. 2026-09-05): ");
                        String date = scanner.nextLine();
                        treatmentHistory.push(new TreatmentRecord(next.getPatientId(), next.getName(),
                                next.getMedicalCondition(), date));
                    }
                }
                case 3 -> emergencyQueue.displayQueue();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ----------------------- 3. TREATMENT STACK MENU -----------------------

    private static void treatmentHistoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Treatment History (Stack) ---");
            System.out.println("1. Push a Completed Treatment Record");
            System.out.println("2. Pop Most Recent Treatment Record");
            System.out.println("3. Display Treatment History");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> {
                    int id = readInt("Enter Patient ID: ");
                    Patient p = patientRecords.search(id);
                    if (p == null) {
                        System.out.println("No patient with ID " + id + " found in records.");
                    } else {
                        System.out.print("Enter treatment date (e.g. 2026-09-05): ");
                        String date = scanner.nextLine();
                        treatmentHistory.push(new TreatmentRecord(p.getPatientId(), p.getName(),
                                p.getMedicalCondition(), date));
                    }
                }
                case 2 -> {
                    TreatmentRecord record = treatmentHistory.pop();
                    if (record != null) {
                        System.out.println("Removed most recent record: " + record);
                    }
                }
                case 3 -> treatmentHistory.displayHistory();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ----------------------- 4. VISIT HISTORY (LINKED LIST) MENU -----------------------

    private static void visitHistoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Visit History (Singly Linked List) ---");
            System.out.println("1. Add Visit to a Patient's History");
            System.out.println("2. Remove Visit from a Patient's History");
            System.out.println("3. Search for a Visit");
            System.out.println("4. Display a Patient's Visit History");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> {
                    Patient p = findPatientOrWarn();
                    if (p != null) {
                        int visitId = readInt("Enter Visit ID: ");
                        System.out.print("Enter Visit Date: ");
                        String date = scanner.nextLine();
                        System.out.print("Enter Doctor Name: ");
                        String doctor = scanner.nextLine();
                        System.out.print("Enter Diagnosis: ");
                        String diagnosis = scanner.nextLine();
                        System.out.print("Enter Treatment: ");
                        String treatment = scanner.nextLine();
                        p.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
                        System.out.println("Visit added to patient's history.");
                    }
                }
                case 2 -> {
                    Patient p = findPatientOrWarn();
                    if (p != null) {
                        int visitId = readInt("Enter Visit ID to remove: ");
                        boolean removed = p.getVisitHistory().removeVisit(visitId);
                        System.out.println(removed ? "Visit removed successfully." : "Visit ID not found.");
                    }
                }
                case 3 -> {
                    Patient p = findPatientOrWarn();
                    if (p != null) {
                        int visitId = readInt("Enter Visit ID to search: ");
                        Visit v = p.getVisitHistory().searchVisit(visitId);
                        System.out.println(v != null ? "Visit found:\n" + v : "Visit ID not found.");
                    }
                }
                case 4 -> {
                    Patient p = findPatientOrWarn();
                    if (p != null) {
                        System.out.println("Visit history for " + p.getName() + " (ID: " + p.getPatientId() + "):");
                        p.getVisitHistory().displayVisits();
                    }
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Patient findPatientOrWarn() {
        int id = readInt("Enter Patient ID: ");
        Patient p = patientRecords.search(id);
        if (p == null) {
            System.out.println("No patient with ID " + id + " found in records.");
        }
        return p;
    }

    // ----------------------- INPUT HELPER -----------------------

    /** Reads an integer safely, re-prompting on invalid input. */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }
}
