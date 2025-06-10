public class Services {
    public static class Service {
    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public String toFileString() {
        return name + "," + price;
    }

    public static Service fromFileString(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        double price = Double.parseDouble(parts[1]);
        return new Service(name, price);
    }
}

    public Services(String serviceName, double price) {
        Services service = new Services(serviceName, price);
        // You can
  
    }

    public String getServiceName() {
        // Return a default value or implement as needed
        return "";
    }

    public double getPrice() {
        // Return a default value or implement as needed
        return 0.0;
    }

    
}
