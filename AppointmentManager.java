import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.*;
import java.util.*;

public class AppointmentManager {

    private final String fileName = "appointments.txt";
    private List<Appointment> appointments;

    public AppointmentManager() {
        appointments = new ArrayList<>();
        loadAppointmentsFromFile();
    }

    private void loadAppointmentsFromFile() {
        appointments.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 7) {
                    Appointment appt = new Appointment(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        data[5].trim(),
                        data[6].trim()
                    );
                    appointments.add(appt);
                }
            }
        } catch (FileNotFoundException e) {
            // If file doesn't exist yet, it's fine — start fresh.
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
    }

    private void saveAppointmentsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("ClientID | ClientName | Species | Date | Time | ContactNumber | Reason");
            for (Appointment appt : appointments) {
                pw.println(appt.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments: " + e.getMessage());
        }
    }

    public void addAppointment() {
        Appointment newAppointment = AppointmentClient.bookAppointment();
        if (newAppointment != null) {
            appointments.add(newAppointment);
            saveAppointmentsToFile();
        }
    }

    public void viewAll() {
        System.out.println("----- All Appointments -----");
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appt : appointments) {
                System.out.println(appt.toString());
            }
        }
        System.out.println("----------------------------");
    }

    public void search() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Client Name to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Appointment appt : appointments) {
            if (appt.getName().toLowerCase().contains(searchName)) {
                System.out.println(appt.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for client: " + searchName);
        }
    }

    public void edit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Client ID to edit appointment: ");
        String clientId = scanner.nextLine().trim();

        Appointment toEdit = null;
        for (Appointment appt : appointments) {
            if (appt.getId().equals(clientId)) {
                toEdit = appt;
                break;
            }
        }

        if (toEdit == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.println("Current Appointment: " + toEdit.toString());

        String newDate = AppointmentManager.inputAppointmentDate(scanner);
        String newTime = AppointmentManager.inputAppointmentTime(scanner);

        toEdit.setDate(newDate);
        toEdit.setAppTime(newTime);

        saveAppointmentsToFile();

        System.out.println("Appointment updated successfully.");
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Client ID to delete appointment: ");
        String clientId = scanner.nextLine().trim();

        boolean removed = appointments.removeIf(appt -> appt.getId().equals(clientId));

        if (removed) {
            saveAppointmentsToFile();
            System.out.println("Appointment deleted successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    // --- Input validation methods ---

    public static String inputAppointmentDate(Scanner scanner) {
        String date;
        while (true) {
            try {
                System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                date = scanner.nextLine().trim();
                LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                return date;
