# Lab Report: Behavioral Design Patterns

**Course:** Tehnici si Metode de Proiectare Software  
**Author:** Bulat Cristian  
**Date:** November 2025

---

## Introduction

### Topic
Implementation of Behavioral Design Patterns in a Computer Shop Order Management System

### Motivation
Following Labs 1 and 2 which covered creational and structural patterns respectively, Lab 3 completes the design patterns trilogy with behavioral patterns. While creational patterns deal with object creation and structural patterns deal with object composition, behavioral patterns focus on communication between objects and distribution of responsibilities.

### Theory
Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects. They describe not just patterns of objects or classes but also the patterns of communication between them. These patterns characterize complex control flow that's difficult to follow at run-time.

The three patterns implemented are:

1. **Observer Pattern** - Establishes a subscription mechanism to notify multiple objects about events
2. **Strategy Pattern** - Enables selecting algorithms at runtime
3. **Command Pattern** - Turns requests into stand-alone objects containing request information

---

## 1. Observer Pattern

### Concept
The Observer pattern defines a one-to-many dependency between objects so that when one object (the subject) changes state, all its dependents (observers) are notified and updated automatically. This pattern is fundamental for implementing distributed event handling systems.

### Problem
In our computer shop, when an order status changes, multiple systems need to react:
- Customer notification system must send emails
- Inventory system must update stock levels
- Analytics system must track statistics

Without Observer, we'd have tight coupling between OrderManager and all these systems.

### Implementation

**Location:** `src/main/java/md/utm/tmps/domain/observer/`

#### Subject Interface
```java
public interface OrderSubject {
    void registerObserver(OrderObserver observer);
    void removeObserver(OrderObserver observer);
    void notifyObservers(String eventType);
}
```

#### Observer Interface
```java
public interface OrderObserver {
    void update(Order order, String eventType);
    String getObserverName();
}
```

#### Concrete Subject - OrderManager
```java
public class OrderManager implements OrderSubject {
    private final List<OrderObserver> observers;
    private Order currentOrder;

    @Override
    public void notifyObservers(String eventType) {
        for (OrderObserver observer : observers) {
            observer.update(currentOrder, eventType);
        }
    }

    public void confirmOrder(String orderId) {
        Order order = orders.get(orderId);
        order.setStatus(Order.OrderStatus.CONFIRMED);
        this.currentOrder = order;
        notifyObservers("ORDER_CONFIRMED");
    }
}
```

#### Concrete Observers

**CustomerNotificationObserver:**
```java
@Override
public void update(Order order, String eventType) {
    String message = switch (eventType) {
        case "ORDER_CREATED" -> String.format(
            "Email to %s: Your order %s has been created!",
            order.getCustomerEmail(), order.getOrderId());
        case "ORDER_SHIPPED" -> String.format(
            "Email to %s: Order %s has been shipped!",
            order.getCustomerEmail(), order.getOrderId());
        // ... more cases
    };
    System.out.println(message);
}
```

**InventoryObserver:**
```java
@Override
public void update(Order order, String eventType) {
    switch (eventType) {
        case "ORDER_CONFIRMED" -> {
            int current = inventory.get(computerName);
            inventory.put(computerName, current - 1);
        }
        case "ORDER_CANCELLED" -> {
            int current = inventory.get(computerName);
            inventory.put(computerName, current + 1);
        }
    }
}
```

**AnalyticsObserver:**
```java
@Override
public void update(Order order, String eventType) {
    switch (eventType) {
        case "ORDER_CREATED" -> totalOrdersCreated++;
        case "ORDER_DELIVERED" -> {
            totalOrdersCompleted++;
            totalRevenue += order.getFinalPrice();
        }
        case "ORDER_CANCELLED" -> totalOrdersCancelled++;
    }
}
```

### Key Benefits
1. **Loose Coupling:** OrderManager doesn't know concrete observer types
2. **Open/Closed:** New observers can be added without modifying OrderManager
3. **Broadcast Communication:** Single event notifies all interested parties
4. **Dynamic Subscription:** Observers can be added/removed at runtime

### Usage Example
```java
OrderManager manager = new OrderManager();

// Register observers
manager.registerObserver(new CustomerNotificationObserver());
manager.registerObserver(new InventoryObserver());
manager.registerObserver(new AnalyticsObserver());

// Any order change automatically notifies all observers
Order order = manager.createOrder("John", "john@email.com", computer);
manager.confirmOrder(order.getOrderId());
// All 3 observers automatically notified!
```

---

## 2. Strategy Pattern

### Concept
The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it. The pattern extracts algorithm-specific code into separate classes.

