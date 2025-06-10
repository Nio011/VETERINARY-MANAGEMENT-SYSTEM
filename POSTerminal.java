import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class POSTerminal {

    private static Scanner sc = new Scanner(System.in);

    private static ClientManager clientManager = new ClientManager();
    private static ServicesManager serviceManager = new ServicesManager();

    public static void processTransaction() {
        try {
            // Step 1: Select Client from ListofClients.txt
            List<Client> clients = clientManager.getClients();
            if (clients.isEmpty()) {
                System.out.println("No clients found.");
                return;
            }

            System.out.println("Select Client by ID:");
            for (Client c : clients) {
                System.out.println(c.getId() + " - " + c.getName());
            }

            String clientId;
            Client selectedClient;
            while (true) {
                System.out.print("Enter Client ID: ");
                clientId = sc.nextLine().trim();
                selectedClient = clientManager.findClientbyId(clientId);
                if (selectedClient == null) {
                    System.out.println("Client ID not found. Please try again.");
                } else {
                    break;
                }
            }
            System.out.println("Client Selected: " + selectedClient.getName());

            // Step 2: Select Services from services.txt, real-time total update
            List<Services.Service> availableServices = serviceManager.getServices();
            if (availableServices.isEmpty()) {
                System.out.println("No services available.");
                return;
            }

            List<Services.Service> selectedServices = new ArrayList<>();
            double total = 0;
            while (true) {
                System.out.println("\n--- Available Services ---");
                for (int i = 0; i < availableServices.size(); i++) {
                    System.out.printf("%d. %s - ₱%.2f%n", i + 1, availableServices.get(i).getName(), availableServices.get(i).getPrice());
                }
                System.out.println("0. Finish selection");
                System.out.println("Current total: ₱" + String.format("%.2f", total));
                System.out.print("Enter service number to add/remove (add if not selected, remove if selected): ");
                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                    continue;
                }

                if (choice == 0) break;
                if (choice < 1 || choice > availableServices.size()) {
                    System.out.println("Invalid service number.");
                    continue;
                }

                Services.Service selected = availableServices.get(choice - 1);
                if (selectedServices.contains(selected)) {
                    selectedServices.remove(selected);
                    total -= selected.getPrice();
                    System.out.println("Removed: " + selected.getName());
                } else {
                    selectedServices.add(selected);
                    total += selected.getPrice();
                    System.out.println("Added: " + selected.getName());
                }
            }

            if (selectedServices.isEmpty()) {
                System.out.println("No services selected. Transaction cancelled.");
                return;
            }

            // Step 3: Generate Order Summary
            System.out.println("\n--- Order Summary ---");
            for (Services.Service s : selectedServices) {
                System.out.printf(" - %s : ₱%.2f%n", s.getName(), s.getPrice());
            }
            System.out.printf("Total: ₱%.2f%n", total);

            // Step 4: Confirm purchase
            System.out.print("Confirm purchase? (Y/N): ");
            String confirm = sc.nextLine().trim();
            if (!confirm.equalsIgnoreCase("Y")) {
                System.out.println("Transaction cancelled.");
                return;
            }

            // Step 5: Generate receipt
            LocalDateTime now = LocalDateTime.now();
            String dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.println("\n--- Receipt ---");
            System.out.println("Date & Time: " + dateTime);
            System.out.println("Client ID: " + selectedClient.getId());
            System.out.println("Client Name: " + selectedClient.getName());
            System.out.println("Services:");
            StringBuilder servicesAndPrices = new StringBuilder();
            for (Services.Service s : selectedServices) {
                System.out.printf(" - %s : ₱%.2f%n", s.getName(), s.getPrice());
                servicesAndPrices.append(s.getName()).append(" ₱").append(String.format("%.2f", s.getPrice())).append("; ");
            }
            System.out.printf("Total: ₱%.2f%n", total);

            // Step 6: Save to AllSales.txt
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("AllSales.txt", true))) {
                bw.write(String.format("%s | %s | %.2f | %s",
                        selectedClient.getId(),
                        servicesAndPrices.toString().trim(),
                        total,
                        dateTime));
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Error writing to AllSales.txt");
            }

            System.out.println("Transaction completed and saved.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during the transaction: " + e.getMessage());
        }
    }
}
