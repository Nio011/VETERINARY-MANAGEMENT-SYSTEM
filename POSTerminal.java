import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class POSTerminal {

    public static void processTransaction() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Client ID: ");
        String clientId = sc.nextLine().trim();
        String clientName = "";
        String petType = "";
        boolean found = false;

        // Read appointments.txt to find client info
        try (BufferedReader br = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length >= 3 && parts[0].equalsIgnoreCase(clientId)) {
                    clientName = parts[1];
                    petType = parts[2];
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading appointments.txt");
            return;
        }

        if (!found) {
            System.out.println("Client ID not found.");
            return;
        }

        System.out.println("Client Name: " + clientName);
        System.out.println("Pet Type: " + petType);

        // Read services.txt and list services for matching pet type
        List<Service> availableServices = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("services.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[2].equalsIgnoreCase(petType)) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    availableServices.add(new Service(name, price, parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading services.txt");
            return;
        }

        if (availableServices.isEmpty()) {
            System.out.println("No services available for pet type: " + petType);
            return;
        }

        System.out.println("\n--- Available Services ---");
        for (int i = 0; i < availableServices.size(); i++) {
            Service s = availableServices.get(i);
            System.out.println((i + 1) + ". " + s.getName() + " - ₱" + s.getPrice());
        }

        List<Service> selectedServices = new ArrayList<>();
        while (true) {
            System.out.print("Enter service number to add (0 to finish): ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            if (choice == 0) break;
            if (choice < 1 || choice > availableServices.size()) {
                System.out.println("Invalid service number.");
            } else {
                selectedServices.add(availableServices.get(choice - 1));
                System.out.println("Added: " + availableServices.get(choice - 1).getName());
            }
        }

        if (selectedServices.isEmpty()) {
            System.out.println("No services selected. Transaction cancelled.");
            return;
        }

        double total = 0;
        System.out.println("\n--- Receipt ---");
        System.out.println("Client: " + clientName);
        for (Service s : selectedServices) {
            System.out.println(s.getName() + " - ₱" + s.getPrice());
            total += s.getPrice();
        }
        System.out.printf("TOTAL: ₱%.2f%n", total);

        // Save to AllSales.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("AllSales.txt", true))) {
            bw.write(LocalDate.now() + " | " + total + " | " + clientName);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to AllSales.txt");
        }

        System.out.println("Transaction completed and saved.");
    }
}
