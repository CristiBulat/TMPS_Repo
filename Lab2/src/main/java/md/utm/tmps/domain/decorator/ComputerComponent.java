package md.utm.tmps.domain.decorator;

// Decorator Pattern
// Base component interface
public interface ComputerComponent {
    String getDescription();
    double getCost();
    void displaySpecs();
}