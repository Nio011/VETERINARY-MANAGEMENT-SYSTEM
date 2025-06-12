// Description: This class manages animal records, allowing for addition, viewing, editing, deletion, and searching of animals.

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalManager implements AdminActions{
    
    private Scanner sc;
    public static List<Animal> animals = new ArrayList<>();
    public static String filename = "animals.txt";
    public ClientManager clientManager;

    public AnimalManager(Scanner sc, ClientManager cm) {
        this.sc = sc;
        this.clientManager = cm;
    }

    static {
        loadFromFile();
    }

    //Method to add a new animal
    @Override
    public void add() {
    String clientId;
    String clientName;

    while (true) {
        System.out.print("Enter Client ID: ");
        clientId = sc.nextLine().trim();

        if (!clientManager.isClientRegistered(clientId)) {
            System.out.println("Client ID not registered. Please register the client first.");
            
            // Ask user if they want to register now
            System.out.print("Do you want to register this client now? (Y/N): ");
            String choice = sc.nextLine().trim();

            if (choice.equalsIgnoreCase("Y")) {
                clientManager.add(); 
                clientId = ClientManager.lastAddedClient.getId();
                clientName = ClientManager.lastAddedClient.getName();
                System.out.println("Client registered successfully.");
                break;
            } else {
                System.out.println("Animal addition cancelled.");
                return; 
            }
        } else {
            clientName = clientManager.getClientNameById(clientId);
            if (clientName == null) {
                System.out.println("Error retrieving client name. Cannot proceed.");
                return;
            }
            break;  
        }
    }

    // Code block for getting animal details 
        System.out.println("Client ID: " + clientId);
        System.out.println("Client Name: " + clientName);

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

        Animal animal;
        if (species.equalsIgnoreCase("Dog")) {
            animal = new Dog(clientId, clientName, petName, breed, dob, weight);
        } else if (species.equalsIgnoreCase("Cat")) {
            animal = new Cat(clientId, clientName, petName, breed, dob, weight);
        } else {
            System.out.println("Species not supported.");
            return;
        }

        animals.add(animal);
        System.out.println("Animal added successfully for Client ID: " + clientId);
        saveToFile();
    }

    //Method to view all animals
    @Override
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

    //Method for editing an animal's details
    @Override
    public void edit() {
        System.out.print("Enter Client ID to update: ");
        String clientId = sc.nextLine().trim();

        Animal animal = findAnimalByClientId(clientId);
        if (animal == null) {
            System.out.println("Client ID not found.");
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

    //Method to delete an animal by Client ID
    @Override
    public void delete() {
        System.out.print("Enter Client ID to delete: ");
        String clientId = sc.nextLine().trim();

        Animal animal = findAnimalByClientId(clientId);
        if (animal == null) {
            System.out.println("Client ID not found");
            return;
        }

        animals.remove(animal);
        System.out.println("Animal deleted successfully.");
        saveToFile();
    }

        //If an animal is deleted, the client will also be deleted
        public static String deleteAnimalbyClientid(String clientId) {
        boolean found = false;

        // Use removeIf to delete all animals with matching client ID
        found = animals.removeIf(a -> a.getId().equalsIgnoreCase(clientId));

        if (found) {
            saveToFile();
            // Delete the client as well
            ClientManager.deleteClientById(clientId);
            return "All animals and the client for Client ID " + clientId + " have been deleted.";
        } else {
            return "No animals found for Client ID " + clientId + ".";
        }
    }

    public static Animal findAnimalByClientId(String clientId) { //finding an animal by Client ID
        if (clientId == null || clientId.isEmpty()) {
            return null; // Return null if clientId is null or empty
        }
        if (animals.isEmpty()) {
            return null; // Return null if the list is empty
        }
        // Using enhanced for loop to find the animal
        clientId = clientId.trim(); // Trim whitespace from clientId
        for (Animal a : animals) {
            if (a.getId().equalsIgnoreCase(clientId)) {
                return a;
            }
        }
        return null;
    }

//Method to save the list of animals to a file
    public static void saveToFile() { 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Animal a : animals) {
              
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%.2f%n",
                        a.getId(),
                        a.getName(),
                        a.getPetName(),
                        a.getBreed(),
                        a.getSpecies(),
                        a.getDateOfBirth().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        a.getWeight()));
            }
        } catch (IOException e) {
            System.out.println("Error saving animals to file: " + e.getMessage());
        }
    }

    //Searching an animal by Client ID
    @Override
    public void search(){
        System.out.println("Enter Client ID to search");
        String clientId = sc.nextLine().trim();
        
        Animal animal = findAnimalByClientId(clientId);
        if (animal == null) {
            System.out.println("Client ID not found.");
        } else {
            System.out.println("Animal found: " + animal.toFileString());
        }
    }
    
    // Method to load animals from a file
    public static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 7) continue; // Skip invalid lines

                String id = parts[0].trim();
                String name = parts[1].trim();
                String petName = parts[2].trim();
                String breed = parts[3].trim();
                String species = parts[4].trim();
                LocalDate dob = LocalDate.parse(parts[5].trim(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                double weight = Double.parseDouble(parts[6].trim());

                Animal animal;
                if (species.equalsIgnoreCase("Dog")) {
                    animal = new Dog(id, name, petName, breed, dob, weight);
                } else if (species.equalsIgnoreCase("Cat")) {
                    animal = new Cat(id, name, petName, breed, dob, weight);
                } else {
                    continue; // Skip unsupported species
                }

                animals.add(animal);
            }
        } catch (IOException e) {
            System.out.println("Error loading animals from file: " + e.getMessage());
        }
    }
}