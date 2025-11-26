package md.utm.tmps.domain.strategy;

import md.utm.tmps.domain.models.Order;

// Strategy Pattern
// Concrete Strategy - 15% student discount
public class StudentDiscountStrategy implements PricingStrategy {
    private static final double STUDENT_DISCOUNT = 0.15; // 15% discount

    @Override
    public double calculatePrice(Order order) {
        double basePrice = order.getComputer().getBasePrice();
        double discount = basePrice * STUDENT_DISCOUNT;
        double finalPrice = basePrice - discount;

        System.out.printf("   🎓 Student Discount Applied!\n");
        System.out.printf("   Base Price: $%.2f\n", basePrice);
        System.out.printf("   Discount (15%%): -$%.2f\n", discount);
        System.out.printf("   Final Price: $%.2f\n", finalPrice);

        return finalPrice;
    }

    @Override
    public String getStrategyName() {
        return "Student Discount";
    }

    @Override
    public String getDiscountDescription() {
        return "15% Student Discount";
    }
}