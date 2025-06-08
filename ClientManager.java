import java.io.*;
import java.util.*;

public class ClientManager implements AdminActions {

    private Scanner sc;
    public static final List<Client> clients = new ArrayList<>();
    private static int lastId = 0;
    public static Client lastAddedClient;


    // Static block to load clients from file on class load
    static {
        try {
            loadClientsFromFile();
        } catch (IOException e) {
            System.out.println("An unexpected error occurred. Could not load clients: " + e.getMessage());
        }
    }

    public ClientManager() {
        this.sc = new Scanner(System.in);
    }

    // Check if a client is registered by ID
    public boolean isClientRegistered(String clientId) {
        for (Client c : clients) {
            if (c.getId().equals(clientId)) {
                return true;
            }
        }
        return false;
    }

    public List<Client> getClients() { //FOR POS
        return new ArrayList<>(clients);
    }

    // Get client name by ID
    public String getClientNameById(String clientId) {
        for (Client c : clients) {
            if (c.getId().equals(clientId)) {
                return c.getName();
            }
        }
        return null;
    }

    private static void loadClientsFromFile() throws IOException {
        clients.clear();
        lastId = 0;

        File file = new File("ListofClients.txt");
        if (!file.exists()) {
            file.createNewFile();
            return; //Creating file 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String contactNum = parts[3].trim();

                    Client client = new Client(id, name, email, contactNum);
                    clients.add(client);
                    lastAddedClient = client;

                    // Update lastId
                    try {
                        int num = Integer.parseInt(id.replaceAll("[^0-9]", ""));
                        if (num > lastId) lastId = num;
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }
    }

    public static String generateClientId() {
        lastId++;
        return String.format("CLT%03d", lastId);
    }

    public static void saveClient(Client client) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ListofClients.txt", true))) {
            writer.write(client.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving client to file: " + e.getMessage());
        }
    }

    public static void overwriteFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ListofClients.txt"))) {
            for (Client client : clients) {
                writer.write(client.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An unexpected error occurred during file overwrite: " + e.getMessage());
        }
    } //Duplicate method, use saveAllClientsToFile instead 
    

    public static Client findClientByName(String name) {
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name.trim())) {
                return client;
            }
        }
        return null;
    }
    public static Client findClientbyId(String id) {
        for (Client client : clients) {
            if (client.getId().equalsIgnoreCase(id.trim())) {
                return client;
            }
        }
        return null;
    }
    public static Client deleteClientById(String id) {
        Iterator<Client> iterator = clients.iterator();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getId().equalsIgnoreCase(id.trim())) {
                iterator.remove();
                overwriteFile();
                return client; // Return the deleted client
            }
        }
        return null; // Client not found
    }
    public static Client updateClientById(String newName, String newEmail, String newContactNum, String id) {
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            if (client.getId().equalsIgnoreCase(id.trim())) {
                // Update the client details
                Client updatedClient = new Client(client.getId(), newName, newEmail, newContactNum);
                clients.set(i, updatedClient);
                overwriteFile();
                return updatedClient; // Return the updated client
            }
        }
        return null; // Client not found
    }
    public static Client saveAllClientsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ListofClients.txt"))) {
            for (Client client : clients) {
                writer.write(client.toFileString());
                writer.newLine();
            }
            return null; // Return null if no error occurs
        } catch (IOException e) {
            System.out.println("An unexpected error occurred while saving clients: " + e.getMessage());
            return null; // Return null if an error occurs
        }
    }
    public static String reloadClientsFromFile() {
        try {
            loadClientsFromFile();
            return "Clients reloaded successfully.";
        } catch (IOException e) {
            return "An error occurred while reloading clients: " + e.getMessage();
        }
    }

    @Override
    public void add() {
        String name, email, contactNum;

        // Name validation
        while (true) {
            try {
                System.out.print("Enter Client's Name: ");
                name = sc.nextLine().trim();
                if (name.isEmpty()) throw new IllegalArgumentException("Name must not be empty.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Email validation
        while (true) {
            try {
                System.out.print("Enter Client's Email: ");
                email = sc.nextLine().trim();
                if (!email.endsWith("@gmail.com")) throw new IllegalArgumentException("Email must end with @gmail.com.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Contact number validation
        while (true) {
            try {
                System.out.print("Enter Client's Phone Number: ");
                contactNum = sc.nextLine().trim();
                if (!contactNum.matches("09\\d{9}")) throw new IllegalArgumentException("Number must be 11 digits and start with 09.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String id = generateClientId();
        Client client = new Client(id, name, email, contactNum);

        clients.add(client);
        saveClient(client);

        System.out.println("Client added successfully.");
        System.out.println("Client ID: " + id);

        lastAddedClient = client;
    }

    @Override
    public void viewAll() {
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
            return;
        }

        System.out.println("\nList of Clients");
        System.out.println("===============================================");
        for (Client client : clients) {
            System.out.println(client.toFileString());
        }
        System.out.println("===============================================");
    }

    @Override
    public void edit() { //Update a client by ID
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
            return;
        }
        System.out.print("Enter Client's ID to update: ");
        String searchId = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            if (client.getId().equalsIgnoreCase(searchId)) {
                System.out.println("Updating Client: " + client.getName());

                // Email validation
                String newEmail;
                while (true) {
                    try {
                        System.out.print("New Email (leave blank to keep current): ");
                        newEmail = sc.nextLine().trim();
                        if (newEmail.isEmpty()) newEmail = client.getEmail();
                        if (!newEmail.endsWith("@gmail.com")) throw new IllegalArgumentException("Email must end with @gmail.com.");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // Contact number validation
                String newContactNum;
                while (true) {
                    try {
                        System.out.print("New Contact Number (leave blank to keep current): ");
                        newContactNum = sc.nextLine().trim();
                        if (newContactNum.isEmpty()) newContactNum = client.getContactNum();
                        if (!newContactNum.matches("09\\d{9}")) throw new IllegalArgumentException("Number must be 11 digits and start with 09.");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                Client updatedClient = new Client(client.getId(), client.getName(), newEmail, newContactNum);
                clients.set(i, updatedClient);
                overwriteFile();

                System.out.println("Client updated successfully.");
                System.out.println("ID: " + updatedClient.getId());
                System.out.println("Name: " + updatedClient.getName());
                System.out.println("Email: " + updatedClient.getEmail());
                System.out.println("Contact Number: " + updatedClient.getContactNum());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Client ID not found.");
        }
    }

    @Override
    public void delete() { //Delete a client by ID 
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
            return;
        }
        System.out.print("Enter Client's ID to delete: ");
        String searchId = sc.nextLine().trim();

        boolean found = false;
        Iterator<Client> iterator = clients.iterator();

        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getId().equalsIgnoreCase(searchId)) {
                System.out.print("Are you sure you want to delete client " + client.getName() + " (Y/N)? ");
                String confirmation = sc.nextLine().trim();

                if (confirmation.equalsIgnoreCase("Y")) {
                    iterator.remove();
                    overwriteFile();
                    System.out.println("Client deleted successfully.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Client ID not found.");
        }
    }

    @Override
    public void search() { //Search for a client by ID
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
            return;
        }
        System.out.print("Enter Client's ID to search: ");
        String searchId = sc.nextLine().trim();

        boolean found = false;
        for (Client client : clients) {
            if (client.getId().equalsIgnoreCase(searchId)) {
                System.out.println("\nClient Found:");
                System.out.println("ID: " + client.getId());
                System.out.println("Name: " + client.getName());
                System.out.println("Email: " + client.getEmail());
                System.out.println("Contact Number: " + client.getContactNum());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Client not found.");
        }
    }
}

