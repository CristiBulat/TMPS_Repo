package md.utm.tmps.domain.models;

public class CPU implements Cloneable {
    private String brand;
    private String model;
    private int cores;
    private double frequency;

    public CPU(String brand, String model, int cores, double frequency) {
        this.brand = brand;
        this.model = model;
        this.cores = cores;
        this.frequency = frequency;
    }

    @Override
    public CPU clone() {
        try {
            return (CPU) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getCores() { return cores; }
    public double getFrequency() { return frequency; }

    @Override
    public String toString() {
        return String.format("%s %s (%d cores @ %.2f GHz)", brand, model, cores, frequency);
    }
}