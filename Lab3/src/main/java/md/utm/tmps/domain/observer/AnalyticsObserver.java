package md.utm.tmps.domain.observer;

import md.utm.tmps.domain.models.Order;
import java.util.HashMap;
import java.util.Map;

// Observer Pattern
// Concrete Observer - tracks analytics and statistics
public class AnalyticsObserver implements OrderObserver {
    private final String observerName = "Analytics & Reporting System";
    private int totalOrdersCreated;
    private int totalOrdersCompleted;
    private int totalOrdersCancelled;
    private double totalRevenue;
    private final Map<String, Integer> productPopularity;

    public AnalyticsObserver() {
        this.totalOrdersCreated = 0;
        this.totalOrdersCompleted = 0;
        this.totalOrdersCancelled = 0;
        this.totalRevenue = 0.0;
        this.productPopularity = new HashMap<>();
    }

    @Override
    public void update(Order order, String eventType) {
        switch (eventType) {
            case "ORDER_CREATED" -> {
                totalOrdersCreated++;
                String productName = order.getComputer().getName();
                productPopularity.merge(productName, 1, Integer::sum);
                System.out.printf("   📊 Analytics: New order recorded. Total orders: %d\n", totalOrdersCreated);
            }
            case "ORDER_DELIVERED" -> {
                totalOrdersCompleted++;
                totalRevenue += order.getFinalPrice();
                System.out.printf("   📊 Analytics: Order completed! Total revenue: $%.2f\n", totalRevenue);
            }
            case "ORDER_CANCELLED" -> {
                totalOrdersCancelled++;
                System.out.printf("   📊 Analytics: Order cancelled. Cancellation rate: %.1f%%\n",
                        (double) totalOrdersCancelled / totalOrdersCreated * 100);
            }
            default -> {
                System.out.printf("   📊 Analytics: Order %s - Status: %s\n",
                        order.getOrderId(), order.getStatus().getDescription());
            }
        }
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    public void displayStatistics() {
        System.out.println("\n   ═══════════════════════════════════════");
        System.out.println("   📈 ANALYTICS DASHBOARD");
        System.out.println("   ═══════════════════════════════════════");
        System.out.printf("   Total Orders Created: %d\n", totalOrdersCreated);
        System.out.printf("   Orders Completed: %d\n", totalOrdersCompleted);
        System.out.printf("   Orders Cancelled: %d\n", totalOrdersCancelled);
        System.out.printf("   Total Revenue: $%.2f\n", totalRevenue);
        System.out.println("\n   Product Popularity:");
        productPopularity.forEach((product, count) ->
                System.out.printf("   - %s: %d orders\n", product, count));
        System.out.println("   ═══════════════════════════════════════");
    }

    public int getTotalOrdersCreated() { return totalOrdersCreated; }
    public int getTotalOrdersCompleted() { return totalOrdersCompleted; }
    public double getTotalRevenue() { return totalRevenue; }
}