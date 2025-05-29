import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.*;
import java.util.Locale;
import java.util.Scanner;

public class AppointmentClient {

    public static void bookAppointment() {
        Scanner scanner = new Scanner(System.in);

        String clientId;
        String name;
        String species;
        String contactNum;
        String reason;

        initializeAppointmentFile();

        try {
            System.out.print("Enter Client Name: ");
            name = scanner.nextLine().trim();

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

                System.out.print("Enter Species: ");
                species = scanner.nextLine().trim();

            } else {
                System.out.println("Client not found. Proceeding as new client.");
                clientId = "NEW";

                System.out.print("Enter Client Name: ");
                name = scanner.nextLine().trim();

                System.out.print("Enter Species: ");
                species = scanner.nextLine().trim();

                contactNum = inputContactNumber(scanner);
            }

            String date = inputAppointmentDate(scanner);
            String time = inputAppointmentTime(scanner);

            System.out.print("Enter Reason for Appointment: ");
            reason = scanner.nextLine().trim();

            String record = clientId + " | " + name + " | " + species + " | " +
                            date + " | " + time + " | " + contactNum + " | " + reason;

            appendAppointmentToFile(record);

            System.out.println("\nAppointment booked successfully!");
            System.out.println("Appointment Details:");
            System.out.println(record);

            if (!isExistingClient) {
                System.out.println("Note: This appointment is not linked to a registered client.");
            }

        } catch (IOException e) {
            System.out.println("Error while booking appointment: " + e.getMessage());
        }
    }

    private static void initializeAppointmentFile() {
        try {
            File file = new File("appointments.txt");
            if (!file.exists() || file.length() == 0) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("ClientID | ClientName | Species | Date | Time | ContactNumber | Reason\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while initializing the appointments file: " + e.getMessage());
        }
    }

    private static String[] findClientDataByName(String nameInput) {
        try (BufferedReader br = new BufferedReader(new FileReader("ListofClients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 4) {
                    String fileName = data[1].trim();
                    if (fileName.equalsIgnoreCase(nameInput.trim())) {
                        return new String[] {
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

    private static void appendAppointmentToFile(String record) throws IOException {
        try (FileWriter apptWriter = new FileWriter("appointments.txt", true)) {
            apptWriter.write(record + "\n");
        }
    }
}
