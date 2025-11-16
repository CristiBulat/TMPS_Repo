package md.utm.tmps.client;

import md.utm.tmps.domain.adapter.*;
import md.utm.tmps.domain.decorator.*;
import md.utm.tmps.domain.facade.ComputerOrderFacade;
import md.utm.tmps.domain.models.Computer;
import md.utm.tmps.domain.models.ComputerFactory;

// Client class that demonstrates all structural patterns
public class ComputerShop {
    private final ComputerOrderFacade orderFacade;
    private final String shopName;
    private final String location;

    public ComputerShop() {
        this.orderFacade = new ComputerOrderFacade();
        this.shopName = "TechBuild Solutions";
        this.location = "Moldova, Chisinau";
    }

    // ========== ADAPTER PATTERN DEMONSTRATION ==========
    public void demonstrateAdapterPattern() {
        System.out.println("\n💡 Scenario: Integrating legacy payment system");
        System.out.println("Problem: Old system has incompatible interface");
        System.out.println("Solution: Use Adapter to make it compatible\n");

        // Legacy payment system
        LegacyPaymentSystem legacySystem = new LegacyPaymentSystem();
        PaymentProcessor adaptedPayment = new PaymentAdapter(legacySystem);

        // Modern payment system
        PaymentProcessor modernPayment = new ModernPaymentProcessor();

        // Both can be used interchangeably
        System.out.println("--- Using Adapted Legacy System ---");
        adaptedPayment.processPayment(1500.00, "John Doe");
        System.out.println("Transaction ID: " + adaptedPayment.getTransactionId());

        System.out.println("\n--- Using Modern System ---");
        modernPayment.processPayment(1500.00, "Jane Smith");
        System.out.println("Transaction ID: " + modernPayment.getTransactionId());

        System.out.println("\n✓ Both systems work through the same interface!");
    }

    // ========== DECORATOR PATTERN DEMONSTRATION ==========
    public void demonstrateDecoratorPattern() {
        System.out.println("\n💡 Scenario: Customizing computer with enhancements");
        System.out.println("Problem: Need flexible way to add features without modifying base class");
        System.out.println("Solution: Use Decorator to add features dynamically\n");

        // Create base computer
        Computer computer = ComputerFactory.createGamingComputer();

        // Start with base component
        ComputerComponent component = new BaseComputer(computer);

        System.out.println("--- Base Configuration ---");
        component.displaySpecs();
        System.out.printf("\nTotal Cost: $%.2f\n", component.getCost());

        // Add RGB lighting
        component = new RGBLightingDecorator(component);
        System.out.println("\n" + "─".repeat(60));
        System.out.println("--- After Adding RGB Lighting ---");
        component.displaySpecs();
        System.out.printf("\nTotal Cost: $%.2f\n", component.getCost());

        // Add water cooling
        component = new WaterCoolingDecorator(component);
        System.out.println("\n" + "─".repeat(60));
        System.out.println("--- After Adding Water Cooling ---");
        component.displaySpecs();
        System.out.printf("\nTotal Cost: $%.2f\n", component.getCost());

        // Add extended warranty
        component = new ExtendedWarrantyDecorator(component, 3);
        System.out.println("\n" + "─".repeat(60));
        System.out.println("--- Final Configuration with 3-Year Warranty ---");
        component.displaySpecs();
        System.out.printf("\n🏷️  FINAL TOTAL: $%.2f\n", component.getCost());

        System.out.println("\n✓ Features added dynamically without modifying the base Computer class!");
    }

    // ========== FACADE PATTERN DEMONSTRATION ==========
    public void demonstrateFacadePattern() {
        System.out.println("\n💡 Scenario: Customer wants to order a complete system");
        System.out.println("Problem: Complex process involving multiple subsystems");
        System.out.println("Solution: Use Facade to provide simple interface\n");

        System.out.println("Without Facade, customer would need to:");
        System.out.println("  1. Create computer configuration");
        System.out.println("  2. Apply decorators");
        System.out.println("  3. Check inventory");
        System.out.println("  4. Process payment");
        System.out.println("  5. Reserve inventory");
        System.out.println("  6. Schedule shipping");
        System.out.println("  7. Send notifications");
        System.out.println("\nWith Facade, it's just ONE method call:");

        // Using modern payment for this order
        PaymentProcessor payment = new ModernPaymentProcessor();

        String result = orderFacade.placeCompleteOrder(
                "Alex Johnson",
                "alex.johnson@email.com",
                "123 Tech Street, Chisinau, Moldova",
                "gaming",
                true,  // Add RGB
                true,  // Add water cooling
                2,     // 2-year warranty
                payment
        );

        System.out.println("\n" + "=".repeat(60));
        System.out.println(result);
        System.out.println("=".repeat(60));

        System.out.println("\n✓ Complex order completed with a single facade method!");
    }

    public void displayShopInfo() {
        System.out.println("Company: " + shopName);
        System.out.println("Location: " + location);
        System.out.println("Total Orders Processed: " + orderFacade.getTotalOrdersProcessed());
    }
}