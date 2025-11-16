package md.utm.tmps.domain.decorator;

// Decorator Pattern
// Concrete decorator - adds extended warranty
public class ExtendedWarrantyDecorator extends ComputerDecorator {
    private static final double WARRANTY_COST = 200.0;
    private final int years;

    public ExtendedWarrantyDecorator(ComputerComponent component, int years) {
        super(component);
        this.years = years;
    }

    @Override
    public String getDescription() {
        return wrappedComponent.getDescription() + String.format(" + %d-Year Warranty", years);
    }

    @Override
    public double getCost() {
        return wrappedComponent.getCost() + (WARRANTY_COST * years);
    }

    @Override
    public void displaySpecs() {
        wrappedComponent.displaySpecs();
        System.out.printf("🛡️  Enhancement: %d-Year Extended Warranty\n", years);
        System.out.println("   - Parts and labor coverage");
        System.out.println("   - On-site support");
        System.out.println("   - Advanced replacement");
        System.out.printf("   - Additional cost: $%.2f\n", WARRANTY_COST * years);
    }
}