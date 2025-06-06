import java.util.List;
import java.util.Scanner;

public class ServicesModule {

    private static ServicesManager servicesManager = new ServicesManager();

    public static void showServicesMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SERVICES MENU ---");
            System.out.println("1. View All Services");
            System.out.println("2. Add New Service");
            System.out.println("3. Update Existing Service");
            System.out.println("4. Delete Service");
            System.out.println("0. Back to Dashboard");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayServices();
                    break;
                case 2:
                    System.out.print("Enter service name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter pet type (dog/cat): ");
                    String petType = sc.nextLine();
                    servicesManager.addService(new Service(name, price, petType));
                    break;
                case 3:
                    displayServices();
                    System.out.print("Enter the index of service to update: ");
                    int updateIndex = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter new service name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter new pet type: ");
                    String newType = sc.nextLine();
                    servicesManager.updateService(updateIndex, new Service(newName, newPrice, newType));
                    break;
                case 4:
                    displayServices();
                    System.out.print("Enter the index of service to delete: ");
                    int deleteIndex = sc.nextInt();
                    servicesManager.deleteService(deleteIndex);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void displayServices() {
        List<Service> services = servicesManager.getAllServices();
        if (services.isEmpty()) {
            System.out.println("No services available.");
        } else {
            System.out.println("\n--- List of Services ---");
            for (int i = 0; i < services.size(); i++) {
                Service s = services.get(i);
                System.out.println(i + ". " + s.getName() + " - ₱" + s.getPrice() + " [" + s.getPetType() + "]");
            }
        }
    }
}
