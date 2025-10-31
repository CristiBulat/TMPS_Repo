package md.utm.tmps.domain.factory.prototype;

import md.utm.tmps.domain.models.*;
import java.util.HashMap;
import java.util.Map;

// Prototype Pattern
// Registry to manage and clone pre-configured computers
public class ComputerPrototypeRegistry {
    private Map<String, Computer> prototypes = new HashMap<>();

    public ComputerPrototypeRegistry() {
        initializePrototypes();
    }

    private void initializePrototypes() {
        // Budget Gaming PC prototype
        Computer budgetGaming = new Computer("Budget Gaming PC");
        budgetGaming.setCpu(new CPU("AMD", "Ryzen 5 5600X", 6, 3.7));
        budgetGaming.setGpu(new GPU("NVIDIA", "RTX 3060", 12));
        budgetGaming.setRam(new RAM("Corsair", 16, 3200));
        budgetGaming.setStorage(new Storage("SSD", 500, "Kingston A2000"));
        budgetGaming.setCaseType("Mid Tower");
        prototypes.put("budget-gaming", budgetGaming);

        // High-End Workstation prototype
        Computer workstation = new Computer("High-End Workstation");
        workstation.setCpu(new CPU("Intel", "Xeon W-3275", 28, 2.5));
        workstation.setGpu(new GPU("NVIDIA", "RTX A6000", 48));
        workstation.setRam(new RAM("Kingston", 128, 3200));
        workstation.setStorage(new Storage("NVMe SSD", 2000, "Samsung 980 Pro"));
        workstation.setCaseType("Full Tower");
        prototypes.put("workstation", workstation);

        // Office PC prototype
        Computer office = new Computer("Office PC");
        office.setCpu(new CPU("Intel", "Core i3-12100", 4, 3.3));
        office.setGpu(new GPU("Integrated", "Intel UHD Graphics", 0));
        office.setRam(new RAM("Crucial", 8, 2666));
        office.setStorage(new Storage("SSD", 256, "WD Blue"));
        office.setCaseType("Small Form Factor");
        prototypes.put("office", office);
    }

    public Computer getPrototype(String key) {
        Computer prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Prototype not found: " + key);
        }
        return prototype.clone();
    }

    public void addPrototype(String key, Computer prototype) {
        prototypes.put(key, prototype);
    }
}