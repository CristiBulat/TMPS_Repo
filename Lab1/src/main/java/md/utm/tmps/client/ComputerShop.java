package md.utm.tmps.client;

import md.utm.tmps.domain.factory.abstractfactory.*;
import md.utm.tmps.domain.factory.builder.*;
import md.utm.tmps.domain.factory.prototype.*;
import md.utm.tmps.domain.factory.singleton.*;
import md.utm.tmps.domain.models.*;

// Client class that demonstrates all creational patterns
public class ComputerShop {
    private final ConfigurationManager config;
    private final ComputerPrototypeRegistry prototypeRegistry;

    public ComputerShop() {
        this.config = ConfigurationManager.getInstance();
        this.prototypeRegistry = new ComputerPrototypeRegistry();
    }

    // Using Abstract Factory to build a complete system
    public Computer buildSystemWithAbstractFactory(ComputerPartsFactory factory, String name) {
        System.out.println("\n🏭 Using Abstract Factory Pattern");

        Computer computer = new Computer(name);
        computer.setCpu(factory.createCPU());
        computer.setGpu(factory.createGPU());
        computer.setRam(factory.createRAM());
        computer.setStorage(factory.createStorage());
        computer.setCaseType(config.getConfiguration("default_case"));

        config.incrementComputersBuilt();
        return computer;
    }

    // Using Builder Pattern
    public Computer buildCustomComputer(ComputerBuilder builder, CPU cpu, GPU gpu, RAM ram, Storage storage) {
        System.out.println("\n🔨 Using Builder Pattern");

        Computer computer = builder
                .setName("Custom Build")
                .setCPU(cpu)
                .setGPU(gpu)
                .setRAM(ram)
                .setStorage(storage)
                .setCaseType(config.getConfiguration("default_case"))
                .build();

        config.incrementComputersBuilt();
        return computer;
    }

    // Using Prototype Pattern
    public Computer orderPreConfiguredSystem(String prototypeKey, String customerName) {
        System.out.println("\n📋 Using Prototype Pattern");

        Computer computer = prototypeRegistry.getPrototype(prototypeKey);
        computer.setName(customerName + "'s " + computer.getName());

        config.incrementComputersBuilt();
        return computer;
    }

    public void displayShopInfo() {
        System.out.println("\n=== Computer Shop Information ===");
        System.out.println("Company: " + config.getConfiguration("company"));
        System.out.println("Location: " + config.getConfiguration("assembly_location"));
        System.out.println("Warranty: " + config.getConfiguration("warranty"));
        System.out.println("Total Systems Built: " + config.getTotalComputersBuilt());
    }
}