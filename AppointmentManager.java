import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentManager implements AdminActions {

    private static List<Appointment> appointments = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);


    static {
        try {
            loadAppointmentsFromFile();
        } catch (IOException e) {
            System.out.println("An unexpected error occurred. Could not load appointments at the moment: " + e.getMessage());
        }
    }

    private static void loadAppointmentsFromFile() throws IOException {
        File file = new File("appointments.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            appointments.clear();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 8) {
                    Appointment appointment = new Appointment(
                        parts[0], parts[1], parts[2],
                        parts[3], parts[4], parts[5], parts[6], parts[7]
                    );
                    appointments.add(appointment);
                }
            }
        }
    }

    public void saveAppointmentsToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (Appointment a : appointments) {
                bw.write(a.getId() + " | " + a.getName() + " | " + a.getBreed() + " | " +
                         a.getDate() + " | " + a.getAppTime() + " | " +
                         a.getContactNum() + " | " + a.getReason() + " | " + a.getStatus());
                bw.newLine();
            }
        }
    }

    @Override
    public void add() {
        AppointmentClient appointmentClient = new AppointmentClient();
        appointmentClient.bookAppointment();  

        try {
            loadAppointmentsFromFile(); 
        } catch (IOException e) {
            System.out.println("Error reloading appointments after booking: " + e.getMessage());
        }
    }

    @Override 
    public void edit() { //edit appointment deets
        System.out.print("Enter Client ID to edit: ");
        String id = sc.nextLine();
        boolean found = false;

        for (Appointment a : appointments) {
            if (a.getId().equalsIgnoreCase(id)) {
                System.out.print("New Date: ");
                a.setDate(sc.nextLine());

                System.out.print("New Time: ");
                a.setAppTime(sc.nextLine());

                found = true;
                break;
            }
        }

        if (found) {
            try {
                saveAppointmentsToFile();
                System.out.println("Appointment updated successfully.");
            } catch (IOException e) {
                System.out.println("Failed to save: " + e.getMessage());
            }
        } else {
            System.out.println("Appointment not found.");
        }
    }

    @Override
    public void delete() {
        System.out.print("Enter Client ID to delete: ");
        String id = sc.nextLine();
        boolean removed = appointments.removeIf(a -> a.getId().equalsIgnoreCase(id));

        if (removed) {
            try {
                saveAppointmentsToFile();
                System.out.println("Appointment deleted.");
            } catch (IOException e) {
                System.out.println("Failed to save changes: " + e.getMessage());
            }
        } else {
            System.out.println("Appointment not found.");
        }
    }

    @Override
    public void search() {
        System.out.print("Enter Client ID or Name: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Appointment a : appointments) {
            if (a.getId().toLowerCase().contains(keyword) || a.getName().toLowerCase().contains(keyword)) {
                displayAppointment(a);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointment found.");
        }
    }

    private void displayAppointment(Appointment a) {
        System.out.println("\n--- Appointment Details ---");
        System.out.println("Client ID     : " + a.getId());
        System.out.println("Client Name   : " + a.getName());
        System.out.println("Breed         : " + a.getBreed());
        System.out.println("Date          : " + a.getDate());
        System.out.println("Time          : " + a.getAppTime());
        System.out.println("Contact No.   : " + a.getContactNum());
        System.out.println("Reason        : " + a.getReason());
        System.out.println("Status        : " + a.getStatus());
        System.out.println("----------------------------\n");
    }

    @Override
    public void viewAll() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
        }
    }

    public void editStatus() { //edit if pending, completed, canceled
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the Client ID of the appointment to update status: ");
    String id = sc.nextLine().trim();

    boolean found = false;

    for (Appointment a : appointments) {
        if (a.getId().equalsIgnoreCase(id)) {
            System.out.println("Appointment found:");
            displayAppointment(a);

            System.out.print("Enter new status (e.g., Pending, Completed, Canceled): ");
            String newStatus = sc.nextLine().trim();


            a.setStatus(newStatus);
            System.out.println("Status updated successfully!");

            try {
                saveAppointmentsToFile();
            } catch (IOException e) {
                System.out.println("Failed to save updated status: " + e.getMessage());
            }

            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("No appointment found with ID: " + id);
    }
}

}
