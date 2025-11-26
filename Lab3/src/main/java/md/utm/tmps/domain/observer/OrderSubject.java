package md.utm.tmps.domain.observer;

// Observer Pattern
// Subject interface - defines methods for managing observers
public interface OrderSubject {
    void registerObserver(OrderObserver observer);
    void removeObserver(OrderObserver observer);
    void notifyObservers(String eventType);
}