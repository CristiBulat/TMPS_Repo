package md.utm.tmps.domain.strategy;

import md.utm.tmps.domain.models.Order;

// Strategy Pattern
// Concrete Strategy - regular pricing with no discount
public class RegularPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Order order) {
        double basePrice = order.getComputer().getBasePrice();
        System.out.printf("   💰 Regular Pricing: $%.2f (no discount)\n", basePrice);
        return basePrice;
    }

    @Override
    public String getStrategyName() {
        return "Regular Pricing";
    }

    @Override
    public String getDiscountDescription() {
        return "No discount applied";
    }
}