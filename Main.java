import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin();
        
        Scanner sc = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to Veterinary Management System!");
        System.out.println("1. Log In");
        System.out.println("Choose an option");
        choice = sc.nextLine();

        if (choice.equals("1")){
            LoginManager lm = new LoginManager(admin, sc);
            lm.loggingIn();
            
        } else {
            System.out.println ("Invalid Choice");
        }

        sc.close();

    }

}
