import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnimalManager implements AdminActions {

    Scanner sc = new Scanner(System.in);
    private static List<Animal> animals = new ArrayList<>();

    static {
            try {
                loadAnimalsFromFile(); //for automatic loading of Animals to the file
            } catch (IOException e) {
                System.out.println("An unexpected error occured. Could not load clients at the moment " + e.getMessage());
            }

        }

        private static void loadAnimalsFromFile() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");;
        animals.clear();
        BufferedReader reader = new BufferedReader(new FileReader("AnimalRecords.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
             if (parts.length >= 8) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                String petName = parts[2].trim();
                String species = parts[3].trim();
                String breed = parts[4].trim();
                String dobStr = parts[5].trim();
                LocalDate dob = LocalDate.parse(dobStr, formatter);
                double weight = Double.parseDouble(parts[7].trim()); 

                Animal animal = null;
                if (species.equalsIgnoreCase("Dog")) {
                    animal = new Dog(id, name, petName, breed, dob, weight);
                } else if (species.equalsIgnoreCase("Cat")) {
                    animal = new Cat(id, name, petName, breed, dob, weight);
                } else {
                    System.out.println("Animal not supported");
                }
                animals.add(animal);
            }
        }
        reader.close();
    }

     private static void saveAnimalToFile(Animal animal) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter("AnimalRecords.txt", true))) {
            write.write(animal.toFileString());
            write.newLine();
        } catch (IOException e) {
            System.out.println("Error saving animal to file: " + e.getMessage());
        }
    }



@Override
    public void add() {
            if (ClientManager.lastAddedClient == null) {
                System.out.println("No client found. Please add a client first.");
            return;
    }

            String clientId = ClientManager.lastAddedClient.getId();
            String clientName = ClientManager.lastAddedClient.getName();

            System.out.println("Adding pet for: " + clientName + " (ID: " + clientId + ")");

            System.out.print("Enter Pet's Name: ");
            String petName = sc.nextLine();
            System.out.print("Enter Species (Dog/Cat): ");
            String species = sc.nextLine();
            System.out.print("Enter Breed: ");
            String breed = sc.nextLine();
            System.out.print("Enter Date of Birth (YYYY-MM-dd): ");
            LocalDate dob = LocalDate.parse(sc.nextLine());
            System.out.print("Enter Weight (kg): ");
            double weight = Double.parseDouble(sc.nextLine());

            Animal animal;
            if (species.equalsIgnoreCase("Dog")) {
            animal = new Dog(clientId, clientName, petName, breed, dob, weight);
            } else if (species.equalsIgnoreCase("Cat")) {
            animal = new Cat(clientId, clientName, petName, breed, dob, weight);
            } else {
                System.out.println("Unsupported Species");
                return;
            }

            animals.add(animal);
            saveAnimalToFile(animal);
            System.out.println("Pet added to " + clientName);
}


    @Override
    public void edit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void search() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }



    @Override
    public void viewAll() {
        if (animals.isEmpty()) {
        System.out.println("No pets found.");
        return;
    }

        System.out.println("List of all pets:");
            for (Animal animal : animals) {
            System.out.println(animal);
    }
    }


}
