import java.io.*;
import java.util.*;

public class ServicesManager implements AdminActions {

    private static List<Services.Service> services = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Helper to check for duplicate service name (ignoreIndex = -1 for add, or index to ignore for edit)
    private boolean isDuplicateServiceName(String name, int ignoreIndex) {
        for (int i = 0; i < services.size(); i++) {
            if (i != ignoreIndex && services.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Load services from file
    public static void loadServicesFromFile() throws IOException {
        services.clear();
        File file = new File("services.txt");
        if (!file.exists()) file.createNewFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Services.Service service = Services.Service.fromFileString(line);
                    services.add(service);
                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        }
    }

    // Save all services to file
    public static void saveAllServicesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("services.txt"))) {
            for (Services.Service service : services) {
                writer.write(service.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save services: " + e.getMessage());
        }
    }

    // For GUI or other classes to get the list
    public static List<Services.Service> getServices() {
        return new ArrayList<>(services);
    }

    // Validating service name input
    private String inputValidServiceName() {
        while (true) {
            System.out.print("Enter service name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Service name cannot be empty.");
            } else {
                return name;
            }
        }
    }

    // Validating service price input    
    private double inputValidServicePrice() {
        while (true) {
            try {
                System.out.print("Enter service price: ");
                String input = scanner.nextLine().trim();
                double price = Double.parseDouble(input);
                if (price < 0) {
                    System.out.println("Price cannot be negative.");
                } else {
                    return price;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Please enter a numeric value.");
            }
        }
    }

    @Override
    public void add() { // Add a service 
        String name = inputValidServiceName();

        // Use isDuplicateServiceName for duplicate check
        if (isDuplicateServiceName(name, -1)) {
            System.out.println("A service with this name already exists. Please use a different name.");
            return;
        }

        double price = inputValidServicePrice();
        services.add(new Services.Service(name, price));
        saveAllServicesToFile();

        System.out.println("Service added successfully!");
    }

    @Override // Edit the price of the service by name
    public void edit() {
        System.out.print("Enter service name to edit: ");
        String target = scanner.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < services.size(); i++) {
            Services.Service service = services.get(i);
            if (service.getName().equalsIgnoreCase(target)) {
                System.out.println("Editing service: " + service.getName());
                String newName = inputValidServiceName();

                // Use helper for duplicate check (ignore current index)
                if (isDuplicateServiceName(newName, i)) {
                    System.out.println("A service with this name already exists. Please use a different name.");
                    return;
                }

                double newPrice = inputValidServicePrice();
                services.set(i, new Services.Service(newName, newPrice));
                saveAllServicesToFile();
                System.out.println("Service updated.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Service not found.");
        }
    }

    @Override // Delete a service by name
    public void delete() {
        System.out.print("Enter service name to delete: ");
        String target = scanner.nextLine().trim();
        Iterator<Services.Service> iterator = services.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Services.Service service = iterator.next();
            if (service.getName().equalsIgnoreCase(target)) {
                System.out.print("Are you sure you want to delete " + service.getName() + "? (Y/N): ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    iterator.remove();
                    saveAllServicesToFile();
                    System.out.println("Service deleted.");
                } else {
                    System.out.println("Deletion canceled.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Service not found.");
        }
    }

    @Override // Search for a service by name
    public void search() {
        System.out.print("Enter service name to search: ");
        String target = scanner.nextLine().trim();
        boolean found = false;

        for (Services.Service service : services) {
            if (service.getName().equalsIgnoreCase(target)) {
                System.out.println("Service Found:");
                System.out.println("Name: " + service.getName());
                System.out.println("Price: ₱" + service.getPrice());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Service not found.");
        }
    }

    @Override // View all services
    public void viewAll() {
        if (services.isEmpty()) {
            System.out.println("No services available.");
        } else {
            System.out.println("\nList of Services:");
            System.out.println("===================================");
            for (Services.Service service : services) {
                System.out.println("Name: " + service.getName() + " | Price: ₱" + service.getPrice());
            }
            System.out.println("===================================");
        }
    }
}
