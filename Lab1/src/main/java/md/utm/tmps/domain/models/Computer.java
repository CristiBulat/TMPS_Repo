package md.utm.tmps.domain.models;

// Main Computer model that will be built using various patterns
public class Computer implements Cloneable {
    private String name;
    private CPU cpu;
    private GPU gpu;
    private RAM ram;
    private Storage storage;
    private String caseType;

    public Computer(String name) {
        this.name = name;
    }

    // Prototype Pattern: Deep copy
    @Override
    public Computer clone() {
        try {
            Computer cloned = (Computer) super.clone();
            // Deep copy of components
            if (this.cpu != null) cloned.cpu = this.cpu.clone();
            if (this.gpu != null) cloned.gpu = this.gpu.clone();
            if (this.ram != null) cloned.ram = this.ram.clone();
            if (this.storage != null) cloned.storage = this.storage.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CPU getCpu() { return cpu; }
    public void setCpu(CPU cpu) { this.cpu = cpu; }

    public GPU getGpu() { return gpu; }
    public void setGpu(GPU gpu) { this.gpu = gpu; }

    public RAM getRam() { return ram; }
    public void setRam(RAM ram) { this.ram = ram; }

    public Storage getStorage() { return storage; }
    public void setStorage(Storage storage) { this.storage = storage; }

    public String getCaseType() { return caseType; }
    public void setCaseType(String caseType) { this.caseType = caseType; }

    @Override
    public String toString() {
        return String.format("""
            Computer: %s
            ├─ CPU: %s
            ├─ GPU: %s
            ├─ RAM: %s
            ├─ Storage: %s
            └─ Case: %s
            """, name, cpu, gpu, ram, storage, caseType);
    }
}