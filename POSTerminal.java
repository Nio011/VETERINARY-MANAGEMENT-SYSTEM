import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class POSTerminal {

    static Scanner sc = new Scanner(System.in);

    public static void processTransaction() {
        // Step 1: Select Client from clients.txt
        Map<String, String> clients = loadClients();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
            return;
        }

        System.out.println("Select Client by ID:");
        clients.forEach((id, name) -> System.out.println(id + " - " + name));
        System.out.print("Enter Client ID: ");
        String clientId = sc.nextLine().trim();

        if (!clients.containsKey(clientId)) {
            System.out.println("Client ID not found.");
            return;
        }

        String clientName = clients.get(clientId);
        System.out.println("Client Selected: " + clientName);

        // Step 2: Show pet details for this client from animaldetails.txt
        List<String> pets = loadPetDetails(clientId);
        if (pets.isEmpty()) {
            System.out.println("No pets found for this client.");
        } else {
            System.out.println("Pet(s):");
            pets.forEach(System.out::println);
        }

        // Step 3: Load all services (no filter by pet type)
        List<Service> availableServices = loadServices();
        if (availableServices.isEmpty()) {
            System.out.println("No services available.");
            return;
        }

        // Step 4: User selects services; total updates automatically
        List<Service> selectedServices = new ArrayList<>();
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

            Service selected = availableServices.get(choice - 1);
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

        // Step 5: Payment input and validation
        double payment = 0;
        while (true) {
            System.out.printf("Total amount due: ₱%.2f%n", total);
            System.out.print("Enter payment amount: ₱");
            try {
                payment = Double.parseDouble(sc.nextLine().trim());
                if (payment < total) {
                    System.out.println("Insufficient payment. Please enter at least ₱" + String.format("%.2f", total));
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
        double change = payment - total;

        // Step 6: Show receipt
        System.out.println("\n--- Receipt ---");
        System.out.println("Date: " + LocalDate.now());
        System.out.println("Client ID: " + clientId);
        System.out.println("Client Name: " + clientName);
        System.out.println("Services:");
        for (Service s : selectedServices) {
            System.out.printf(" - %s : ₱%.2f%n", s.getName(), s.getPrice());
        }
        System.out.printf("Total: ₱%.2f%n", total);
        System.out.printf("Payment: ₱%.2f%n", payment);
        System.out.printf("Change: ₱%.2f%n", change);

        // Step 7: Save transaction to AllSales.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("AllSales.txt", true))) {
            bw.write(String.format("%s | %s | %s | %.2f | %.2f | %.2f",
                    LocalDate.now(), clientId, clientName, total, payment, change));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to AllSales.txt");
        }

        System.out.println("Transaction completed and saved.");
    }

    private static Map<String, String> loadClients() {
        Map<String, String> clients = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("clients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    clients.put(id, name);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading clients.txt");
        }
        return clients;
    }

    private static List<String> loadPetDetails(String clientId) {
        List<String> pets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("animaldetails.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming animaldetails.txt format: ClientID | PetName | Species | ...
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && parts[0].trim().equalsIgnoreCase(clientId)) {
                    String petInfo = parts[1].trim() + " (" + parts[2].trim() + ")";
                    pets.add(petInfo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading animaldetails.txt");
        }
        return pets;
    }

    private static List<Service> loadServices() {
        List<Service> services = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("services.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    services.add(new Service(name, price));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading services.txt");
        }
        return services;
    }

    static class Service {
        private String name;
        private double price;

        public Service(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Service)) return false;
            Service other = (Service) obj;
            return name.equalsIgnoreCase(other.name);
        }

        @Override
        public int hashCode() {
            return name.toLowerCase().hashCode();
        }
    }
}
