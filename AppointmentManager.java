import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.*;
import java.util.*;

public class AppointmentManager implements AdminActions {

    Scanner scanner = new Scanner(System.in);
    static int lastAppId; // For appointments unique ID (to edit the appointment)

    // Generate Appointment ID
    public static String generateAppointmentId() { // eto pag gegenerate na ng Appointment ID
        lastAppId++; // Increment lang kunwari 001 yung last nung nauna magiging 002 yung susunod
        return String.format("APT%03d", lastAppId);
    }

    public static List<Appointment> appointments = new ArrayList<>();

    // Static block to load appointments from file on class load
    static {
        try {
            loadAppointmentsFromFile(); // for automatic loading of appointments from the file
        } catch (IOException e) {
            System.out.println("An unexpected error occured. Could not load appointments at the moment " + e.getMessage());
        }
    }

    // Load appointments from file
    public static void loadAppointmentsFromFile() throws IOException {
        appointments.clear();
        int maxId = 0; // Track the highest ID and change follows it. Fixed the Id generation logic
        // Load appointments from file
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    String id = parts[0].trim();
                    String name = parts[1];
                    String species = parts[2];
                    String date = parts[3];
                    String appTime = parts[4];
                    String contactNum = parts[5];
                    String reason = parts[6];

                    appointments.add(new Appointment(id, name, species, date, appTime, contactNum, reason));

                    // Extract numeric part of ID and update maxId 
                    if (id.startsWith("APT")) {
                        try {
                            int num = Integer.parseInt(id.substring(3));
                            if (num > maxId) maxId = num;
                        } catch (NumberFormatException e) {
                            // Ignore malformed IDs
                        }
                    }
                }
            }
        }
        lastAppId = maxId;
    }

    // Save all appointments to file (overwrite)
    private static void overwriteFile() { // Para maupdate yung information
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (Appointment appointment : appointments) {
                writer.write(appointment.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An Unexpected Error Occured");
        }
    }

    // Append a single appointment record to file
    public static void appendAppointmentToFile(String record) throws IOException {
        try (FileWriter apptWriter = new FileWriter("appointments.txt", true)) {
            apptWriter.write(record + "\n");
        }
    }

    // Find client data by name from ListofClients.txt
    public static String[] findClientDataByName(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader("ListofClients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 4) {
                    String fileName = data[1].trim();
                    if (fileName.equalsIgnoreCase(name.trim())) {
                        return new String[]{
                            data[0].trim(),  // ID
                            data[1].trim(),  // Name
                            data[3].trim()   // Contact Number
                        };
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading client file: " + e.getMessage());
        }
        return null;
    }

    // Input helpers
    private static String inputContactNumber(Scanner scanner) {
        String contactNum;
        while (true) {
            System.out.print("Enter Contact Number (11 digits): ");
            contactNum = scanner.nextLine().trim();
            if (contactNum.matches("\\d{11}")) {
                return contactNum;
            } else {
                System.out.println("Invalid contact number. Must be exactly 11 digits.");
            }
        }
    }

    private static String inputAppointmentDate(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                String date = scanner.nextLine().trim();
                LocalDate enteredDate = LocalDate.parse(date);
                if (enteredDate.isBefore(LocalDate.now())) {
                    System.out.println("Date cannot be in the past.");
                } else {
                    return date;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    private static String inputAppointmentTime(Scanner scanner) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("hh:mm a")
                .toFormatter(Locale.ENGLISH)
                .withResolverStyle(ResolverStyle.STRICT);

        LocalTime opening = LocalTime.of(8, 0);
        LocalTime closing = LocalTime.of(15, 0);

        while (true) {
            try {
                System.out.print("Enter Appointment Time (hh:mm AM/PM): ");
                String time = scanner.nextLine().trim().toUpperCase();

                LocalTime enteredTime = LocalTime.parse(time, formatter);

                if (enteredTime.isBefore(opening) || enteredTime.isAfter(closing)) {
                    System.out.println("Appointment time must be between 8:00 AM and 3:00 PM.");
                } else {
                    return time;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use hh:mm AM/PM (e.g., 08:00 AM, 02:30 PM).");
            }
        }
    }

    // Helper for species validation
    private String inputSpecies() {
        while (true) {
            System.out.print("Enter Species: ");
            String species = scanner.nextLine().trim();
            if (!(species.equalsIgnoreCase("Dog") || species.equalsIgnoreCase("Cat"))) {
                System.out.println("Species must be 'Dog' or 'Cat' only.");
            } else {
                return species;
            }
        }
    }

    // Helper for contact number validation
    private String inputContactNumber() {
        while (true) {
            System.out.print("Enter Contact Number (11 digits): ");
            String contactNum = scanner.nextLine().trim();
            if (!contactNum.matches("\\d{11}")) {
                System.out.println("Invalid contact number. Must be exactly 11 digits.");
            } else {
                return contactNum;
            }
        }
    }

    // Add appointment
    @Override
    public void add() {
        String clientId;
        String name;
        String species;
        String contactNum;
        String reason;

        try {
            // Name validation
            while (true) {
                System.out.print("Enter Client Name: ");
                name = scanner.nextLine().trim();
                if (name == null || name.isEmpty()) {
                    System.out.println("Name must not be empty.");
                } else {
                    break;
                }
            }

            String[] clientData = findClientDataByName(name);
            boolean isExistingClient = clientData != null;

            if (isExistingClient) {
                clientId = clientData[0];
                name = clientData[1];
                contactNum = clientData[2];
                System.out.println("Client found! Auto-filled details:");
                System.out.println("ID: " + clientId);
                System.out.println("Name: " + name);
                System.out.println("Contact Number: " + contactNum);

                // Use helper for species validation
                species = inputSpecies();

            } else {
                System.out.println("Client not found. Proceeding as new client.");
                clientId = AppointmentManager.generateAppointmentId();

                species = inputSpecies();
                contactNum = inputContactNumber();
            }

            String date = inputAppointmentDate(scanner);
            String time = inputAppointmentTime(scanner);

            System.out.print("Enter Reason for Appointment: ");
            reason = scanner.nextLine().trim();

            Appointment appointment = new Appointment(clientId, name, species, date, time, contactNum, reason);
            appointments.add(appointment);
            overwriteFile();

            System.out.println("\nAppointment booked successfully!");
            System.out.println("Appointment Details:");
            System.out.println(appointment.toFileString());

            if (!isExistingClient) {
                System.out.println("Note: This appointment is not linked to a registered client.");
            }

        } catch (Exception e) {
            System.out.println("Error while booking appointment: " + e.getMessage());
        }
    }

    // Edit appointment
    @Override
    public void edit() {
        System.out.println("Enter Appointment ID: ");
        String searchId = scanner.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getId().trim().equalsIgnoreCase(searchId.trim())) {
                System.out.println("Updating Appointment's Info: " + appointment.getName());

                System.out.print("New Date (YYYY-MM-DD): ");
                String newDate = scanner.nextLine().trim();
                System.out.print("New Time (hh:mm AM/PM): ");
                String newAppTime = scanner.nextLine().trim();

                // Update only if not empty
                if (!newDate.isEmpty()) appointment.setDate(newDate);
                if (!newAppTime.isEmpty()) appointment.setAppTime(newAppTime);

                appointments.set(i, appointment);
                overwriteFile();
                found = true;
                System.out.println("Appointment Updated");
                System.out.println("ID: " + appointment.getId());
                System.out.println("Name: " + appointment.getName());
                System.out.println("Species: " + appointment.getSpecies());
                System.out.println("Contact Number: " + appointment.getContactNum());
                break;
            }
        }
        if (!found) {
            System.out.println("Appointment ID not found.");
        }
    }

    // Delete appointment
    @Override
    public void delete() {
        System.out.print("Enter Appointment ID to delete: ");
        String searchId = scanner.nextLine().trim();

        boolean found = false;
        Iterator<Appointment> iterator = appointments.iterator();

        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getId().equalsIgnoreCase(searchId)) {
                System.out.print("Are you sure you want to delete appointment for " + appointment.getName() + " (Y/N)? ");
                String confirmation = scanner.nextLine().trim();

                if (confirmation.equalsIgnoreCase("Y")) {
                    iterator.remove();
                    overwriteFile();
                    System.out.println("Appointment deleted successfully.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Appointment ID not found.");
        }
    }

    // Search appointment
    @Override
    public void search() {
        System.out.print("Enter Appointment ID to search: ");
        String searchId = scanner.nextLine().trim();

        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getId().equalsIgnoreCase(searchId)) {
                System.out.println("\nAppointment Found:");
                System.out.println(appointment.toFileString());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Appointment not found.");
        }
    }

    // View all appointments
    @Override
    public void viewAll() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("\nList of Appointments");
        System.out.println("===============================================");
        for (Appointment appointment : appointments) {
            System.out.println(appointment.toFileString());
        }
        System.out.println("===============================================");
    }
}

