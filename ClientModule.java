import java.util.*;

public class ClientModule {

    public static void showClientMenu(){

        Scanner sc = new Scanner(System.in);

        ClientManager cm = new ClientManager();
        DashboardModule dm = new DashboardModule();

        cm.viewAll(); 
        
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
                    cm.add();
                    break;
                case 2:
                    cm.search();
                    break;
                case 3:
                    cm.edit();
                    break;
                case 4:
                    cm.delete();
                    break;
                case 5:
                    dm.showDashboard();
                default: 
                    System.out.println("Invalid choice");

            }



        }
    }

    


}
