import java.io.*;
import java.util.*;

//okay na to
//tama na plss
//4.0 na this

public class ClientManager implements AdminActions {

        public static Client lastAddedClient;
        private Scanner sc;
        static int lastId; //For Clients unique ID
        private static List<Client> clients = new ArrayList<>();

          static {
            try {
                loadClientsFromFile(); //for automatic loading of client to the file
            } catch (IOException e) {
                System.out.println("An unexpected error occured. Could not load clients at the moment " + e.getMessage());
            }
        }

        public ClientManager(Scanner sc) {
            this.sc = sc;
         }

        private static void loadClientsFromFile() throws IOException { //Creating and Formatting yung sa client
            clients.clear();
            lastId = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader("ListofClients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null){
                String [] parts = line.split("\\|");
                if (parts.length >= 4){ //Para magkakahiwalay sila
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String contactNum = parts[3].trim();

                    Client loadedClient = new Client(id, name, email, contactNum);
                    clients.add(loadedClient);
                    lastAddedClient = loadedClient; 

                    try {
                    int num = Integer.parseInt(id.replaceAll("[^0-9]", ""));
                    if (num > lastId) {
                        lastId = num;
                    }
                } catch (NumberFormatException e) {

                } // Para sa Id to
            }
        }
    }
}

        public static String generateClientId() { //eto pag gegenerate na ng Client ID
            lastId++; //Increment lang kunwari 001 yung last nung nauna magiging 002 yung susunod
            return String.format("CLT%03d",lastId);
        }

        public static void saveClient(Client client) { //Para malagay yung client na nasa arrayList papunta sa File
            try (BufferedWriter writer = new BufferedWriter(new FileWriter ("ListofClients.txt", true))){
                writer.write(client.toFileString());
                writer.newLine();
            } catch (IOException e){
                System.out.println("Error saving client to the file");
            }
        }

        private static void overwriteFile() { //Para maupdate yung information
            try (BufferedWriter writer = new BufferedWriter(new FileWriter ("ListofClients.txt"))){
                for (Client client : clients) {
                    writer.write(client.toFileString());
                    writer.newLine();
                }

        } catch (IOException e) {
            System.out.println("An Unexpected Error Occured");
        }
    }

         @Override
         public void add() { //basta eto yung part na iaadd tsaka isasave na yung client sa file and arrayList
           
            System.out.println("Enter Client's Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Client's Email: ");
            String email = sc.nextLine();
            System.out.println("Enter Client's Phone Number: ");
            String contactNum = sc.nextLine();

            String id = generateClientId();
            Client client = new Client(id, name, email, contactNum);
            clients.add (client);
            saveClient(client);
            System.out.println("Client's ID: " + id);
            lastAddedClient = client;
         }

         @Override
         public void viewAll(){ //para makita sila sa dashboard
            if (clients.isEmpty()){
                System.out.println("No Clients Found");
                return;
            }

            System.out.println("\nList of Clients");
            System.out.println("======================================================================");
            for (Client client : clients){
                System.out.println(client.toFileString());
            }
            System.out.println("======================================================================");
         }
         
         @Override
         public void edit() { // to update info (Contact and Email)
            System.out.println("Enter Client's ID: ");
            String searchId = sc.nextLine().trim();
            boolean found = false;

            for (int i = 0; i < clients.size(); i++) {
                Client client = clients.get(i);
                if (client.getId().trim().equalsIgnoreCase(searchId)) {
                    System.out.println("Updating Client's Info: " + client.getName());

                    System.out.println("New Email: ");
                    String newEmail = sc.nextLine();
                    System.out.println("New Contact Number: ");
                    String newContactNum = sc.nextLine();

                    Client updated = new Client(searchId, client.getName(), newEmail, newContactNum);
                    clients.set(i, updated);
                    overwriteFile();
                    found = true; 

                    System.out.println("Client Updated");
                    System.out.println("ID: " + updated.getId());
                    System.out.println("Name: " + updated.getName());
                    System.out.println("Email: " + updated.getEmail());
                    System.out.println("Contact Number: " + updated.getContactNum());
                    break;
                }
            }

            if (!found) {
              System.out.println("Client ID not found");
        }
    }

         @Override
    public void delete() {
        System.out.println("Enter Client's ID to delete: ");
        String searchId = sc.nextLine().trim();

        boolean found = false;
        Iterator<Client> iterator = clients.iterator();

        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getId().trim().equalsIgnoreCase(searchId)) {
                iterator.remove();
                overwriteFile();
                found = true;
                System.out.println("Client Deleted");
                break;
            }
        }

        if (!found) {
            System.out.println("Client ID not found");
        }
    }

    @Override
    public void search() {
        System.out.println("Enter Client's ID to search: ");
        String searchId = sc.nextLine().trim();
        boolean found = false;

        for (Client client : clients) {
            if (client.getId().equalsIgnoreCase(searchId)) {
                System.out.println("\nClient Found");
                System.out.println("ID: " + client.getId());
                System.out.println("Name: " + client.getName());
                System.out.println("Email: " + client.getEmail());
                System.out.println("Contact Number: " + client.getContactNum());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Client not found");
        }
    }
}