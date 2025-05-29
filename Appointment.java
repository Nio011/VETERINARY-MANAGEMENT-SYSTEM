public class Appointment {
    private String id;
    private String name; 
    private String breed;
    private String date;
    private String appTime; 
    private String contactNum;
    private String reason;

    public Appointment(String id, String name, String breed, String date, String appTime, String contactNum, String reason){
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.date = date;
        this.appTime = appTime;
        this.contactNum = contactNum;
        this.reason = reason;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getDate() { return date; }
    public String getAppTime() { return appTime; }
    public String getContactNum() { return contactNum; }
    public String getReason() { return reason; }

    public void setDate(String date) { this.date = date; }
    public void setAppTime(String appTime) { this.appTime = appTime; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + breed + " | " + date + " | " + appTime + " | " + contactNum + " | " + reason;
    }
}
