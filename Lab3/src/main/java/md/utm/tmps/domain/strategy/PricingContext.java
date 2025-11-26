package md.utm.tmps.domain.strategy;

import md.utm.tmps.domain.models.Order;

// Strategy Pattern
// Context class - uses a pricing strategy to calculate the final price
public class PricingContext {
    private PricingStrategy strategy;

    public PricingContext() {
        // Default to regular pricing
        this.strategy = new RegularPricingStrategy();
    }

    public PricingContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
        System.out.printf("   ⚙️  Pricing strategy changed to: %s\n", strategy.getStrategyName());
    }

    public PricingStrategy getStrategy() {
        return strategy;
    }

    public double executeStrategy(Order order) {
        System.out.println("\n   ─────────────────────────────────────");
        System.out.printf("   Applying %s Strategy\n", strategy.getStrategyName());
        System.out.println("   ─────────────────────────────────────");

        double finalPrice = strategy.calculatePrice(order);
        order.setFinalPrice(finalPrice);
        order.setDiscountApplied(strategy.getDiscountDescription());

        return finalPrice;
    }
}