package md.utm.tmps.domain.decorator;

import md.utm.tmps.domain.models.Computer;

// Decorator Pattern
// Concrete component - basic computer without enhancements
public class BaseComputer implements ComputerComponent {
    private final Computer computer;

    public BaseComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String getDescription() {
        return computer.getName();
    }

    @Override
    public double getCost() {
        return computer.getBasePrice();
    }

    @Override
    public void displaySpecs() {
        System.out.println("=== Base Computer ===");
        System.out.println(computer);
    }

    public Computer getComputer() {
        return computer;
    }
}