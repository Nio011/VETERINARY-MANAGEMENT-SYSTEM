public class Appointment {
    private String id;
    private String name; 
    private String breed;
    private String date;
    private String appTime; 
    private String contactNum;
    private String reason;
    private String status;

    public Appointment(String id, String name, String breed, String date, String appTime, String contactNum, String reason, String status){
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.date = date;
        this.appTime = appTime;
        this.contactNum = contactNum;
        this.reason = reason;
        this.status = status;



}

 public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getDate() {
        return date;
    }

    public String getAppTime() {
        return appTime;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus(){
        return status;
    }

    public void setDate(String trim) {
        this.date = trim;
    }

    public void setAppTime(String nextLine) {
        this.appTime = nextLine;
    }

    public void setStatus(String nextLine) {
        this.status = nextLine;
    }

}

