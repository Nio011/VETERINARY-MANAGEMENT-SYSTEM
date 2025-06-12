//all the information of a client

public class Client {
// This class represents a client with an ID, name, email, and contact number.
    private String id;
    private String name;
    private String email;  
    private String contactNum;
    
    //Constructor
    public Client(String id, String name, String email, String contactNum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
    }

    //getters and setters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContactNum() { return contactNum; }
    public String getId() { return id; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setContactNum(String contactNum) { this.contactNum = contactNum; }
    public void setId(String id) { this.id = id; }


    public String toFileString() {
        return id + " | " + name + " | " + email + " | " + contactNum; 
    }
}
