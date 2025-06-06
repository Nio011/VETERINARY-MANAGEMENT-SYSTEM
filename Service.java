public class Service {
    private String name;
    private double price;
    private String petType;

    public Service(String name, double price, String petType) {
        this.name = name;
        this.price = price;
        this.petType = petType;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getPetType() { return petType; }

    public String toFileString() {
        return name + "," + price + "," + petType;
    }

    public static Service fromFileString(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        double price = Double.parseDouble(parts[1]);
        String petType = parts[2];
        return new Service(name, price, petType);
    }
}
