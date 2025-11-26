package md.utm.tmps.domain.command;

import md.utm.tmps.domain.models.Order;
import md.utm.tmps.domain.observer.OrderManager;

// Command Pattern
// Concrete Command - updates order status
public class UpdateOrderStatusCommand implements OrderCommand {
    private final OrderManager orderManager;
    private final String orderId;
    private final Order.OrderStatus newStatus;
    private Order.OrderStatus previousStatus;

    public UpdateOrderStatusCommand(OrderManager orderManager, String orderId,
                                    Order.OrderStatus newStatus) {
        this.orderManager = orderManager;
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    @Override
    public void execute() {
        System.out.println("\n   ▶ Executing: Update Order Status Command");

        Order order = orderManager.getOrder(orderId);
        if (order != null) {
            previousStatus = order.getStatus();

            // Execute the appropriate status change
            switch (newStatus) {
                case PROCESSING -> orderManager.processOrder(orderId);
                case SHIPPED -> orderManager.shipOrder(orderId);
                case DELIVERED -> orderManager.deliverOrder(orderId);
                case CANCELLED -> orderManager.cancelOrder(orderId);
                default -> {
                    System.out.printf("   ⚠️  Status change to %s not supported via command\n",
                            newStatus.getDescription());
                    return;
                }
            }

            System.out.printf("   ✅ Order %s status updated: %s → %s\n",
                    orderId, previousStatus.getDescription(), newStatus.getDescription());
        } else {
            System.out.printf("   ❌ Order %s not found!\n", orderId);
        }
    }

    @Override
    public void undo() {
        System.out.println("\n   ◀ Undoing: Update Order Status Command");

        Order order = orderManager.getOrder(orderId);
        if (order != null && previousStatus != null) {
            // For some status changes, undo might not make business sense
            if (newStatus == Order.OrderStatus.DELIVERED) {
                System.out.println("   ⚠️  Cannot undo delivery - order already delivered");
                return;
            }

            order.setStatus(previousStatus);
            System.out.printf("   ↩️  Order %s restored to: %s\n",
                    orderId, previousStatus.getDescription());
        }
    }

    @Override
    public String getCommandName() {
        return "Update Order Status to " + newStatus.getDescription();
    }

    @Override
    public boolean isReversible() {
        // Delivery cannot be reversed
        return newStatus != Order.OrderStatus.DELIVERED;
    }
}