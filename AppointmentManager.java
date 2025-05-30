import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.*;
import java.util.*;

public class AppointmentManager implements AdminActions{

    Scanner scanner = new Scanner(System.in);
    static int lastAppId; //For appointments unique ID (to edit the appointment)

     public static String generateAppointmentId(){ //eto pag gegenerate na ng Appointment ID
            lastAppId++; //Increment lang kunwari 001 yung last nung nauna magiging 002 yung susunod
            return String.format("APT%03d",lastAppId);
        }

    private static List<Appointment> appointments = new ArrayList<>();

     static {
            try {
                loadAppointmentsFromFile(); //for automatic loading of client to the file
            } catch (IOException e) {
                System.out.println("An unexpected error occured. Could not load clients at the moment " + e.getMessage());
            }

        }

        private static void loadAppointmentsFromFile() throws FileNotFoundException, IOException { //Creating and Formatting yung sa client
            appointments.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null){
                String [] parts = line.split("\\|");
                if (parts.length >= 7){ //Para magkakahiwalay sila
                    String id = parts[0];
                    String name = parts[1];
                    String species = parts[2];
                    String date = parts[3];
                    String appTime = parts[4];
                    String contactNum = parts[5];
                    String reason = parts[6];


                    appointments.add(new Appointment (id, name, species, date, appTime, contactNum, reason)); 
            }
        }
        reader.close();
    }
    
}  
        public static void saveAppointment(Appointment appointment){ //Para malagay yung appointment na nasa arrayList papunta sa File
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter ("appointments.txt", true))){
                        writer.write(((Appointment) appointments).toFileString());
                        writer.newLine();
                    } catch (IOException e){
                        System.out.println("Error saving client to the file");
                    }
                }

        private static void overwriteFile(){ //Para maupdate yung information
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter ("ListofClients.txt"))){
                        for (Appointment appointments : appointments) {
                            writer.write(appointments.toFileString());
                            writer.newLine();
                        }

                    
                } catch (IOException e) {
                    System.out.println("An Unexpected Error Occured");
                }
            }
        
        private static String[] findClientDataByName(String nameInput) {
        try (BufferedReader br = new BufferedReader(new FileReader("ListofClients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 4) {
                    String fileName = data[1].trim();
                    if (fileName.equalsIgnoreCase(nameInput.trim())) {
                        return new String[] {
                            data[0].trim(),  // ID
                            data[1].trim(),  // Name
                            data[3].trim()   // Contact Number
                        };
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading client file: " + e.getMessage());
        }
    return null;
}

        private static String inputContactNumber(Scanner scanner) {
            String contactNum;
                while (true) {
                    System.out.print("Enter Contact Number (11 digits): ");
                    contactNum = scanner.nextLine().trim();
                    if (contactNum.matches("\\d{11}")) {
                        return contactNum;
                    } else {
                        System.out.println("Invalid contact number. Must be exactly 11 digits.");
                    }
                }
            }

        private static String inputAppointmentDate(Scanner scanner) {
            while (true) {
                try {
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine().trim();
                    LocalDate enteredDate = LocalDate.parse(date);
                    if (enteredDate.isBefore(LocalDate.now())) {
                        System.out.println("Date cannot be in the past.");
                    } else {
                        return date;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }
        }

        private static String inputAppointmentTime(Scanner scanner) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("hh:mm a")
                    .toFormatter(Locale.ENGLISH)
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalTime opening = LocalTime.of(8, 0);
            LocalTime closing = LocalTime.of(15, 0);

            while (true) {
                try {
                    System.out.print("Enter Appointment Time (hh:mm AM/PM): ");
                    String time = scanner.nextLine().trim().toUpperCase();

                    LocalTime enteredTime = LocalTime.parse(time, formatter);

                    if (enteredTime.isBefore(opening) || enteredTime.isAfter(closing)) {
                        System.out.println("Appointment time must be between 8:00 AM and 3:00 PM.");
                    } else {
                        return time;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please use hh:mm AM/PM (e.g., 08:00 AM, 02:30 PM).");
                }
            }
        }

    private static void appendAppointmentToFile(String record) throws IOException {
        try (FileWriter apptWriter = new FileWriter("appointments.txt", true)) {
            apptWriter.write(record + "\n");
        }
    }

        @Override
            public void add() {

                String clientId;
                String name;
                String species;
                String contactNum;
                String reason;

                try {
                    System.out.print("Enter Client Name: ");
                    name = scanner.nextLine().trim();

                    String[] clientData = findClientDataByName(name);
                    boolean isExistingClient = clientData != null;

                    if (isExistingClient) {
                        clientId = clientData[0];
                        name = clientData[1];
                        contactNum = clientData[2];
                        System.out.println("Client found! Auto-filled details:");
                        System.out.println("ID: " + clientId);
                        System.out.println("Name: " + name);
                        System.out.println("Contact Number: " + contactNum);

                        System.out.print("Enter Species: ");
                        species = scanner.nextLine().trim();

                    } else {
                        System.out.println("Client not found. Proceeding as new client.");
                        clientId = AppointmentManager.generateAppointmentId();

                        System.out.print("Enter Client Name: ");
                        name = scanner.nextLine().trim();

                        System.out.print("Enter Species: ");
                        species = scanner.nextLine().trim();

                        contactNum = inputContactNumber(scanner);
                    }

                    String date = inputAppointmentDate(scanner);
                    String time = inputAppointmentTime(scanner);

                    System.out.print("Enter Reason for Appointment: ");
                    reason = scanner.nextLine().trim();

                    String record = clientId + " | " + name + " | " + species + " | " +
                                    date + " | " + time + " | " + contactNum + " | " + reason;

                    appendAppointmentToFile(record);

                    System.out.println("\nAppointment booked successfully!");
                    System.out.println("Appointment Details:");
                    System.out.println(record);

                    if (!isExistingClient) {
                        System.out.println("Note: This appointment is not linked to a registered client.");
                    }

                } catch (IOException e) {
                    System.out.println("Error while booking appointment: " + e.getMessage());
                }
            }

        @Override
        public void edit() {
            System.out.println("Enter Client's ID: ");
            String searchId = scanner.nextLine().trim();
            boolean found = false;
            for (Appointment appointment : appointments){
                System.out.println("Checking: " + appointment.getId());
                if (appointment.getId().trim().equals(searchId.trim())){
                    System.out.println("Updating Appointment's Info: " + appointment.getName());

                    System.out.println("New Date: ");
                    String newDate = scanner.nextLine();
                    System.out.println("New Time: ");
                    String newAppTime = scanner.nextLine();

                    ((List<Appointment>) appointment).remove(appointment);
                    Appointment updated = new Appointment (searchId, appointment.getName(), newDate, newAppTime, appointment.getContactNum(), appointment.getSpecies(), appointment.getReason());
                    appointments.add(updated);
                    overwriteFile();
                    found = true; 
                    System.out.println("Client Updated");
                    System.out.println("ID: " + updated.getId());
                    System.out.println("Name: " + updated.getName());
                    System.out.println("Species: " + updated.getSpecies());
                    System.out.println("Contact Number: " + updated.getContactNum());
                    break;
                }
            }
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
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'viewAll'");
        }
            }

