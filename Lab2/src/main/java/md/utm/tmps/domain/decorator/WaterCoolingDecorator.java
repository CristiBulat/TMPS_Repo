package md.utm.tmps.domain.decorator;

// Decorator Pattern
// Concrete decorator - adds water cooling system
public class WaterCoolingDecorator extends ComputerDecorator {
    private static final double COOLING_COST = 300.0;

    public WaterCoolingDecorator(ComputerComponent component) {
        super(component);
    }

    @Override
    public String getDescription() {
        return wrappedComponent.getDescription() + " + Water Cooling";
    }

    @Override
    public double getCost() {
        return wrappedComponent.getCost() + COOLING_COST;
    }

    @Override
    public void displaySpecs() {
        wrappedComponent.displaySpecs();
        System.out.println("❄️  Enhancement: Custom Water Cooling Loop");
        System.out.println("   - CPU and GPU blocks");
        System.out.println("   - 360mm radiator");
        System.out.println("   - Premium coolant");
        System.out.printf("   - Additional cost: $%.2f\n", COOLING_COST);
    }
}