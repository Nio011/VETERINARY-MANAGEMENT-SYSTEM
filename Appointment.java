public class Appointment {
    private String id;
    private String name; 
    private String species;
    private String date;
    private String appTime; 
    private String contactNum;
    private String reason;

    public Appointment(String id, String name, String species, String date, String appTime, String contactNum, String reason){
        this.id = id;
        this.name = name;
        this.species = species;
        this.date = date;
        this.appTime = appTime;
        this.contactNum = contactNum;
        this.reason = reason;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getDate() { return date; }
    public String getAppTime() { return appTime; }
    public String getContactNum() { return contactNum; }
    public String getReason() { return reason; }

    public void setDate(String date) { this.date = date; }
    public void setAppTime(String appTime) { this.appTime = appTime; }
    public void setContactNum(String contactNum) {this.contactNum = contactNum;}

    public String toFileString() {
        return id + " | " + name + " | " + species + " | " + date + " | " + appTime + " | " + contactNum + " | " + reason;
    }
}
