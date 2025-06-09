import java.util.Scanner;

public class ServicesModule {

    private static ServicesManager sm = new ServicesManager();
    private static DashboardModule dm = new DashboardModule();
    private static ClientManager cm = new ClientManager();

    public static void showServicesMenu() {
        Scanner sc = new Scanner(System.in);

        sm.viewAll();

        while (true) {
            System.out.println("\n--- SERVICES MENU ---");
            System.out.println("1. Add New Service");
            System.out.println("2. Search Services");
            System.out.println("3. Update Existing Service");
            System.out.println("4. Delete Service");
            System.out.println("0. Back to Dashboard");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice){
                case 1: 
                    sm.add();
                    break;
                case 2:
                    sm.search();
                    break;
                case 3:
                    sm.edit();
                    break;
                case 4:
                    sm.delete();
                    break;
                case 5:
                    dm.showDashboard();
                    break;
                default: 
                    System.out.println("Invalid choice");


            }

}
    }
}