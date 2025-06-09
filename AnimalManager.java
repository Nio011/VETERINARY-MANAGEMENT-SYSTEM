import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalManager implements AdminActions{
    private Scanner sc;
    List<Animal> animals;
    private int lastAnimalId = 0;
    private final String filename = "animals.txt";

    public AnimalManager(Scanner sc) {
        this.sc = sc;
        this.animals = new ArrayList<>();
        loadFromFile();
    }


    public void add() {
        System.out.print("Enter Client Name: ");
        String clientName = sc.nextLine().trim();

        System.out.print("Enter Pet Name: ");
        String petName = sc.nextLine().trim();

        System.out.print("Enter Species (Dog or Cat): ");
        String species = sc.nextLine().trim();

        System.out.print("Enter Breed: ");
        String breed = sc.nextLine().trim();

        System.out.print("Enter Date of Birth (MM/dd/yyyy): ");
        String dobStr = sc.nextLine().trim();

        LocalDate dob;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            dob = LocalDate.parse(dobStr, formatter);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use MM/dd/yyyy.");
            return;
        }

        System.out.print("Enter Weight (kg): ");
        double weight;
        try {
            weight = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight format.");
            return;
        }

        String id = generateAnimalId();

        Animal animal;
        if (species.equalsIgnoreCase("Dog")) {
            animal = new Dog(id, clientName, petName, breed, dob, weight);
        } else if (species.equalsIgnoreCase("Cat")) {
            animal = new Cat(id, clientName, petName, breed, dob, weight);
        } else {
            System.out.println("Species not supported.");
            return;
        }

        animals.add(animal);
        System.out.println("Animal added successfully with ID: " + id);
        saveToFile();
    }

    public void viewAll() {
        if (animals.isEmpty()) {
            System.out.println("No animals to display.");
            return;
        }
        System.out.println("\nList of Animals:");
        for (Animal a : animals) {
            System.out.println(a.toFileString());
        }
    }

    public void edit() {
        System.out.print("Enter Animal ID to update: ");
        String id = sc.nextLine().trim();

        Animal animal = findAnimalById(id);
        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }

        System.out.println("Updating animal: " + animal.toString());

        System.out.print("Enter new Client Name (or press Enter to keep current): ");
        String newClientName = sc.nextLine().trim();
        if (!newClientName.isEmpty()) {
            animal.setName(newClientName);
        }

        System.out.print("Enter new Pet Name (or press Enter to keep current): ");
        String newPetName = sc.nextLine().trim();
        if (!newPetName.isEmpty()) {
            animal.setPetName(newPetName);
        }

        System.out.print("Enter new Breed (or press Enter to keep current): ");
        String newBreed = sc.nextLine().trim();
        if (!newBreed.isEmpty()) {
            animal.setBreed(newBreed);
        }

        System.out.print("Enter new Date of Birth (MM/dd/yyyy) (or press Enter to keep current): ");
        String dobStr = sc.nextLine().trim();
        if (!dobStr.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate dob = LocalDate.parse(dobStr, formatter);
                animal.setDateOfBirth(dob);
            } catch (Exception e) {
                System.out.println("Invalid date format. Date not updated.");
            }
        }

        System.out.print("Enter new Weight (kg) (or press Enter to keep current): ");
        String weightStr = sc.nextLine().trim();
        if (!weightStr.isEmpty()) {
            try {
                double weight = Double.parseDouble(weightStr);
                animal.setWeight(weight);
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight format. Weight not updated.");
            }
        }

        System.out.println("Animal updated.");
        saveToFile();
    }

    public void delete() {
        System.out.print("Enter Animal ID to delete: ");
        String id = sc.nextLine().trim();

        Animal animal = findAnimalById(id);
        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }

        animals.remove(animal);
        System.out.println("Animal deleted successfully.");
        saveToFile();
    }

    private Animal findAnimalById(String id) {
        for (Animal a : animals) {
            if (a.getId().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    public String generateAnimalId() {
        lastAnimalId++;
        return String.format("ANM%03d", lastAnimalId);
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Animal a : animals) {
              
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%.2f%n",
                        a.getId(),
                        a.getName(),
                        a.getPetName(),
                        a.getBreed(),
                        a.getSpecies(),
                        a.getDateOfBirth().toString(), 
                        a.getWeight()));
            }
        } catch (IOException e) {
            System.out.println("Error saving animals to file: " + e.getMessage());
        }
    }

    public void search(){
        System.out.println("nice");
    }

    public void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            return; 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 7) continue; 

                String id = parts[0];
                String clientName = parts[1];
                String petName = parts[2];
                String breed = parts[3];
                String species = parts[4];
                LocalDate dob = LocalDate.parse(parts[5]);
                double weight = Double.parseDouble(parts[6]);

                Animal animal;
                if (species.equalsIgnoreCase("Dog")) {
                    animal = new Dog(id, clientName, petName, breed, dob, weight);
                } else if (species.equalsIgnoreCase("Cat")) {
                    animal = new Cat(id, clientName, petName, breed, dob, weight);
                } else {
                    continue; 
                }

                animals.add(animal);

                try {
                    int num = Integer.parseInt(id.replace("ANM", ""));
                    if (num > lastAnimalId) lastAnimalId = num;
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            System.out.println("Error loading animals from file: " + e.getMessage());
        }
    }
}
