//Handles all the appointement data

public class Appointment {
    private String id;
    private String name; 
    private String species;
    private String date;
    private String appTime; 
    private String contactNum;
    private String reason;

    // Full constructor
    public Appointment(String id, String name, String species, String date, String appTime, String contactNum, String reason){
        this.id = id;
        this.name = name;
        this.species = species;
        this.date = date;
        this.appTime = appTime;
        this.contactNum = contactNum;
        this.reason = reason;
    }

    // Constructor that parses a string and takes date and appTime separately
    public Appointment(String data, String date, String appTime) {
        String[] parts = data.split(" \\| ");
        this.id = parts[0].trim();
        this.name = parts[1].trim();
        this.species = parts[2].trim();
        this.date = date;
        this.appTime = appTime;
        this.contactNum = "";
        this.reason = "";
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getDate() { return date; }
    public String getAppTime() { return appTime; }
    public String getContactNum() { return contactNum; }
    public String getReason() { return reason; }
    public String getClientId() { return id != null && id.length() >= 3 ? id.substring(0, 3) : ""; }

    public void setDate(String date) { this.date = date; }
    public void setAppTime(String appTime) { this.appTime = appTime; }
    public void setContactNum(String contactNum) { this.contactNum = contactNum; }

    //What will it look like in the file
    public String toFileString() {
        return id + " | " + name + " | " + species + " | " + date + " | " + appTime + " | " + contactNum + " | " + reason;
    }
}
