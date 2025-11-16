package md.utm.tmps.domain.decorator;

// Decorator Pattern
// Concrete decorator - adds RGB lighting
public class RGBLightingDecorator extends ComputerDecorator {
    private static final double RGB_COST = 150.0;

    public RGBLightingDecorator(ComputerComponent component) {
        super(component);
    }

    @Override
    public String getDescription() {
        return wrappedComponent.getDescription() + " + RGB Lighting";
    }

    @Override
    public double getCost() {
        return wrappedComponent.getCost() + RGB_COST;
    }

    @Override
    public void displaySpecs() {
        wrappedComponent.displaySpecs();
        System.out.println("🌈 Enhancement: RGB Lighting System");
        System.out.println("   - Addressable RGB strips");
        System.out.println("   - Customizable colors and effects");
        System.out.printf("   - Additional cost: $%.2f\n", RGB_COST);
    }
}