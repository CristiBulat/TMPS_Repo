package md.utm.tmps.domain.models;

public class RAM implements Cloneable {
    private String brand;
    private int capacity;
    private int speed;

    public RAM(String brand, int capacity, int speed) {
        this.brand = brand;
        this.capacity = capacity;
        this.speed = speed;
    }

    @Override
    public RAM clone() {
        try {
            return (RAM) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    public String getBrand() { return brand; }
    public int getCapacity() { return capacity; }
    public int getSpeed() { return speed; }

    @Override
    public String toString() {
        return String.format("%s %dGB DDR4-%d", brand, capacity, speed);
    }
}