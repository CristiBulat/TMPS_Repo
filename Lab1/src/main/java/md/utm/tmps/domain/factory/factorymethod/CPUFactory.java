package md.utm.tmps.domain.factory.factorymethod;

import md.utm.tmps.domain.models.CPU;

// Concrete Factory for CPUs
public class CPUFactory extends ComponentFactory<CPU> {

    @Override
    public CPU createComponent(String type) {
        return switch (type.toLowerCase()) {
            case "intel-i5" -> new CPU("Intel", "Core i5-12600K", 10, 3.7);
            case "intel-i7" -> new CPU("Intel", "Core i7-12700K", 12, 3.6);
            case "intel-i9" -> new CPU("Intel", "Core i9-12900K", 16, 3.2);
            case "amd-r5" -> new CPU("AMD", "Ryzen 5 5600X", 6, 3.7);
            case "amd-r7" -> new CPU("AMD", "Ryzen 7 5800X", 8, 3.8);
            case "amd-r9" -> new CPU("AMD", "Ryzen 9 5950X", 16, 3.4);
            default -> throw new IllegalArgumentException("Unknown CPU type: " + type);
        };
    }
}