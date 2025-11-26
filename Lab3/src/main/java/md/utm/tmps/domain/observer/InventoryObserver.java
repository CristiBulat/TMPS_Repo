package md.utm.tmps.domain.observer;

import md.utm.tmps.domain.models.Order;
import java.util.HashMap;
import java.util.Map;

// Observer Pattern
// Concrete Observer - handles inventory updates based on order events
public class InventoryObserver implements OrderObserver {
    private final String observerName = "Inventory Management System";
    private final Map<String, Integer> inventory;

    public InventoryObserver() {
        this.inventory = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.put("Gaming Beast", 10);
        inventory.put("Professional Workstation", 5);
        inventory.put("Budget Gaming PC", 15);
        inventory.put("Office PC", 20);
    }

    @Override
    public void update(Order order, String eventType) {
        String computerName = order.getComputer().getName();

        switch (eventType) {
            case "ORDER_CONFIRMED" -> {
                int current = inventory.getOrDefault(computerName, 0);
                if (current > 0) {
                    inventory.put(computerName, current - 1);
                    System.out.printf("   📦 Inventory: Reserved 1x %s. Stock remaining: %d\n",
                            computerName, current - 1);
                } else {
                    System.out.printf("   ⚠️  Inventory: WARNING - %s out of stock!\n", computerName);
                }
            }
            case "ORDER_CANCELLED" -> {
                int current = inventory.getOrDefault(computerName, 0);
                inventory.put(computerName, current + 1);
                System.out.printf("   📦 Inventory: Restored 1x %s. Stock now: %d\n",
                        computerName, current + 1);
            }
            case "ORDER_SHIPPED" -> {
                System.out.printf("   📦 Inventory: %s shipped for order %s\n",
                        computerName, order.getOrderId());
            }
            default -> {
                // No inventory action needed for other events
            }
        }
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    public int getStock(String computerName) {
        return inventory.getOrDefault(computerName, 0);
    }

    public void displayInventory() {
        System.out.println("\n   Current Inventory:");
        inventory.forEach((name, count) ->
                System.out.printf("   - %s: %d units\n", name, count));
    }
}