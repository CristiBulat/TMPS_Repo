package md.utm.tmps.domain.strategy;

import md.utm.tmps.domain.models.Order;

// Strategy Pattern
// Concrete Strategy - bulk order discount (tiered based on quantity)
public class BulkOrderStrategy implements PricingStrategy {
    private final int quantity;

    public BulkOrderStrategy(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double calculatePrice(Order order) {
        double basePrice = order.getComputer().getBasePrice();
        double discountRate = calculateDiscountRate();
        double totalBeforeDiscount = basePrice * quantity;
        double discount = totalBeforeDiscount * discountRate;
        double finalPrice = totalBeforeDiscount - discount;

        System.out.printf("   📦 Bulk Order Pricing (Quantity: %d)\n", quantity);
        System.out.printf("   Unit Price: $%.2f\n", basePrice);
        System.out.printf("   Total Before Discount: $%.2f\n", totalBeforeDiscount);
        System.out.printf("   Bulk Discount (%.0f%%): -$%.2f\n", discountRate * 100, discount);
        System.out.printf("   Final Total: $%.2f\n", finalPrice);
        System.out.printf("   Price Per Unit: $%.2f\n", finalPrice / quantity);

        return finalPrice;
    }

    private double calculateDiscountRate() {
        if (quantity >= 10) {
            return 0.20; // 20% for 10+ units
        } else if (quantity >= 5) {
            return 0.10; // 10% for 5-9 units
        } else if (quantity >= 3) {
            return 0.05; // 5% for 3-4 units
        }
        return 0.0; // No discount for less than 3
    }

    @Override
    public String getStrategyName() {
        return "Bulk Order Discount";
    }

    @Override
    public String getDiscountDescription() {
        double rate = calculateDiscountRate();
        return String.format("%.0f%% Bulk Discount for %d units", rate * 100, quantity);
    }
}