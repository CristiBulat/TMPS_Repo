package md.utm.tmps.utilities;

import java.util.HashMap;
import java.util.Map;

// Facade Pattern - Subsystem
// Manages inventory availability
public class InventoryManager {
    private Map<String, Integer> inventory;

    public InventoryManager() {
        this.inventory = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.put("gaming", 10);
        inventory.put("workstation", 5);
        inventory.put("budget", 20);
        inventory.put("office", 30);
    }

    public boolean checkAvailability(String itemType) {
        int stock = inventory.getOrDefault(itemType.toLowerCase(), 0);
        System.out.printf("   Stock available: %d units\n", stock);
        return stock > 0;
    }

    public void reserveItem(String itemType) {
        String key = itemType.toLowerCase();
        int current = inventory.getOrDefault(key, 0);
        if (current > 0) {
            inventory.put(key, current - 1);
            System.out.printf("   Reserved 1 unit. Remaining stock: %d\n", current - 1);
        }
    }

    public void restockItem(String itemType, int quantity) {
        String key = itemType.toLowerCase();
        int current = inventory.getOrDefault(key, 0);
        inventory.put(key, current + quantity);
        System.out.printf("   Restocked %d units of %s\n", quantity, itemType);
    }
}