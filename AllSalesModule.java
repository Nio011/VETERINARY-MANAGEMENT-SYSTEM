// AllSalesModule.java
import java.util.*;

public class AllSalesModule {
    private static final String SALES_FILE = "allsales.txt";
    Scanner sc = new Scanner(System.in);

    private static DashboardModule dm = new DashboardModule();
    private static AllSalesManager allSalesViewer = new AllSalesManager();

    public static void showAllSalesMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- ALL SALES MENU ---");
            System.out.println("1. View All Sales");
            System.out.println("2. Search Sales");
            System.out.println("3. Back to Dashboard");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    AllSalesManager.viewAllSales();
                    break;
                case 2:
                    System.out.print("Enter Client ID or keyword to search: ");
                    String query = sc.nextLine();
                    AllSalesManager.searchSales(query);
                    break;
                case 3:
                    dm.showDashboard();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
