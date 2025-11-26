package md.utm.tmps.domain.observer;

import md.utm.tmps.domain.models.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Observer Pattern
// Concrete Subject - manages orders and notifies observers of changes
public class OrderManager implements OrderSubject {
    private final List<OrderObserver> observers;
    private final Map<String, Order> orders;
    private Order currentOrder;

    public OrderManager() {
        this.observers = new ArrayList<>();
        this.orders = new HashMap<>();
    }

    @Override
    public void registerObserver(OrderObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.printf("   📝 Observer registered: %s\n", observer.getObserverName());
        }
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
        System.out.printf("   ❌ Observer removed: %s\n", observer.getObserverName());
    }

    @Override
    public void notifyObservers(String eventType) {
        System.out.printf("\n   🔔 Notifying %d observers about: %s\n", observers.size(), eventType);
        System.out.println("   " + "─".repeat(40));
        for (OrderObserver observer : observers) {
            observer.update(currentOrder, eventType);
        }
    }

    public Order createOrder(String customerName, String email, md.utm.tmps.domain.models.Computer computer) {
        Order order = new Order(customerName, email, computer);
        this.currentOrder = order;
        orders.put(order.getOrderId(), order);
        notifyObservers("ORDER_CREATED");
        return order;
    }

    public void confirmOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(Order.OrderStatus.CONFIRMED);
            this.currentOrder = order;
            notifyObservers("ORDER_CONFIRMED");
        }
    }

    public void processOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(Order.OrderStatus.PROCESSING);
            this.currentOrder = order;
            notifyObservers("ORDER_PROCESSING");
        }
    }

    public void shipOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(Order.OrderStatus.SHIPPED);
            this.currentOrder = order;
            notifyObservers("ORDER_SHIPPED");
        }
    }

    public void deliverOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(Order.OrderStatus.DELIVERED);
            this.currentOrder = order;
            notifyObservers("ORDER_DELIVERED");
        }
    }

    public void cancelOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(Order.OrderStatus.CANCELLED);
            this.currentOrder = order;
            notifyObservers("ORDER_CANCELLED");
        }
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public int getObserverCount() {
        return observers.size();
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}