### Problem
Our computer shop needs different pricing strategies:
- Regular pricing for standard customers
- Student discount (15%) for students
- Bulk order discount (tiered: 5%/10%/20%)
- Holiday sales (Black Friday, Cyber Monday, etc.)

Without Strategy, we'd have complex conditional logic in the Order class.

### Implementation

**Location:** `src/main/java/md/utm/tmps/domain/strategy/`

#### Strategy Interface
```java
public interface PricingStrategy {
    double calculatePrice(Order order);
    String getStrategyName();
    String getDiscountDescription();
}
```

#### Concrete Strategies

**RegularPricingStrategy:**
```java
public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Order order) {
        return order.getComputer().getBasePrice();
    }
}
```

**StudentDiscountStrategy:**
```java
public class StudentDiscountStrategy implements PricingStrategy {
    private static final double STUDENT_DISCOUNT = 0.15;

    @Override
    public double calculatePrice(Order order) {
        double basePrice = order.getComputer().getBasePrice();
        return basePrice * (1 - STUDENT_DISCOUNT);
    }
}
```

**BulkOrderStrategy:**
```java
public class BulkOrderStrategy implements PricingStrategy {
    private final int quantity;

    @Override
    public double calculatePrice(Order order) {
        double basePrice = order.getComputer().getBasePrice();
        double discountRate = calculateDiscountRate();
        return basePrice * quantity * (1 - discountRate);
    }

    private double calculateDiscountRate() {
        if (quantity >= 10) return 0.20;
        if (quantity >= 5) return 0.10;
        if (quantity >= 3) return 0.05;
        return 0.0;
    }
}
```

**HolidaySaleStrategy:**
```java
public class HolidaySaleStrategy implements PricingStrategy {
    private final String holidayName;
    private final double discountRate;

    public static HolidaySaleStrategy blackFriday() {
        return new HolidaySaleStrategy("Black Friday", 0.25);
    }

    @Override
    public double calculatePrice(Order order) {
        double basePrice = order.getComputer().getBasePrice();
        return basePrice * (1 - discountRate);
    }
}
```

#### Context Class
```java
public class PricingContext {
    private PricingStrategy strategy;

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(Order order) {
        double finalPrice = strategy.calculatePrice(order);
        order.setFinalPrice(finalPrice);
        order.setDiscountApplied(strategy.getDiscountDescription());
        return finalPrice;
    }
}
```

### Key Benefits
1. **Algorithm Isolation:** Each pricing algorithm in its own class
2. **Runtime Flexibility:** Can switch strategies without recompiling
3. **Eliminates Conditionals:** No if-else chains for pricing decisions
4. **Easy Testing:** Each strategy can be tested independently

### Usage Example
```java
PricingContext context = new PricingContext();
Order order = new Order("Test", "test@email.com", computer);

// Use different strategies
context.setStrategy(new RegularPricingStrategy());
double regularPrice = context.executeStrategy(order);

context.setStrategy(new StudentDiscountStrategy());
double studentPrice = context.executeStrategy(order);

context.setStrategy(HolidaySaleStrategy.blackFriday());
double salePrice = context.executeStrategy(order);
```

---

## 3. Command Pattern

### Concept
The Command pattern encapsulates a request as an object, thereby allowing parameterization of clients with different requests, queuing of requests, and logging of requests. It also supports undoable operations.

### Problem
Our order management system needs to:
- Execute order operations (place, cancel, update status)
- Track operation history
- Support undo functionality
- Potentially queue operations

Without Command, these operations would be scattered throughout the code with no unified way to track or undo them.

### Implementation

**Location:** `src/main/java/md/utm/tmps/domain/command/`

#### Command Interface
```java
public interface OrderCommand {
    void execute();
    void undo();
    String getCommandName();
    boolean isReversible();
}
```

#### Concrete Commands

**PlaceOrderCommand:**
```java
public class PlaceOrderCommand implements OrderCommand {
    private final OrderManager orderManager;
    private final String customerName;
    private final Computer computer;
    private final PricingContext pricingContext;
    private Order createdOrder;

    @Override
    public void execute() {
        createdOrder = orderManager.createOrder(customerName, customerEmail, computer);
        pricingContext.executeStrategy(createdOrder);
        orderManager.confirmOrder(createdOrder.getOrderId());
    }

    @Override
    public void undo() {
        if (createdOrder != null) {
            orderManager.cancelOrder(createdOrder.getOrderId());
        }
    }
}
```

