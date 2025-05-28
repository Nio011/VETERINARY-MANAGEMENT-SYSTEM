import java.util.Scanner;

public class LoginManager {
    private Admin admin;
    private Scanner login; 

    public LoginManager(Admin admin, Scanner login){;
        this.admin = admin; 
        this.login = login;
    }

    public void loggingIn(){
        System.out.println ("Enter username: ");
        String user = login.nextLine();
        System.out.println ("Enter password: ");
        String pass = login.nextLine();

        if (admin.login(user, pass)){
            DashboardModule dm = new DashboardModule();
            dm.showDashboard();
            
        } else {
            System.out.println("Login Failed");
        }
    }

}
