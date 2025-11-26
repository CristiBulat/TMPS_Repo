package md.utm.tmps.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

// Order model that tracks computer orders
public class Order {
    private final String orderId;
    private final String customerName;
    private final String customerEmail;
    private final Computer computer;
    private OrderStatus status;
    private double finalPrice;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String discountApplied;

    public enum OrderStatus {
        CREATED("Order Created"),
        CONFIRMED("Order Confirmed"),
        PROCESSING("Processing"),
        SHIPPED("Shipped"),
        DELIVERED("Delivered"),
        CANCELLED("Cancelled");

        private final String description;

        OrderStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Order(String customerName, String customerEmail, Computer computer) {
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.computer = computer;
        this.status = OrderStatus.CREATED;
        this.finalPrice = computer.getBasePrice();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.discountApplied = "None";
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Computer getComputer() { return computer; }
    public OrderStatus getStatus() { return status; }
    public double getFinalPrice() { return finalPrice; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public String getDiscountApplied() { return discountApplied; }

    // Setters
    public void setStatus(OrderStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
        this.updatedAt = LocalDateTime.now();
    }

    public void setDiscountApplied(String discountApplied) {
        this.discountApplied = discountApplied;
    }

    @Override
    public String toString() {
        return String.format("""
            ══════════════════════════════════════
            Order ID: %s
            Customer: %s (%s)
            Status: %s
            Computer: %s
            Base Price: $%.2f
            Discount: %s
            Final Price: $%.2f
            Created: %s
            ══════════════════════════════════════
            """, orderId, customerName, customerEmail,
                status.getDescription(), computer.getName(),
                computer.getBasePrice(), discountApplied, finalPrice,
                createdAt.toString());
    }
}