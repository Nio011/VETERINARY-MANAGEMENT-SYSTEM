import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentManager {

    private static List<Appointment> appointments = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    static {
        try { loadAppointmentsFromFile();
        } catch (IOException e) {
            System.out.println("An unexpected error occurred. Could not load appointments at the moment: " + e.getMessage());
        }
    }

     private static void loadAppointmentsFromFile() throws IOException {
        File file = new File("appointments.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 7) {
                    Appointment appointment = new Appointment(
                        parts[0], parts[1], parts[2],
                        parts[3], parts[4], parts[5], parts[6]
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
                         a.getContactNum() + " | " + a.getReason());
                bw.newLine();
            }
        }
    }
}
