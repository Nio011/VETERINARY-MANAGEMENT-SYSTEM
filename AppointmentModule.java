import java.util.Scanner;

public class AppointmentModule {

    public static void showClientMenu(){

        Scanner sc = new Scanner(System.in);

        AppointmentManager ApM = new AppointmentManager();
        DashboardModule dm = new DashboardModule();

        ApM.viewAll(); 
        
        while (true){
            System.out.println("CLIENT MANAGEMENT");
            System.out.println("1. Add Client");
            System.out.println("2. Search Clients");
            System.out.println("3. Update Clients");
            System.out.println("4. Delete Clients");
            System.out.println("5. Back");
            System.out.println("Choose 1 Option");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1: 
                    ApM.add();
                    break;
                case 2:
                    ApM.search();
                    break;
                case 3:
                    ApM.edit();
                    break;
                case 4:
                    ApM.delete();
                    break;
                case 5:
                    dm.showDashboard();
                default: 
                    System.out.println("Invalid choice");

            }


        }
    }
}
