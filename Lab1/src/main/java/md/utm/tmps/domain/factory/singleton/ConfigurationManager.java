package md.utm.tmps.domain.factory.singleton;

import java.util.HashMap;
import java.util.Map;

// Singleton Pattern
// Thread-safe configuration manager using double-checked locking
public class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private Map<String, String> configurations;
    private int totalComputersBuilt;

    // Private constructor to prevent instantiation
    private ConfigurationManager() {
        configurations = new HashMap<>();
        totalComputersBuilt = 0;
        loadDefaultConfigurations();
    }

    // Thread-safe singleton instance getter
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private void loadDefaultConfigurations() {
        configurations.put("company", "TechBuild Solutions");
        configurations.put("warranty", "2 years");
        configurations.put("default_case", "Mid Tower RGB");
        configurations.put("assembly_location", "Moldova, Chisinau");
    }

    public String getConfiguration(String key) {
        return configurations.getOrDefault(key, "Not configured");
    }

    public void setConfiguration(String key, String value) {
        configurations.put(key, value);
    }

    public void incrementComputersBuilt() {
        totalComputersBuilt++;
    }

    public int getTotalComputersBuilt() {
        return totalComputersBuilt;
    }

    public void displayConfigurations() {
        System.out.println("=== Configuration Manager ===");
        configurations.forEach((key, value) ->
                System.out.printf("%s: %s\n", key, value));
        System.out.printf("Total Computers Built: %d\n", totalComputersBuilt);
    }
}