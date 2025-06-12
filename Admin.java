 //Admin Class
// This class represents an admin user with a username and password.
// It provides methods for logging in and changing the password.
 
 class Admin {
    private final String username = "admin"; // admin username
    private String pass = "admin123"; //admin password

    public boolean login (String username, String pass){ //method for log in
        return this.username.equals(username) && this.pass.equals(pass);
    }


}
