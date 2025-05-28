import java.util.*;

public class AnimalModule {

    public static void showAnimalMenu(){

        Scanner sc = new Scanner(System.in);

        AnimalManager am = new AnimalManager();
        DashboardModule dm = new DashboardModule();

        am.viewAll(); 
        
        while (true){
            System.out.println("PET MANAGEMENT");
            System.out.println("1. Add Pets");
            System.out.println("2. Search Pets");
            System.out.println("3. Update Pets");
            System.out.println("4. Delete Pets");
            System.out.println("5. Back");
            System.out.println("Choose 1 Option");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1: 
                    am.add();
                    break;
                case 2:
                    am.search();
                    break;
                case 3:
                    am.edit();
                    break;
                case 4:
                    am.delete();
                    break;
                case 5:
                    dm.showDashboard();
                default: 
                    System.out.println("Invalid choice");

            }



        }
    }

    


}