**CancelOrderCommand:**
```java
public class CancelOrderCommand implements OrderCommand {
    private final OrderManager orderManager;
    private final String orderId;
    private Order.OrderStatus previousStatus;

    @Override
    public void execute() {
        Order order = orderManager.getOrder(orderId);
        previousStatus = order.getStatus();
        orderManager.cancelOrder(orderId);
    }

    @Override
    public void undo() {
        Order order = orderManager.getOrder(orderId);
        order.setStatus(previousStatus);
    }
}
```

**UpdateOrderStatusCommand:**
```java
public class UpdateOrderStatusCommand implements OrderCommand {
    private final OrderManager orderManager;
    private final String orderId;
    private final Order.OrderStatus newStatus;
    private Order.OrderStatus previousStatus;

    @Override
    public void execute() {
        Order order = orderManager.getOrder(orderId);
        previousStatus = order.getStatus();
        // Execute appropriate status change
        switch (newStatus) {
            case PROCESSING -> orderManager.processOrder(orderId);
            case SHIPPED -> orderManager.shipOrder(orderId);
            case DELIVERED -> orderManager.deliverOrder(orderId);
        }
    }

    @Override
    public void undo() {
        Order order = orderManager.getOrder(orderId);
        order.setStatus(previousStatus);
    }
}
```

#### Invoker Class
```java
public class OrderCommandInvoker {
    private final Stack<OrderCommand> commandHistory;
    private final List<String> executionLog;

    public void executeCommand(OrderCommand command) {
        command.execute();
        if (command.isReversible()) {
            commandHistory.push(command);
        }
        executionLog.add("Executed: " + command.getCommandName());
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            OrderCommand lastCommand = commandHistory.pop();
            lastCommand.undo();
            executionLog.add("Undone: " + lastCommand.getCommandName());
        }
    }
}
```

### Key Benefits
1. **Decoupling:** Invoker doesn't know command specifics
2. **Undo Support:** Each command knows how to reverse itself
3. **History Tracking:** Complete log of all operations
4. **Extensibility:** New commands without changing invoker

### Usage Example
```java
OrderCommandInvoker invoker = new OrderCommandInvoker();

// Create and execute place order command
PlaceOrderCommand placeOrder = new PlaceOrderCommand(
    orderManager, "Alice", "alice@email.com", computer, pricingContext
);
invoker.executeCommand(placeOrder);

// Update status
UpdateOrderStatusCommand updateStatus = new UpdateOrderStatusCommand(
    orderManager, placeOrder.getCreatedOrder().getOrderId(), 
    Order.OrderStatus.PROCESSING
);
invoker.executeCommand(updateStatus);

// Undo last command
invoker.undoLastCommand(); // Reverts to CONFIRMED status
```

---

## Pattern Integration

### How Patterns Work Together

The three behavioral patterns integrate to create a cohesive order management system:

```
┌─────────────────────────────────────────────────────────────┐
│                         CLIENT                               │
│                      (ComputerShop)                          │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                   COMMAND PATTERN                            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │PlaceOrder    │  │CancelOrder   │  │UpdateStatus  │       │
│  │Command       │  │Command       │  │Command       │       │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘       │
│         └─────────────────┼─────────────────┘               │
│                           │                                  │
│              ┌────────────▼────────────┐                    │
│              │  OrderCommandInvoker    │                    │
│              │  (executes & tracks)    │                    │
│              └────────────┬────────────┘                    │
└───────────────────────────┼─────────────────────────────────┘
                            │
            ┌───────────────┴───────────────┐
            │                               │
            ▼                               ▼
┌───────────────────────┐      ┌───────────────────────────┐
│   STRATEGY PATTERN    │      │    OBSERVER PATTERN       │
│  ┌─────────────────┐  │      │  ┌──────────────────────┐ │
│  │PricingContext   │  │      │  │   OrderManager       │ │
│  └────────┬────────┘  │      │  │    (Subject)         │ │
│           │           │      │  └──────────┬───────────┘ │
│  ┌────────┴────────┐  │      │             │             │
│  │   Strategies:   │  │      │  ┌──────────┼──────────┐  │
│  │ • Regular       │  │      │  │          │          │  │
│  │ • Student       │  │      │  ▼          ▼          ▼  │
│  │ • Bulk          │  │      │ Customer Inventory Analytics│
│  │ • Holiday       │  │      │ Observer  Observer  Observer│
│  └─────────────────┘  │      └───────────────────────────┘
└───────────────────────┘
```

### Integration Example

