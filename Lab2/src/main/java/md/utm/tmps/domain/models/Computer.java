package md.utm.tmps.domain.models;

// Simple Computer model for Lab 2
public class Computer {
    private String name;
    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String caseType;
    private double basePrice;

    public Computer(String name) {
        this.name = name;
    }

    // Full constructor
    public Computer(String name, String cpu, String gpu, String ram, String storage, String caseType, double basePrice) {
        this.name = name;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.storage = storage;
        this.caseType = caseType;
        this.basePrice = basePrice;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCpu() { return cpu; }
    public void setCpu(String cpu) { this.cpu = cpu; }

    public String getGpu() { return gpu; }
    public void setGpu(String gpu) { this.gpu = gpu; }

    public String getRam() { return ram; }
    public void setRam(String ram) { this.ram = ram; }

    public String getStorage() { return storage; }
    public void setStorage(String storage) { this.storage = storage; }

    public String getCaseType() { return caseType; }
    public void setCaseType(String caseType) { this.caseType = caseType; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    @Override
    public String toString() {
        return String.format("""
            Computer: %s
            ├─ CPU: %s
            ├─ GPU: %s
            ├─ RAM: %s
            ├─ Storage: %s
            ├─ Case: %s
            └─ Base Price: $%.2f
            """, name, cpu, gpu, ram, storage, caseType, basePrice);
    }
}