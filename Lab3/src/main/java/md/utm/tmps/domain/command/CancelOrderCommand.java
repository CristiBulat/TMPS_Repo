package md.utm.tmps.domain.command;

import md.utm.tmps.domain.models.Order;
import md.utm.tmps.domain.observer.OrderManager;

// Command Pattern
// Concrete Command - cancels an existing order
public class CancelOrderCommand implements OrderCommand {
    private final OrderManager orderManager;
    private final String orderId;
    private Order.OrderStatus previousStatus;

    public CancelOrderCommand(OrderManager orderManager, String orderId) {
        this.orderManager = orderManager;
        this.orderId = orderId;
    }

    @Override
    public void execute() {
        System.out.println("\n   ▶ Executing: Cancel Order Command");

        Order order = orderManager.getOrder(orderId);
        if (order != null) {
            previousStatus = order.getStatus();

            if (previousStatus == Order.OrderStatus.SHIPPED ||
                    previousStatus == Order.OrderStatus.DELIVERED) {
                System.out.printf("   ❌ Cannot cancel order %s - already %s\n",
                        orderId, previousStatus.getDescription());
                return;
            }

            orderManager.cancelOrder(orderId);
            System.out.printf("   ✅ Order %s cancelled successfully!\n", orderId);
        } else {
            System.out.printf("   ❌ Order %s not found!\n", orderId);
        }
    }

    @Override
    public void undo() {
        System.out.println("\n   ◀ Undoing: Cancel Order Command");

        Order order = orderManager.getOrder(orderId);
        if (order != null && previousStatus != null) {
            order.setStatus(previousStatus);
            System.out.printf("   ↩️  Order %s restored to: %s\n",
                    orderId, previousStatus.getDescription());
        }
    }

    @Override
    public String getCommandName() {
        return "Cancel Order";
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}