import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Scanner;

public class AppointmentClient{

   public void static bookAppointment() {
        Scanner scanner = new Scanner(System.in);
        String clientId;
        String name;
        String species;
        String contactNum;
        String reason;

        // Initialize appointments file with header if empty
        try {
            FileWriter writer = new FileWriter("appointments.txt", true);
            BufferedReader br = new BufferedReader(new FileReader("appointments.txt"));
            if (br.readLine() == null) {
                writer.write("ClientID | ClientName | Species | Date | Time | ContactNumber | Reason\n");
            }
            br.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while initializing the appointments file: " + e.getMessage());
        }

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

                // Ask for species since it is not stored in client file
                System.out.print("Enter Species: ");
                species = scanner.nextLine().trim();

            } else {
                System.out.println("Client not found. Proceeding as new client.");
                clientId = "NEW";

                // Get details manually
                System.out.print("Enter Client Name: ");
                name = scanner.nextLine().trim();

                System.out.print("Enter Species: ");
                species = scanner.nextLine().trim();

                // For new client, enter contact number now
                while (true) {
                    System.out.print("Enter Contact Number (11 digits): ");
                    contactNum = scanner.nextLine().trim();
                    if (contactNum.matches("\\d{11}")) {
                        break;
                    } else {
                        System.out.println("Invalid contact number. Must be exactly 11 digits.");
                    }
                }
            }

            // Appointment Date input with validation
            String date;
            while (true) {
                try {
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    date = scanner.nextLine().trim();
                    LocalDate enteredDate = LocalDate.parse(date);
                    if (enteredDate.isBefore(LocalDate.now())) {
                        throw new IllegalArgumentException("Date cannot be in the past.");
                    }
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Appointment Time input with validation
            String time;
            while (true) {
                try {
                    System.out.print("Enter Appointment Time (hh:mm AM/PM): ");
                    time = scanner.nextLine().trim().toUpperCase();

                    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern("hh:mm a")
                        .toFormatter(Locale.ENGLISH)
                        .withResolverStyle(ResolverStyle.STRICT);

                    LocalTime enteredTime = LocalTime.parse(time, formatter);
                    LocalTime opening = LocalTime.of(8, 0);
                    LocalTime closing = LocalTime.of(15, 0);

                    if (enteredTime.isBefore(opening) || enteredTime.isAfter(closing)) {
                        throw new IllegalArgumentException("Appointment time must be between 8:00 AM and 3:00 PM.");
                    }

                    break; // time is valid
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please use hh:mm AM/PM (e.g., 08:00 AM, 02:30 PM).");
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ " + e.getMessage());
                }
            }

            // Reason for appointment
            System.out.print("Enter Reason for Appointment: ");
            String reason = scanner.nextLine().trim();

            // Build appointment record
            String record = clientId + " | " + name + " | " + species + " | " +
                            date + " | " + time + " | " + contactNum + " | " + reason;

            // Append appointment record to file
            try (FileWriter apptWriter = new FileWriter("appointments.txt", true)) {
                apptWriter.write(record + "\n");
            }

            System.out.println("\nAppointment booked successfully!");
            System.out.println("Appointment Details:");
            System.out.println("Client ID: " + clientId);
            System.out.println("Client Name: " + name);
            System.out.println("Species: " + species);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Contact Number: " + contactNum);
            System.out.println("Reason: " + reason);

            if (!isExistingClient) {
                System.out.println("Note: This appointment is not linked to a registered client.");
            }

        } catch (IOException e) {
            System.out.println("Error while booking appointment: " + e.getMessage());
        }
    }

    private String[] findClientDataByName(String nameInput) {
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
                } else {
                    System.out.println("⚠️ Invalid client line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading client file: " + e.getMessage());
        }
        return null;
    }

}
