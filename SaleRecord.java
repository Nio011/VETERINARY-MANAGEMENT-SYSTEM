import java.util.List;

public class SaleRecord {
    private String clientName;
    private String petType;
    private List<Service> services;
    private double total;
    private String date;

    public SaleRecord(String clientName, String petType, List<Service> services, double total, String date) {
        this.clientName = clientName;
        this.petType = petType;
        this.services = services;
        this.total = total;
        this.date = date;
    }

    public void display() {
        System.out.println("Client: " + clientName);
        System.out.println("Pet Type: " + petType);
        System.out.println("Date: " + date);
        System.out.println("Services:");
        for (Service service : services) {
            System.out.println("- " + service.getServiceName() + ": ₱" + String.format("%.2f", service.getPrice()));
        }
        System.out.println("Total: ₱" + String.format("%.2f", total));
    }
}
