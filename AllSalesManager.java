//This class manages all sales records, allowing viewing and searching through them.

import java.io.*;
import java.util.*;

public class AllSalesManager {
    private static final String SALES_FILE = "allsales.txt";

    // Loads all sales records from the file and returns as a list of strings
    public static List<String> loadAllSales() {
        List<String> sales = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sales.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading sales file.");
        }
        return sales;
    }

    // Displays all sales records to the console
    public static void viewAllSales() {
        List<String> sales = loadAllSales();
        if (sales.isEmpty()) {
            System.out.println("No sales records found.");
        } else {
            System.out.println("\n--- SALES RECORDS ---");
            int index = 0;
            for (String sale : sales) {
                System.out.println(index + ". " + sale);
                index++;
            }
        }
    }

    //Searches sales records based on Client Id and display all the transactions related to the ID
    public static void searchSales(String query) { 
        List<String> sales = loadAllSales();
        boolean found = false;
        System.out.println("\n--- SEARCH RESULTS ---");
        int index = 0;
        for (String sale : sales) {
            if (sale.toLowerCase().contains(query.toLowerCase())) {
                System.out.println(index + ". " + sale);
                found = true;
            }
            index++;
        }
        if (!found) {
            System.out.println("No sales found matching: " + query);
        }
    }
}