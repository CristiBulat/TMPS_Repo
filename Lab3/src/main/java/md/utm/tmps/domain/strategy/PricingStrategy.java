package md.utm.tmps.domain.strategy;

import md.utm.tmps.domain.models.Order;

// Strategy Pattern
// Strategy interface - defines the pricing algorithm contract
public interface PricingStrategy {
    double calculatePrice(Order order);
    String getStrategyName();
    String getDiscountDescription();
}