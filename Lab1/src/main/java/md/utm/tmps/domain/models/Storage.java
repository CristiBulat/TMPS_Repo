package md.utm.tmps.domain.models;

public class Storage implements Cloneable {
    private String type;
    private int capacity;
    private String brand;

    public Storage(String type, int capacity, String brand) {
        this.type = type;
        this.capacity = capacity;
        this.brand = brand;
    }

    @Override
    public Storage clone() {
        try {
            return (Storage) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    public String getType() { return type; }
    public int getCapacity() { return capacity; }
    public String getBrand() { return brand; }

    @Override
    public String toString() {
        return String.format("%s %s %dGB", brand, type, capacity);
    }
}