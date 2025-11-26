package md.utm.tmps.utilities;

import md.utm.tmps.domain.models.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Utility class for managing and querying order history
public class OrderHistory {
    private final List<OrderRecord> records;

    public OrderHistory() {
        this.records = new ArrayList<>();
    }

    public void recordOrder(Order order, String action) {
        records.add(new OrderRecord(
                order.getOrderId(),
                order.getCustomerName(),
                order.getComputer().getName(),
                order.getFinalPrice(),
                order.getStatus().getDescription(),
                action,
                System.currentTimeMillis()
        ));
    }

    public List<OrderRecord> getRecordsByCustomer(String customerName) {
        return records.stream()
                .filter(r -> r.customerName().equals(customerName))
                .collect(Collectors.toList());
    }

    public List<OrderRecord> getRecordsByAction(String action) {
        return records.stream()
                .filter(r -> r.action().equals(action))
                .collect(Collectors.toList());
    }

    public void displayHistory() {
        System.out.println("\n   ═══════════════════════════════════════");
        System.out.println("   📋 ORDER HISTORY");
        System.out.println("   ═══════════════════════════════════════");

        if (records.isEmpty()) {
            System.out.println("   No orders recorded.");
            return;
        }

        for (OrderRecord record : records) {
            System.out.printf("   [%s] %s - %s | %s | $%.2f | %s\n",
                    record.action(),
                    record.orderId(),
                    record.customerName(),
                    record.computerName(),
                    record.price(),
                    record.status());
        }
    }

    public int getTotalRecords() {
        return records.size();
    }

    // Record class to store order history entries
    public record OrderRecord(
            String orderId,
            String customerName,
            String computerName,
            double price,
            String status,
            String action,
            long timestamp
    ) {}
}