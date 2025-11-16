package md.utm.tmps.domain.facade;

import md.utm.tmps.domain.adapter.PaymentProcessor;
import md.utm.tmps.domain.decorator.*;
import md.utm.tmps.domain.models.Computer;
import md.utm.tmps.domain.models.ComputerFactory;
import md.utm.tmps.utilities.*;

// Facade Pattern
// Simplifies the complex process of ordering a computer
public class ComputerOrderFacade {
    private final InventoryManager inventoryManager;
    private final ShippingService shippingService;
    private final NotificationService notificationService;
    private int totalOrdersProcessed;

    public ComputerOrderFacade() {
        this.inventoryManager = new InventoryManager();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
        this.totalOrdersProcessed = 0;
    }

    // Simplified method that hides complex subsystem interactions
    public String placeCompleteOrder(
            String customerName,
            String email,
            String address,
            String systemType,
            boolean addRGB,
            boolean addWaterCooling,
            int warrantyYears,
            PaymentProcessor paymentProcessor
    ) {
        System.out.println("\n🏪 FACADE: Processing complete order for " + customerName);
        System.out.println("─".repeat(60));

        try {
            // Step 1: Create the computer
            Computer computer = createComputerByType(systemType);

            // Step 2: Apply decorators
            ComputerComponent component = new BaseComputer(computer);
            if (addRGB) {
                component = new RGBLightingDecorator(component);
            }
            if (addWaterCooling) {
                component = new WaterCoolingDecorator(component);
            }
            if (warrantyYears > 0) {
                component = new ExtendedWarrantyDecorator(component, warrantyYears);
            }

            double totalCost = component.getCost();
            System.out.println("\n📋 Order Summary:");
            System.out.println(component.getDescription());
            System.out.printf("Total Cost: $%.2f\n", totalCost);

            // Step 3: Check inventory
            System.out.println("\n📦 Checking inventory...");
            if (!inventoryManager.checkAvailability(systemType)) {
                return "ORDER FAILED: Item out of stock";
            }

            // Step 4: Process payment
            System.out.println("\n💳 Processing payment...");
            if (!paymentProcessor.processPayment(totalCost, customerName)) {
                return "ORDER FAILED: Payment declined";
            }
            String transactionId = paymentProcessor.getTransactionId();

            // Step 5: Reserve inventory
            inventoryManager.reserveItem(systemType);

            // Step 6: Schedule shipping
            System.out.println("\n🚚 Scheduling shipping...");
            String trackingNumber = shippingService.scheduleDelivery(
                    customerName, address, computer.getName());

            // Step 7: Send notifications
            System.out.println("\n📧 Sending notifications...");
            notificationService.sendOrderConfirmation(email, transactionId, trackingNumber);

            // Step 8: Update statistics
            totalOrdersProcessed++;

            System.out.println("\n✅ Order completed successfully!");
            return String.format("ORDER CONFIRMED - Transaction: %s, Tracking: %s",
                    transactionId, trackingNumber);

        } catch (Exception e) {
            System.out.println("\n❌ Order failed: " + e.getMessage());
            return "ORDER FAILED: " + e.getMessage();
        }
    }

    private Computer createComputerByType(String type) {
        return switch (type.toLowerCase()) {
            case "gaming" -> ComputerFactory.createGamingComputer();
            case "workstation" -> ComputerFactory.createWorkstation();
            case "budget" -> ComputerFactory.createBudgetPC();
            case "office" -> ComputerFactory.createOfficePC();
            default -> throw new IllegalArgumentException("Unknown system type: " + type);
        };
    }

    public int getTotalOrdersProcessed() {
        return totalOrdersProcessed;
    }

    public void displayOrderStatus(String orderId) {
        System.out.println("\n📊 Order Status for: " + orderId);
        System.out.println("Status: Processing");
        System.out.println("Estimated delivery: 3-5 business days");
    }
}