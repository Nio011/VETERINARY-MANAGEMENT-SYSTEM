import java.io.*;
import java.util.*;

//okay na to
public class ClientManager implements AdminActions {

        public static Client lastAddedClient;
        Scanner sc = new Scanner(System.in);
        static int lastId; //For Clients unique ID
        private static List<Client> clients = new ArrayList<>(); //Using Array list to manipulate the clients individually

          static {
            try {
                loadClientsFromFile(); //for automatic loading of client to the file
            } catch (IOException e) {
                System.out.println("An unexpected error occured. Could not load clients at the moment " + e.getMessage());
            }

        }

        private static void loadClientsFromFile() throws FileNotFoundException, IOException { //Creating and Formatting yung sa client
            clients.clear();
            lastId = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader("ListofClients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null){
                String [] parts = line.split("\\|");
                if (parts.length >= 4){ //Para magkakahiwalay sila
                    String id = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    String contactNum = parts[3];

                    clients.add(new Client (id, name, email, contactNum)); 

                    try {
                    int num = Integer.parseInt(id.replaceAll("[^0-9]", ""));
                    if (num > lastId) {
                        lastId = num;
                    }
                } catch (NumberFormatException e) {

                } // Para sa Id to

                Client loadedClient = new Client(id, name, email, contactNum);
                clients.add(loadedClient);
                lastAddedClient = loadedClient; 
            }
        }
        reader.close();
    }
    
}

        public static String generateClientId(){ //eto pag gegenerate na ng Client ID
            lastId++; //Increment lang kunwari 001 yung last nung nauna magiging 002 yung susunod
            return String.format("CLT%03d",lastId);
        }

        public static void saveClient(Client client){ //Para malagay yung client na nasa arrayList papunta sa File
            try (BufferedWriter writer = new BufferedWriter(new FileWriter ("ListofClients.txt", true))){
                writer.write(client.toFileString());
                writer.newLine();
            } catch (IOException e){
                System.out.println("Error saving client to the file");
            }
        }

        private static void overwriteFile(){ //Para maupdate yung information
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

            ClientManager.lastAddedClient = client;

            AnimalManager am = new AnimalManager();
            am.add();



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
            for (Client client : clients){
                System.out.println("Checking: " + client.getId());
                if (client.getId().trim().equals(searchId.trim())){
                    System.out.println("Updating Client's Info: " + client.getName());

                    System.out.println("New Email: ");
                    String newEmail = sc.nextLine();
                    System.out.println("New Contact Number: ");
                    String newContactNum = sc.nextLine();

                    clients.remove(client);
                    Client updated = new Client(searchId, client.getName(), newEmail, newContactNum);
                    clients.add(updated);
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
         }

         @Override
         public void delete() {
            System.out.println("Enter Client's ID to delete: ");
            String searchId = sc.nextLine();

            boolean found = false;
            Client toDelete = null; 

            for (Client client : clients){
                System.out.println("Checking client ID: [" + client.getId() + "]");
                System.out.println("Comparing with input: [" + searchId + "]");
                if (client.getId().trim().equals(searchId.trim())){
                    toDelete = client;
                    break;
                }
            }

            if (toDelete !=null){
                clients.remove(toDelete);
                overwriteFile();
                found = true;
                System.out.println("Client Deleted");
            }

            if (!found){
                System.out.println("Client ID not found");
            }
         }
         

         @Override
         public void search() {
            System.out.println("Enter Client's ID to search: ");
            String searchId = sc.nextLine();

            boolean found = false; 

            for (Client client : clients){
                if (client.getId().equalsIgnoreCase(searchId)){
                    System.out.println("\nClient Found");
                    System.out.println("ID: " + client.getId());
                    System.out.println("Name: " + client.getName());
                    System.out.println("Email: " + client.getEmail());
                    System.out.println("Contact Number: " + client.getContactNum());
                    found = true;
                    break;
                }
            }   if (!found){
                    System.out.println("Client not found");
                }

        }

}


        



        