package md.utm.tmps.client;

import md.utm.tmps.domain.command.*;
import md.utm.tmps.domain.models.*;
import md.utm.tmps.domain.observer.*;
import md.utm.tmps.domain.strategy.*;

// Client class that demonstrates all behavioral patterns
public class ComputerShop {
    private final OrderManager orderManager;
    private final OrderCommandInvoker commandInvoker;
    private final PricingContext pricingContext;
    private final CustomerNotificationObserver customerObserver;
    private final InventoryObserver inventoryObserver;
    private final AnalyticsObserver analyticsObserver;
    private final String shopName;

    public ComputerShop() {
        this.shopName = "TechBuild Solutions";
        this.orderManager = new OrderManager();
        this.commandInvoker = new OrderCommandInvoker();
        this.pricingContext = new PricingContext();

        // Create observers
        this.customerObserver = new CustomerNotificationObserver();
        this.inventoryObserver = new InventoryObserver();
        this.analyticsObserver = new AnalyticsObserver();

        // Register observers with order manager
        System.out.println("\n📝 Registering Observers:");
        orderManager.registerObserver(customerObserver);
        orderManager.registerObserver(inventoryObserver);
        orderManager.registerObserver(analyticsObserver);
    }

    // ========== OBSERVER PATTERN DEMONSTRATION ==========
    public void demonstrateObserverPattern() {
        System.out.println("\n💡 Scenario: Multiple systems need to react to order changes");
        System.out.println("Problem: Order updates must notify customers, inventory, and analytics");
        System.out.println("Solution: Use Observer pattern for event-driven notifications\n");

        // Create an order - all observers will be notified
        Computer gamingPC = ComputerFactory.createGamingComputer();
        Order order = orderManager.createOrder("John Doe", "john@email.com", gamingPC);

        System.out.println("\n--- Progressing Order Through Lifecycle ---");

        // Confirm order
        orderManager.confirmOrder(order.getOrderId());

        // Process order
        orderManager.processOrder(order.getOrderId());

        // Ship order
        orderManager.shipOrder(order.getOrderId());

        // Deliver order
        orderManager.deliverOrder(order.getOrderId());

        System.out.println("\n✓ All observers automatically notified at each stage!");
        System.out.printf("✓ %d observers are tracking order changes\n", orderManager.getObserverCount());
    }

    // ========== STRATEGY PATTERN DEMONSTRATION ==========
    public void demonstrateStrategyPattern() {
        System.out.println("\n💡 Scenario: Different pricing strategies for different customers");
        System.out.println("Problem: Need flexible pricing without modifying order logic");
        System.out.println("Solution: Use Strategy pattern to swap pricing algorithms\n");

        Computer workstation = ComputerFactory.createWorkstation();
        Order order = new Order("Test Customer", "test@email.com", workstation);

        // Strategy 1: Regular Pricing
        System.out.println("\n--- Strategy 1: Regular Pricing ---");
        pricingContext.setStrategy(new RegularPricingStrategy());
        pricingContext.executeStrategy(order);

        // Strategy 2: Student Discount
        System.out.println("\n--- Strategy 2: Student Discount ---");
        pricingContext.setStrategy(new StudentDiscountStrategy());
        pricingContext.executeStrategy(order);

        // Strategy 3: Bulk Order
        System.out.println("\n--- Strategy 3: Bulk Order (5 units) ---");
        pricingContext.setStrategy(new BulkOrderStrategy(5));
        pricingContext.executeStrategy(order);

        // Strategy 4: Holiday Sale
        System.out.println("\n--- Strategy 4: Black Friday Sale ---");
        pricingContext.setStrategy(HolidaySaleStrategy.blackFriday());
        pricingContext.executeStrategy(order);

        System.out.println("\n✓ Pricing strategy can be changed at runtime!");
        System.out.println("✓ Order logic remains unchanged regardless of pricing algorithm");
    }

