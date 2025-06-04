import java.util.*;

public class ServicesModule {
    private static Map<String, List<Service>> servicesByType = new HashMap<>();

    static {
        // Initialize services for cats and dogs only
        servicesByType.put("cat", new ArrayList<>());
        servicesByType.put("dog", new ArrayList<>());

        // Cat services
        servicesByType.get("cat").add(new Service("General Consultation", 500));
        servicesByType.get("cat").add(new Service("Follow-up Consultation", 400));
        servicesByType.get("cat").add(new Service("Anti-Rabies", 350));
        servicesByType.get("cat").add(new Service("4-in-1 Vaccine", 900));
        servicesByType.get("cat").add(new Service("Feline Leukemia (FeLV)", 1450));
        // ... Add all other cat services here ...

        // Dog services
        servicesByType.get("dog").add(new Service("General Consultation", 500));
        servicesByType.get("dog").add(new Service("Follow-up Consultation", 400));
        servicesByType.get("dog").add(new Service("Anti-Rabies", 350));
        servicesByType.get("dog").add(new Service("5-in-1 Vaccine", 950));
        servicesByType.get("dog").add(new Service("6-in-1 Vaccine", 1200));
        // ... Add all other dog services here ...
    }

    public static void showServicesMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- SERVICES MENU ---");
            System.out.println("1. View Services");
            System.out.println("2. Add Service");
            System.out.println("3. Delete Service");
            System.out.println("4. Back to Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewServices(sc);
                    break;
                case 2:
                    addService(sc);
                    break;
                case 3:
                    deleteService(sc);
                    break;
                case 4:
                    new DashboardModule().showDashboard();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewServices(Scanner sc) {
        String petType = choosePetType(sc);
        if (petType == null) return;

        List<Service> services = servicesByType.get(petType);
        if (services.isEmpty()) {
            System.out.println("No services available for " + petType + ".");
            return;
        }

        System.out.println("\n--- " + petType.toUpperCase() + " SERVICES ---");
        for (int i = 0; i < services.size(); i++) {
            System.out.printf("%d. %s - ₱%.2f\n", i + 1, services.get(i).getName(), services.get(i).getPrice());
        }
    }

    private static void addService(Scanner sc) {
        String petType = choosePetType(sc);
        if (petType == null) return;

        System.out.print("Enter new service name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ₱");
        double price = sc.nextDouble();
        sc.nextLine(); // consume newline

        servicesByType.get(petType).add(new Service(name, price));
        System.out.println("Service added successfully.");
    }

    private static void deleteService(Scanner sc) {
        String petType = choosePetType(sc);
        if (petType == null) return;

        List<Service> services = servicesByType.get(petType);
        if (services.isEmpty()) {
            System.out.println("No services to delete for " + petType + ".");
            return;
        }

        System.out.println("\n--- SELECT SERVICE TO DELETE ---");
        for (int i = 0; i < services.size(); i++) {
            System.out.printf("%d. %s - ₱%.2f\n", i + 1, services.get(i).getName(), services.get(i).getPrice());
        }
        System.out.print("Enter service number to delete: ");
        int index = sc.nextInt();
        sc.nextLine(); // consume newline

        if (index < 1 || index > services.size()) {
            System.out.println("Invalid selection.");
        } else {
            Service removed = services.remove(index - 1);
            System.out.println("Removed service: " + removed.getName());
        }
    }

    private static String choosePetType(Scanner sc) {
        System.out.println("Select pet type:");
        System.out.println("1. Cat");
        System.out.println("2. Dog");
        System.out.print("Enter choice: ");
        int typeChoice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (typeChoice) {
            case 1:
                return "cat";
            case 2:
                return "dog";
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    private static class Service {
        private final String name;
        private final double price;

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
    }
}
