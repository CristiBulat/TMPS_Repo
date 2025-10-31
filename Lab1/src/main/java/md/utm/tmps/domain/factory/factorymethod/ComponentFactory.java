package md.utm.tmps.domain.factory.factorymethod;

// Factory Method Pattern
// Abstract factory class that defines the factory method
public abstract class ComponentFactory<T> {

    // Factory Method - subclasses will implement this
    public abstract T createComponent(String type);

    // Template method that uses the factory method
    public T orderComponent(String type) {
        T component = createComponent(type);
        System.out.println("Component created: " + component);
        return component;
    }
}