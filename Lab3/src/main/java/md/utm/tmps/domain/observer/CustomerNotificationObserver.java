package md.utm.tmps.domain.observer;

import md.utm.tmps.domain.models.Order;

// Observer Pattern
// Concrete Observer - handles customer notifications
public class CustomerNotificationObserver implements OrderObserver {
    private final String observerName = "Customer Notification Service";

    @Override
    public void update(Order order, String eventType) {
        String message = switch (eventType) {
            case "ORDER_CREATED" -> String.format(
                    "📧 Email to %s: Your order %s has been created! Total: $%.2f",
                    order.getCustomerEmail(), order.getOrderId(), order.getFinalPrice());
            case "ORDER_CONFIRMED" -> String.format(
                    "📧 Email to %s: Order %s confirmed! We're preparing your %s.",
                    order.getCustomerEmail(), order.getOrderId(), order.getComputer().getName());
            case "ORDER_PROCESSING" -> String.format(
                    "📧 Email to %s: Order %s is being assembled!",
                    order.getCustomerEmail(), order.getOrderId());
            case "ORDER_SHIPPED" -> String.format(
                    "📧 Email to %s: Order %s has been shipped! Track your delivery.",
                    order.getCustomerEmail(), order.getOrderId());
            case "ORDER_DELIVERED" -> String.format(
                    "📧 Email to %s: Order %s delivered! Enjoy your new %s!",
                    order.getCustomerEmail(), order.getOrderId(), order.getComputer().getName());
            case "ORDER_CANCELLED" -> String.format(
                    "📧 Email to %s: Order %s has been cancelled. Refund initiated.",
                    order.getCustomerEmail(), order.getOrderId());
            default -> String.format(
                    "📧 Email to %s: Order %s status update.",
                    order.getCustomerEmail(), order.getOrderId());
        };
        System.out.println("   " + message);
    }

    @Override
    public String getObserverName() {
        return observerName;
    }
}