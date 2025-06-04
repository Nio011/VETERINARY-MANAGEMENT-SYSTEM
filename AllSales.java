import java.util.ArrayList;
import java.util.List;

public class AllSales {
    private List<SaleRecord> sales;

    public AllSales() {
        this.sales = new ArrayList<>();
    }

    public void addSale(String clientName, String petType, List<Service> services, double total, String date) {
        SaleRecord record = new SaleRecord(clientName, petType, services, total, date);
        sales.add(record);
    }

    public void displayAllSales() {
        if (sales.isEmpty()) {
            System.out.println("No sales recorded yet.");
            return;
        }

        System.out.println("=== ALL SALES ===");
        for (SaleRecord sale : sales) {
            sale.display();
            System.out.println("-------------------");
        }
    }
}
