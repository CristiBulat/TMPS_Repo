package md.utm.tmps.domain.models;

public class GPU implements Cloneable {
    private String brand;
    private String model;
    private int vram;

    public GPU(String brand, String model, int vram) {
        this.brand = brand;
        this.model = model;
        this.vram = vram;
    }

    @Override
    public GPU clone() {
        try {
            return (GPU) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getVram() { return vram; }

    @Override
    public String toString() {
        return String.format("%s %s (%dGB VRAM)", brand, model, vram);
    }
}