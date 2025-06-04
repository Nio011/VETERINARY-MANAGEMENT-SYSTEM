public class Service {
    private String petType;
    private String serviceName;
    private double price;

    public Service(String petType, String serviceName, double price) {
        this.petType = petType;
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getPetType() {
        return petType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
