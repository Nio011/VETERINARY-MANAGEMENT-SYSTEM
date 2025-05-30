import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DashboardModule {

    public void moduleChoice() {
        Scanner c = new Scanner(System.in);
        System.out.println("1. Clients");
        System.out.println("2. Pet Details");
        System.out.println("3. Appointments");
        System.out.println("4. Services");
        System.out.println("5. All Sales");
        System.out.println("6. POS");
        System.out.println("7. Change Password");
        System.out.print("Choose 1 Option: ");
        int choice = c.nextInt();
        c.nextLine(); // consume newline

        switch (choice) {
            case 1:
                ClientModule.showClientMenu(); // Assuming ClientModule exists
                break;
            case 2:
                AnimalModule.showAnimalMenu(c); // Pass Scanner here
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                System.out.println("Working on it");
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }

    public void showDashboard() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM dd, yyyy | hh:mm a");
        String formattedDateTime = now.format(format);

        System.out.println("VETERINARY MANAGEMENT SYSTEM");
        System.out.println("ADMIN DASHBOARD");
        System.out.println("DATE & TIME: " + formattedDateTime);
        System.out.println();

        System.out.println("System Overview");
        System.out.println("TOTAL CLIENTS: ");
        System.out.println("TODAY'S APPOINTMENT: ");
        System.out.println("PENDING APPOINTMENTS: ");
        System.out.println("TOTAL SALES: ");
        System.out.println();

        System.out.println("MENU");
        moduleChoice();
    }
}
