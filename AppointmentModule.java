import java.util.Scanner;

public class AppointmentModule {

    public static void showAppointmentMenu() {

        Scanner sc = new Scanner(System.in);
        AppointmentManager apptManager = new AppointmentManager();

        while (true) {
            System.out.println("\nAPPOINTMENT MANAGEMENT");
            System.out.println("1. Add Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Search Appointments");
            System.out.println("4. Edit Appointment");
            System.out.println("5. Delete Appointment");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> apptManager.add();
                case 2 -> apptManager.viewAll();
                case 3 -> apptManager.search();
                case 4 -> apptManager.edit();
                case 5 -> apptManager.delete();
                case 6 -> {
                    System.out.println("Exiting Appointment Module.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }


}

