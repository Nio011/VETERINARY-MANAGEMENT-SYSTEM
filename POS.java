import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class POS {
    private ServicesManager servicesManager;
    private AllSales allSales;
    private Map<String, Appointment> appointmentsMap;

    public POS(ServicesManager servicesManager, AllSales allSales, List<Appointment> appointments) {
        this.servicesManager = servicesManager;
        this.allSales = allSales;
        this.appointmentsMap = new HashMap<>();

        for (Appointment appt : appointments) {
            appointmentsMap.put(appt.getId(), appt);
        }
    }

    public void processTransaction(Scanner sc) {
        System.out.print("Enter client ID: ");
        String clientID = sc.nextLine().trim();

        Appointment appt = appointmentsMap.get(clientID);
        if (appt == null) {
            System.out.println("Client ID not found.");
            return;
        }

        System.out.println("Client Name: " + appt.getName());
        System.out.println("Pet Type: " + appt.getSpecies());

        List<Service> availableServices = servicesManager.getServicesByPetType(appt.getSpecies());
        if (availableServices.isEmpty()) {
            System.out.println("No services available for this pet type.");
            return;
        }

        System.out.println("\nAvailable Services:");
        for (int i = 0; i < availableServices.size(); i++) {
            System.out.println((i + 1) + ". " + availableServices.get(i));
        }

        List<Service> chosenServices = new ArrayList<>();
        while (true) {
            System.out.print("Enter service number to add (0 to finish): ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 0) break;

            if (choice < 1 || choice > availableServices.size()) {
                System.out.println("Invalid choice.");
                continue;
            }

            chosenServices.add(availableServices.get(choice - 1));
            System.out.println("Service added: " + availableServices.get(choice - 1).getServiceName());
        }

        if (chosenServices.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        double total = 0;
        System.out.println("\n--- RECEIPT ---");
        System.out.println("Client: " + appt.getName());
        System.out.println("Pet Type: " + appt.getSpecies());
        for (Service s : chosenServices) {
            System.out.println("- " + s.getServiceName() + ": ₱" + String.format("%.2f", s.getPrice()));
            total += s.getPrice();
        }
        System.out.println("TOTAL: ₱" + String.format("%.2f", total));
        System.out.println("---------------------");

        // Record to AllSales
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        allSales.addSale(appt.getName(), appt.getSpecies(), chosenServices, total, date);
    }
}
