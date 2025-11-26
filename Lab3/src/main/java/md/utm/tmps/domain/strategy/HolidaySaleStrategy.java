package md.utm.tmps.domain.strategy;

import md.utm.tmps.domain.models.Order;

// Strategy Pattern
// Concrete Strategy - holiday/seasonal sale pricing
public class HolidaySaleStrategy implements PricingStrategy {
    private final String holidayName;
    private final double discountRate;

    public HolidaySaleStrategy(String holidayName, double discountRate) {
        this.holidayName = holidayName;
        this.discountRate = discountRate;
    }

    // Predefined holiday sales
    public static HolidaySaleStrategy blackFriday() {
        return new HolidaySaleStrategy("Black Friday", 0.25);
    }

    public static HolidaySaleStrategy cyberMonday() {
        return new HolidaySaleStrategy("Cyber Monday", 0.20);
    }

    public static HolidaySaleStrategy christmasSale() {
        return new HolidaySaleStrategy("Christmas Sale", 0.15);
    }

    public static HolidaySaleStrategy newYearSale() {
        return new HolidaySaleStrategy("New Year Sale", 0.18);
    }

    @Override
    public double calculatePrice(Order order) {
        double basePrice = order.getComputer().getBasePrice();
        double discount = basePrice * discountRate;
        double finalPrice = basePrice - discount;

        System.out.printf("   🎉 %s SALE!\n", holidayName.toUpperCase());
        System.out.printf("   Base Price: $%.2f\n", basePrice);
        System.out.printf("   Holiday Discount (%.0f%%): -$%.2f\n", discountRate * 100, discount);
        System.out.printf("   🔥 Sale Price: $%.2f\n", finalPrice);

        return finalPrice;
    }

    @Override
    public String getStrategyName() {
        return holidayName + " Sale";
    }

    @Override
    public String getDiscountDescription() {
        return String.format("%.0f%% %s Discount", discountRate * 100, holidayName);
    }
}