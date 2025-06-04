import java.util.*;

public class ServicesManager {
    private List<Service> services;

    public ServicesManager() {
        services = new ArrayList<>();
        loadDefaultServices();
    }

    private void loadDefaultServices() {
        // CATS
        addService("Cat", "General Consultation", 500);
        addService("Cat", "Follow-up Consultation", 400);
        addService("Cat", "Anti-Rabies", 350);
        addService("Cat", "4-in-1 Vaccine", 900);
        addService("Cat", "Feline Leukemia (FeLV)", 1450);
        addService("Cat", "Deworming", 450);
        addService("Cat", "CBC", 1200);
        addService("Cat", "Blood Chemistry", 3000);
        addService("Cat", "Urinalysis", 800);
        addService("Cat", "Fecalysis", 250);
        addService("Cat", "Skin Scraping", 250);
        addService("Cat", "X-ray", 2000);
        addService("Cat", "Ultrasound", 850);
        addService("Cat", "Dental Cleaning", 3500);
        addService("Cat", "Tooth Extraction (Simple)", 300);
        addService("Cat", "Tooth Extraction (Complicated)", 700);
        addService("Cat", "Spay (Female)", 5000);
        addService("Cat", "Neuter (Male)", 4000);
        addService("Cat", "Microchipping", 150);
        addService("Cat", "Boarding (per day)", 700);

        // DOGS
        addService("Dog", "General Consultation", 500);
        addService("Dog", "Follow-up Consultation", 400);
        addService("Dog", "Anti-Rabies", 350);
        addService("Dog", "5-in-1 Vaccine", 950);
        addService("Dog", "6-in-1 Vaccine", 1200);
        addService("Dog", "8-in-1 Vaccine", 850);
        addService("Dog", "Kennel Cough", 800);
        addService("Dog", "Deworming", 500);
        addService("Dog", "CBC", 1200);
        addService("Dog", "Blood Chemistry", 3000);
        addService("Dog", "Urinalysis", 800);
        addService("Dog", "Fecalysis", 250);
        addService("Dog", "Skin Scraping", 250);
        addService("Dog", "Parvo Test", 1200);
        addService("Dog", "Distemper Test", 1000);
        addService("Dog", "Ehrlichia Test", 1000);
        addService("Dog", "Heartworm Test", 1000);
        addService("Dog", "X-ray", 2000);
        addService("Dog", "Ultrasound", 850);
        addService("Dog", "Dental Cleaning", 3500);
        addService("Dog", "Tooth Extraction (Simple)", 300);
        addService("Dog", "Tooth Extraction (Complicated)", 700);
        addService("Dog", "Spay (Female)", 5000);
        addService("Dog", "Neuter (Male)", 4000);
        addService("Dog", "Cesarean Section", 7000);
        addService("Dog", "Otoplasty (Ear Cropping)", 3000);
        addService("Dog", "Heartworm Preventives (Injection)", 4500);
        addService("Dog", "Microchipping", 150);
        addService("Dog", "Boarding (per day)", 700);
    }

    public void viewServices() {
        if (services.isEmpty()) {
            System.out.println("No services available.");
            return;
        }

        System.out.println("\nAvailable Services:");
        for (int i = 0; i < services.size(); i++) {
            Service s = services.get(i);
            System.out.printf("[%d] [%s] %s - ₱%.2f%n", i + 1, s.getPetType(), s.getServiceName(), s.getPrice());
        }
    }

    public void addService(String petType, String serviceName, double price) {
        services.add(new Service(petType, serviceName, price));
        System.out.println("Service added successfully.");
    }

    public void editService(int index, String newName, double newPrice) {
        if (index < 1 || index > services.size()) {
            System.out.println("Invalid service number.");
            return;
        }
        Service s = services.get(index - 1);
        s.setServiceName(newName);
        s.setPrice(newPrice);
        System.out.println("Service updated successfully.");
    }

    public void deleteService(int index) {
        if (index < 1 || index > services.size()) {
            System.out.println("Invalid service number.");
            return;
        }
        services.remove(index - 1);
        System.out.println("Service deleted successfully.");
    }

    public List<Service> getAllServices() {
        return services;
    }

    public List<Service> getServicesByPetType(String petType) {
        List<Service> result = new ArrayList<>();
        for (Service s : services) {
            if (s.getPetType().equalsIgnoreCase(petType)) {
                result.add(s);
            }
        }
        return result;
    }

    public Service getServiceByIndex(int index) {
        if (index >= 0 && index < services.size()) {
            return services.get(index);
        }
        return null;
    }
}
