package md.utm.tmps.domain.observer;

import md.utm.tmps.domain.models.Order;

// Observer Pattern
// Observer interface - defines the update method that observers must implement
public interface OrderObserver {
    void update(Order order, String eventType);
    String getObserverName();
}