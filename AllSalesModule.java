// AllSalesModule.java
import java.io.*;
import java.util.*;

public class AllSalesModule {
    private static final String SALES_FILE = "allsales.txt";

    public static void showAllSalesMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- ALL SALES MENU ---");
            System.out.println("1. View All Sales");
            System.out.println("2. Edit Sale Entry");
            System.out.println("3. Delete Sale Entry");
            System.out.println("0. Back to Dashboard");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewAllSales();
                    break;
                case 2:
                    editSale(sc);
                    break;
                case 3:
                    deleteSale(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void viewAllSales() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            System.out.println("\n--- SALES RECORDS ---");
            int index = 0;
            while ((line = reader.readLine()) != null) {
                System.out.println(index + ". " + line);
                index++;
            }
        } catch (IOException e) {
            System.out.println("Error reading sales file.");
        }
    }

    private static void editSale(Scanner sc) {
        List<String> sales = readAllSales();
        viewAllSales();
        System.out.print("Enter index of sale to edit: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index >= 0 && index < sales.size()) {
            System.out.print("Enter new sale entry (format: Date | Amount | Client Name): ");
            String newEntry = sc.nextLine();
            sales.set(index, newEntry);
            writeAllSales(sales);
            System.out.println("Sale updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void deleteSale(Scanner sc) {
        List<String> sales = readAllSales();
        viewAllSales();
        System.out.print("Enter index of sale to delete: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index >= 0 && index < sales.size()) {
            sales.remove(index);
            writeAllSales(sales);
            System.out.println("Sale deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static List<String> readAllSales() {
        List<String> sales = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sales.add(line);
            }
        } catch (IOException e) {
            // Ignore
        }
        return sales;
    }

    private static void writeAllSales(List<String> sales) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SALES_FILE))) {
            for (String sale : sales) {
                writer.write(sale);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to sales file.");
        }
    }
}
