import java.util.ArrayList;
import java.util.Scanner;

public class HospitalQueueSystemQuestion {
    private static ArrayList<Patient> patientQueue = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to Hospital Queue Management System");

        while (running) {
            displayMenu();
            int choice = getValidIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    serveNextPatient();
                    break;
                case 3:
                    displayQueue();
                    break;
                case 4:
                    updatePriority();
                    break;
                case 5:
                    searchPatient();
                    break;
                case 6:
                    System.out.println("Thank you for using Hospital Queue Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== HOSPITAL QUEUE SYSTEM =====");
        System.out.println("1. Add a new patient to the queue");
        System.out.println("2. Serve next patient");
        System.out.println("3. Display current queue");
        System.out.println("4. Update patient priority");
        System.out.println("5. Search for a patient");
        System.out.println("6. Exit");
        System.out.println("=================================");
    }

    private static void addPatient() {
        System.out.println("\n--- Add New Patient ---");
        String name = getValidStringInput("Enter patient's name: ");
        int age = getValidIntInput("Enter patient's age: ");
        String condition = getValidStringInput("Enter condition description: ");
        int priority = getValidIntInRange("Enter priority (1-Critical to 5-Non-urgent): ", 1, 5);

        Patient newPatient = new Patient(name, age, condition, priority);

        // Insert based on priority (lower number = higher priority)
        int index = 0;
        while (index < patientQueue.size() && patientQueue.get(index).getPriority() <= priority) {
            index++;
        }
        patientQueue.add(index, newPatient);
        System.out.println("Patient added successfully.");
    }

    private static void serveNextPatient() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in queue.");
        } else {
            Patient next = patientQueue.remove(0);
            System.out.println("Serving patient: " + next.getName() +
                " | Age: " + next.getAge() +
                " | Condition: " + next.getCondition() +
                " | Priority: " + getPriorityText(next.getPriority()));
        }
    }

    private static void displayQueue() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in queue.");
        } else {
            System.out.println("\n--- Current Patient Queue ---");
            for (int i = 0; i < patientQueue.size(); i++) {
                Patient p = patientQueue.get(i);
                System.out.println((i + 1) + ". " + p.getName() +
                    " | Age: " + p.getAge() +
                    " | Condition: " + p.getCondition() +
                    " | Priority: " + getPriorityText(p.getPriority()));
            }
        }
    }

    private static void updatePriority() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients to update.");
            return;
        }

        String name = getValidStringInput("Enter the patient's name to update: ");
        boolean found = false;

        for (int i = 0; i < patientQueue.size(); i++) {
            Patient p = patientQueue.get(i);
            if (p.getName().equalsIgnoreCase(name)) {
                int newPriority = getValidIntInRange("Enter new priority (1-Critical to 5-Non-urgent): ", 1, 5);
                patientQueue.remove(i); // Remove old entry
                p.setPriority(newPriority);

                // Reinsert with new priority
                int index = 0;
                while (index < patientQueue.size() && patientQueue.get(index).getPriority() <= newPriority) {
                    index++;
                }
                patientQueue.add(index, p);
                System.out.println("Priority updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient not found.");
        }
    }

    private static void searchPatient() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in queue.");
            return;
        }

        String name = getValidStringInput("Enter the patient's name to search: ");
        boolean found = false;

        for (Patient p : patientQueue) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("Found: " + p.getName() +
                    " | Age: " + p.getAge() +
                    " | Condition: " + p.getCondition() +
                    " | Priority: " + getPriorityText(p.getPriority()));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient not found.");
        }
    }

    private static String getPriorityText(int priority) {
        switch (priority) {
            case 1:
                return "1-Critical";
            case 2:
                return "2-Urgent";
            case 3:
                return "3-High";
            case 4:
                return "4-Medium";
            case 5:
                return "5-Non-urgent";
            default:
                return "Unknown";
        }
    }

    private static int getValidIntInput(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    private static int getValidIntInRange(String prompt, int min, int max) {
        int value;
        while (true) {
            value = getValidIntInput(prompt);
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Please enter a value between " + min + " and " + max + ".");
        }
        return value;
    }

    private static String getValidStringInput(String prompt) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                break;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
        return value;
    }
}

class Patient {
    private String name;
    private int age;
    private String condition;
    private int priority; // 1 (Critical) to 5 (Non-urgent)

    public Patient(String name, int age, String condition, int priority) {
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCondition() {
        return condition;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}