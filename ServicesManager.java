import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServicesManager {
    private static final String FILE_NAME = "services.txt";
    private List<Service> services;

    public ServicesManager() {
        services = new ArrayList<>();
        loadServices();
    }

    private void loadServices() {
        services.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Service service = Service.fromFileString(line);
                services.add(service);
            }
        } catch (IOException e) {
            System.out.println("Error loading services: " + e.getMessage());
        }
    }

    private void saveServices() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Service service : services) {
                bw.write(service.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving services: " + e.getMessage());
        }
    }

    public List<Service> getAllServices() {
        return services;
    }

    public void addService(Service service) {
        services.add(service);
        saveServices();
        System.out.println("Service added successfully.");
    }

    public void updateService(int index, Service updatedService) {
        if (index >= 0 && index < services.size()) {
            services.set(index, updatedService);
            saveServices();
            System.out.println("Service updated successfully.");
        } else {
            System.out.println("Invalid service index.");
        }
    }

    public void deleteService(int index) {
        if (index >= 0 && index < services.size()) {
            services.remove(index);
            saveServices();
            System.out.println("Service deleted successfully.");
        } else {
            System.out.println("Invalid service index.");
        }
    }
}
