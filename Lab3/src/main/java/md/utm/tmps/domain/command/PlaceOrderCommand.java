package md.utm.tmps.domain.command;

import md.utm.tmps.domain.models.Computer;
import md.utm.tmps.domain.models.Order;
import md.utm.tmps.domain.observer.OrderManager;
import md.utm.tmps.domain.strategy.PricingContext;

// Command Pattern
// Concrete Command - places a new order
public class PlaceOrderCommand implements OrderCommand {
    private final OrderManager orderManager;
    private final String customerName;
    private final String customerEmail;
    private final Computer computer;
    private final PricingContext pricingContext;
    private Order createdOrder;

    public PlaceOrderCommand(OrderManager orderManager, String customerName,
                             String customerEmail, Computer computer,
                             PricingContext pricingContext) {
        this.orderManager = orderManager;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.computer = computer;
        this.pricingContext = pricingContext;
    }

    @Override
    public void execute() {
        System.out.println("\n   ▶ Executing: Place Order Command");

        // Create the order
        createdOrder = orderManager.createOrder(customerName, customerEmail, computer);

        // Apply pricing strategy
        pricingContext.executeStrategy(createdOrder);

        // Confirm the order
        orderManager.confirmOrder(createdOrder.getOrderId());

        System.out.printf("   ✅ Order %s placed successfully!\n", createdOrder.getOrderId());
    }

    @Override
    public void undo() {
        if (createdOrder != null) {
            System.out.println("\n   ◀ Undoing: Place Order Command");
            orderManager.cancelOrder(createdOrder.getOrderId());
            System.out.printf("   ↩️  Order %s has been cancelled (undo)\n", createdOrder.getOrderId());
        }
    }

    @Override
    public String getCommandName() {
        return "Place Order";
    }

    @Override
    public boolean isReversible() {
        return true;
    }

    public Order getCreatedOrder() {
        return createdOrder;
    }
}