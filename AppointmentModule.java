import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AppointmentModule {

    public void bookAppointment() {
        Scanner scanner = new Scanner(System.in);

        try {
            FileWriter writer = new FileWriter("appointments.txt", true);
            BufferedReader br = new BufferedReader(new FileReader("appointments.txt"));
            if (br.readLine() == null) {
                writer.write("ClientID | ClientName | PetType | Time | Reason\n");
            }
            br.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while initializing the appointments file: " + e.getMessage());
        }

        try {
            System.out.print("Enter Client Name: ");
            String name = scanner.nextLine();

            String clientId = findClientIdByName(name);
            boolean isExistingClient = clientId != null;

            if (isExistingClient) {
                System.out.println("Client found! Client ID: " + clientId);
            } else {
                System.out.println("Client not found. Booking will continue without linking to a registered client.");
                clientId = "NEW";
            }

            System.out.print("Enter Type of Pet: ");
            String petType = scanner.nextLine();

            System.out.print("Enter Appointment Time: ");
            String time = scanner.nextLine();

            System.out.print("Enter Reason for Appointment: ");
            String reason = scanner.nextLine();

            String appointmentRecord = clientId + " | " + name + " | " + petType + " | " + time + " | " + reason;

            FileWriter apptWriter = new FileWriter("appointments.txt", true);
            apptWriter.write(appointmentRecord + "\n");
            apptWriter.close();

            System.out.println("\nAppointment booked successfully!");
            if (!isExistingClient) {
                System.out.println("Note: This appointment is not linked to a registered client.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String findClientIdByName(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader("ListofClients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length > 1 && data[1].trim().equalsIgnoreCase(name.trim())) {
                    return data[0].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading client file: " + e.getMessage());
        }
        return null;
    }
}
