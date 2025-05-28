 class Admin {
    private final String username = "admin"; 
    private String pass = "admin123"; 

    public boolean login (String username, String pass){ //method for log in
        return this.username.equals(username) && this.pass.equals(pass);
    }

    public void changePass (String oldPass, String newPass){ //method for changing password 
        if (pass.equals(oldPass)){
            pass = newPass;
            System.out.println("Password changed successfully");
        } else {
            System.out.println ("Incorrect password. Password not change");
        }
    }


}