    // ========== COMMAND PATTERN DEMONSTRATION ==========
    public void demonstrateCommandPattern() {
        System.out.println("\n💡 Scenario: Need to execute, queue, and undo order operations");
        System.out.println("Problem: Operations should be reversible and trackable");
        System.out.println("Solution: Use Command pattern to encapsulate operations\n");

        Computer budgetPC = ComputerFactory.createBudgetPC();

        // Create and execute PlaceOrderCommand
        System.out.println("--- Executing Place Order Command ---");
        PlaceOrderCommand placeOrder = new PlaceOrderCommand(
                orderManager,
                "Alice Smith",
                "alice@email.com",
                budgetPC,
                new PricingContext(new StudentDiscountStrategy())
        );
        commandInvoker.executeCommand(placeOrder);

        String orderId = placeOrder.getCreatedOrder().getOrderId();

        // Create and execute UpdateOrderStatusCommand
        System.out.println("\n--- Executing Update Status Command (to Processing) ---");
        UpdateOrderStatusCommand updateStatus = new UpdateOrderStatusCommand(
                orderManager,
                orderId,
                Order.OrderStatus.PROCESSING
        );
        commandInvoker.executeCommand(updateStatus);

        // Show history
        commandInvoker.displayHistory();

        // Demonstrate undo
        System.out.println("\n--- Demonstrating Undo Capability ---");
        commandInvoker.undoLastCommand();

        // Show updated history
        commandInvoker.displayHistory();

        System.out.println("\n✓ Commands are executed through invoker");
        System.out.println("✓ Operations can be undone in reverse order");
        System.out.println("✓ Complete execution history is maintained");
    }

    // ========== INTEGRATED DEMONSTRATION ==========
    public void demonstrateIntegratedPatterns() {
        System.out.println("\n💡 Scenario: Complete order workflow using all three patterns");
        System.out.println("─".repeat(60));

        // Setup holiday sale strategy
        pricingContext.setStrategy(HolidaySaleStrategy.cyberMonday());

        // Create order command
        Computer gamingPC = ComputerFactory.createGamingComputer();
        PlaceOrderCommand order1 = new PlaceOrderCommand(
                orderManager,
                "Bob Wilson",
                "bob@email.com",
                gamingPC,
                pricingContext
        );

        // Execute via command pattern (triggers observer pattern)
        commandInvoker.executeCommand(order1);

        // Progress order (triggers observers)
        String orderId = order1.getCreatedOrder().getOrderId();

        UpdateOrderStatusCommand shipCommand = new UpdateOrderStatusCommand(
                orderManager, orderId, Order.OrderStatus.SHIPPED
        );
        commandInvoker.executeCommand(shipCommand);

        UpdateOrderStatusCommand deliverCommand = new UpdateOrderStatusCommand(
                orderManager, orderId, Order.OrderStatus.DELIVERED
        );
        commandInvoker.executeCommand(deliverCommand);

        System.out.println("\n" + "═".repeat(60));
        System.out.println("INTEGRATION SUMMARY:");
        System.out.println("═".repeat(60));
        System.out.println("✓ Command Pattern: Encapsulated all operations");
        System.out.println("✓ Observer Pattern: Notified all systems automatically");
        System.out.println("✓ Strategy Pattern: Applied Cyber Monday pricing");
    }

    public void displayShopInfo() {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("📊 SHOP SUMMARY");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("Company: " + shopName);
        System.out.printf("Active Observers: %d\n", orderManager.getObserverCount());
        System.out.printf("Commands in History: %d\n", commandInvoker.getHistorySize());
        System.out.printf("Current Pricing Strategy: %s\n", pricingContext.getStrategy().getStrategyName());

        // Show analytics
        analyticsObserver.displayStatistics();

        // Show inventory
        inventoryObserver.displayInventory();

        // Show command history
        commandInvoker.displayHistory();
    }

    // Getters for testing
    public OrderManager getOrderManager() { return orderManager; }
    public OrderCommandInvoker getCommandInvoker() { return commandInvoker; }
    public PricingContext getPricingContext() { return pricingContext; }
    public AnalyticsObserver getAnalyticsObserver() { return analyticsObserver; }
}