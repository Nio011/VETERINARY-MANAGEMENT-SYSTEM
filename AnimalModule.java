import java.util.Scanner;

public class AnimalModule {

    public static void showAnimalMenu(Scanner sc) {
        AnimalManager am = new AnimalManager(sc);
        DashboardModule dm = new DashboardModule();

        am.viewAll();
        while (true) {
            System.out.println("\n=== Animal Manager ===");
            System.out.println("1. Add Animal");
            System.out.println("2. Search Animals");
            System.out.println("3. Update Animal");
            System.out.println("4. Delete Animal");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Choose an option: ");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    am.add();
                    break;
                case "2":
                    am.search();
                    break;
                case "3":
                    am.edit();
                    break;
                case "4":
                    am.delete();
                    break;
                case "5":
                    dm.showDashboard();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    }

