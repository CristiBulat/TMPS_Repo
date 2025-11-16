package md.utm.tmps.domain.decorator;

// Decorator Pattern
// Abstract decorator class
public abstract class ComputerDecorator implements ComputerComponent {
    protected ComputerComponent wrappedComponent;

    public ComputerDecorator(ComputerComponent component) {
        this.wrappedComponent = component;
    }

    @Override
    public String getDescription() {
        return wrappedComponent.getDescription();
    }

    @Override
    public double getCost() {
        return wrappedComponent.getCost();
    }

    @Override
    public void displaySpecs() {
        wrappedComponent.displaySpecs();
    }
}