```java
// 1. Setup Observer pattern - register all observers
orderManager.registerObserver(customerNotificationObserver);
orderManager.registerObserver(inventoryObserver);
orderManager.registerObserver(analyticsObserver);

// 2. Setup Strategy pattern - choose pricing
pricingContext.setStrategy(HolidaySaleStrategy.blackFriday());

// 3. Use Command pattern - execute operation
PlaceOrderCommand command = new PlaceOrderCommand(
    orderManager, "Customer", "email@test.com", computer, pricingContext
);
commandInvoker.executeCommand(command);
// This triggers:
// - Strategy calculates Black Friday price
// - Observer notifies Customer, Inventory, Analytics
// - Command is stored for potential undo
```

---

## Testing Strategy

### Unit Tests

Each pattern has dedicated tests:

```java
// Observer Pattern Test
@Test
void testObserverNotification() {
    TestObserver testObserver = new TestObserver();
    orderManager.registerObserver(testObserver);
    orderManager.createOrder("Test", "test@test.com", computer);
    
    assertTrue(testObserver.wasNotified());
    assertEquals("ORDER_CREATED", testObserver.getLastEventType());
}

// Strategy Pattern Test
@Test
void testStudentDiscount() {
    pricingContext.setStrategy(new StudentDiscountStrategy());
    double expectedPrice = computer.getBasePrice() * 0.85;
    double actualPrice = pricingContext.executeStrategy(order);
    
    assertEquals(expectedPrice, actualPrice, 0.01);
}

// Command Pattern Test
@Test
void testCommandUndo() {
    commandInvoker.executeCommand(placeOrderCommand);
    assertEquals(Order.OrderStatus.CONFIRMED, order.getStatus());
    
    commandInvoker.undoLastCommand();
    assertEquals(Order.OrderStatus.CANCELLED, order.getStatus());
}
```

### Integration Tests

```java
@Test
void testAllPatternsIntegration() {
    // Setup all patterns
    orderManager.registerObserver(testObserver);
    pricingContext.setStrategy(new StudentDiscountStrategy());
    
    // Execute via command
    commandInvoker.executeCommand(placeOrderCommand);
    
    // Verify all patterns worked
    assertTrue(testObserver.wasNotified());  // Observer
    assertEquals(expectedPrice, order.getFinalPrice());  // Strategy
    assertEquals(1, commandInvoker.getHistorySize());  // Command
}
```

---

## Results and Conclusions

### Achievements

1. **Implemented 3 behavioral patterns**
   - Observer for event-driven notifications
   - Strategy for flexible pricing algorithms
   - Command for undoable operations

2. **Demonstrated pattern synergy**
   - Patterns work together seamlessly
   - Each handles its specific responsibility
   - Combined system is more powerful than parts

3. **SOLID Principles adherence**
   - Single Responsibility: Each class has one purpose
   - Open/Closed: Extensible without modification
   - Dependency Inversion: Depend on abstractions

### Pattern Benefits Observed

**Observer Pattern:**
- ✓ Decoupled order management from notification systems
- ✓ Easy to add new observers (e.g., SMS notification)
- ✓ Automatic notification on state changes

**Strategy Pattern:**
- ✓ Pricing logic isolated and testable
- ✓ Runtime strategy switching
- ✓ No complex conditionals

**Command Pattern:**
- ✓ Full undo capability
- ✓ Operation history
- ✓ Decoupled operation execution

### Comparison with Previous Labs

| Aspect | Lab 1 (Creational) | Lab 2 (Structural) | Lab 3 (Behavioral) |
|--------|-------------------|-------------------|-------------------|
| Focus | Object creation | Object composition | Object communication |
| Patterns | Singleton, Factory, Builder, Prototype | Adapter, Decorator, Facade | Observer, Strategy, Command |
| Problem Solved | How to create objects | How to compose objects | How objects interact |

### Real-World Applications

These patterns are used extensively in production systems:
- **Observer:** GUI event systems, message brokers, reactive programming
- **Strategy:** Payment processing, authentication methods, sorting algorithms
- **Command:** Transaction systems, text editors (undo), game engines

---

## Conclusions

The implementation of behavioral design patterns successfully demonstrates how objects can communicate and distribute responsibilities effectively:

1. **Observer** enables reactive, event-driven architecture
2. **Strategy** provides algorithm flexibility without code changes
3. **Command** enables undo/redo and operation tracking

Together with creational (Lab 1) and structural (Lab 2) patterns, this completes a comprehensive understanding of the Gang of Four design patterns. The computer shop domain effectively showcases how all pattern categories work together to create maintainable, flexible, and robust software systems.

---

## References

1. Gang of Four (GoF) Design Patterns: Elements of Reusable Object-Oriented Software
2. "Head First Design Patterns" by Freeman & Freeman
3. "Design Patterns Explained" by Shalloway & Trott
4. Java Observer Pattern Documentation
5. Martin Fowler - Command Pattern