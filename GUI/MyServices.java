public class MyServices {
    

    

    public static class Services {
    private String serviceName;
    private double price;
    

    

    public Services(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }
    

    public String getName() { return serviceName; }
    public double getPrice() { return price; }

    public String toFileString() {
        return serviceName + "," + price;
    }

    public static Services fromFileString(String line) {
        String[] parts = line.split(",");
        String serviceName = parts[0];
        double price = Double.parseDouble(parts[1]);
        return new Services(serviceName, price);
    }
}

    public MyServices(String serviceName, double price) {
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
