import java.io.*;
import java.util.*;

public class ClientManager implements AdminActions {

    private Scanner sc;
    private static List<Client> clients = new ArrayList<>();
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

    public ClientManager(Scanner sc) {
        this.sc = sc;
    }

    public ClientManager() {
        this.sc = new Scanner(System.in);
    }

    private static void loadClientsFromFile() throws IOException {
        clients.clear();
        lastId = 0;

        File file = new File("ListofClients.txt");
        if (!file.exists()) {
            file.createNewFile();
            return;  // No file yet, so no clients to load
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
                    if (num > lastId) {
                        lastId = num;
                    }
                } catch (NumberFormatException e) {

                } // Para sa Id to

                Client loadedClient = new Client(id, name, email, contactNum);
                lastAddedClient = loadedClient; 
            }
        }
        reader.close();
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

    private static void overwriteFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ListofClients.txt"))) {
            for (Client client : clients) {
                writer.write(client.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An unexpected error occurred during file overwrite: " + e.getMessage());
        }
    }

    public static Client findClientByName(String name) {
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name.trim())) {
                return client;
            }
        }
        return null;
    }

    @Override
    public void add() {
        System.out.print("Enter Client's Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Client's Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter Client's Phone Number: ");
        String contactNum = sc.nextLine().trim();

        String id = generateClientId();
        Client client = new Client(id, name, email, contactNum);

        clients.add(client);
        saveClient(client);

        System.out.println("Client added successfully.");
        System.out.println("Client ID: " + id);

        lastAddedClient = client;
    }

         @Override
         public void viewAll(){ //para makita sila sa client module
            if (clients.isEmpty()){
                System.out.println("No Clients Found");
                return;
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
    public void edit() {
        System.out.print("Enter Client's ID to update: ");
        String searchId = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            if (client.getId().equalsIgnoreCase(searchId)) {
                System.out.println("Updating Client: " + client.getName());

                System.out.print("New Email (leave blank to keep current): ");
                String newEmail = sc.nextLine().trim();
                if (newEmail.isEmpty()) newEmail = client.getEmail();

                System.out.print("New Contact Number (leave blank to keep current): ");
                String newContactNum = sc.nextLine().trim();
                if (newContactNum.isEmpty()) newContactNum = client.getContactNum();

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
    public void delete() {
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
    public void search() {
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
