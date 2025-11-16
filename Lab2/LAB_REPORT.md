# Lab Report: Structural Design Patterns

**Course:** Tehnici si Metode de Proiectare Software  
**Author:** Bulat Cristian  
**Date:** November 2025

---

## Introduction

### Topic
Implementation of Structural Design Patterns in a Computer Manufacturing System

### Motivation
Following Lab 1's implementation of creational patterns, Lab 2 extends the system with structural patterns that focus on how objects and classes are composed to form larger, more complex structures. While creational patterns deal with object creation mechanisms, structural patterns ease the design by identifying simple ways to realize relationships between entities.

### Theory
Structural design patterns explain how to assemble objects and classes into larger structures while keeping these structures flexible and efficient. They use inheritance to compose interfaces and define ways to compose objects to obtain new functionality.

The three patterns implemented are:

1. **Adapter Pattern** - Converts the interface of a class into another interface clients expect
2. **Decorator Pattern** - Attaches additional responsibilities to an object dynamically
3. **Facade Pattern** - Provides a unified interface to a set of interfaces in a subsystem

---

## 1. Adapter Pattern

### Concept
The Adapter pattern acts as a bridge between two incompatible interfaces. It wraps an existing class with a new interface so that it becomes compatible with the client's interface. This is particularly useful when integrating legacy systems or third-party libraries.

### Implementation

**Location:** `src/main/java/md/utm/tmps/domain/adapter/`

**Problem:**
Our computer shop needs to integrate a legacy payment system that has a different interface than our modern payment processing system.

**Solution:**
Create an adapter that translates calls from our modern `PaymentProcessor` interface to the legacy system's methods.

#### Target Interface
```java
public interface PaymentProcessor {
    boolean processPayment(double amount, String customerName);
    String getTransactionId();
    void refund(String transactionId);
}
```

#### Adaptee (Legacy System)
```java
public class LegacyPaymentSystem {
    public String makeTransaction(String client, double sum) {
        // Different method signature and return type
        this.lastTransactionCode = "LEG-" + UUID.randomUUID();
        return lastTransactionCode;
    }
    
    public void cancelTransaction(String code) {
        // Different method name
    }
}
```

#### Adapter Implementation
```java
public class PaymentAdapter implements PaymentProcessor {
    private final LegacyPaymentSystem legacySystem;
    
    @Override
    public boolean processPayment(double amount, String customerName) {
        // Adapt parameters and return type
        this.currentTransactionId = legacySystem.makeTransaction(customerName, amount);
        return true;
    }
    
    @Override
    public void refund(String transactionId) {
        // Adapt method call
        legacySystem.cancelTransaction(transactionId);
    }
}
```

### Key Benefits
1. **Reusability:** Legacy code can be reused without modification
2. **Single Responsibility:** Adapter handles interface translation
3. **Flexibility:** Easy to add new payment providers
4. **Open/Closed:** Can extend with new adapters without modifying existing code

### Usage Example
```java
// Both legacy and modern systems work through same interface
LegacyPaymentSystem legacy = new LegacyPaymentSystem();
PaymentProcessor adapter = new PaymentAdapter(legacy);

PaymentProcessor modern = new ModernPaymentProcessor();

// Client code works with both
adapter.processPayment(1500.00, "John Doe");
modern.processPayment(1500.00, "Jane Smith");
```

---

## 2. Decorator Pattern

### Concept
The Decorator pattern allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class. It provides a flexible alternative to subclassing for extending functionality.

### Implementation

**Location:** `src/main/java/md/utm/tmps/domain/decorator/`

**Problem:**
Customers want to customize their computers with various enhancements (RGB lighting, water cooling, extended warranty). Creating a subclass for every combination would lead to class explosion (2^n classes for n features).

**Solution:**
Use decorators that wrap the base computer and add functionality dynamically.

#### Component Interface
```java
public interface ComputerComponent {
    String getDescription();
    double getCost();
    void displaySpecs();
}
```

#### Concrete Component
```java
public class BaseComputer implements ComputerComponent {
    private final Computer computer;
    private final double basePrice;
    
    @Override
    public double getCost() {
        return basePrice;
    }
}
```

#### Abstract Decorator
```java
public abstract class ComputerDecorator implements ComputerComponent {
    protected ComputerComponent wrappedComponent;
    
    public ComputerDecorator(ComputerComponent component) {
        this.wrappedComponent = component;
    }
}
```

#### Concrete Decorators
```java
public class RGBLightingDecorator extends ComputerDecorator {
    private static final double RGB_COST = 150.0;
    
    @Override
    public double getCost() {
        return wrappedComponent.getCost() + RGB_COST;
    }
    
    @Override
    public String getDescription() {
        return wrappedComponent.getDescription() + " + RGB Lighting";
    }
}

public class WaterCoolingDecorator extends ComputerDecorator {
    private static final double COOLING_COST = 300.0;
    
    @Override
    public double getCost() {
        return wrappedComponent.getCost() + COOLING_COST;
    }
}

public class ExtendedWarrantyDecorator extends ComputerDecorator {
    private final int years;
    
    @Override
    public double getCost() {
        return wrappedComponent.getCost() + (200.0 * years);
    }
}
```

### Key Benefits
1. **Flexibility:** Add/remove responsibilities at runtime
2. **Single Responsibility:** Each decorator handles one enhancement
3. **Open/Closed:** New decorators can be added without modifying existing code
4. **Composition:** Decorators can be stacked in any order

### Usage Example
```java
// Start with base computer
ComputerComponent computer = new BaseComputer(baseComputer, 1500.0);

// Add features dynamically
computer = new RGBLightingDecorator(computer);           // $1650
computer = new WaterCoolingDecorator(computer);          // $1950
computer = new ExtendedWarrantyDecorator(computer, 3);   // $2550

// Cost calculated automatically through chain
System.out.println(computer.getCost());  // $2550
```

### Design Highlights
- Each decorator adds exactly one responsibility
- Decorators can be combined in any order
- Cost and description automatically aggregate
- No class explosion - 3 features = 3 classes (not 2^3 = 8)

---

## 3. Facade Pattern

### Concept
The Facade pattern provides a simplified interface to a complex subsystem. It defines a higher-level interface that makes the subsystem easier to use by wrapping a complicated subsystem with a simpler interface.

### Implementation

**Location:** `src/main/java/md/utm/tmps/domain/facade/`

**Problem:**
Placing a complete computer order involves multiple complex steps across different subsystems:
1. Create computer configuration (Abstract Factory)
2. Apply enhancements (Decorator)
3. Check inventory availability
4. Process payment (Adapter)
5. Reserve inventory
6. Schedule shipping
7. Send notifications
8. Update statistics (Singleton)

**Solution:**
Create a facade that encapsulates all these steps behind a single simple method.

#### Facade Implementation
```java
public class ComputerOrderFacade {
    private final InventoryManager inventoryManager;
    private final ShippingService shippingService;
    private final NotificationService notificationService;
    private final ConfigurationManager config;
    
    public String placeCompleteOrder(
        String customerName,
        String email,
        String address,
        String systemType,
        boolean addRGB,
        boolean addWaterCooling,
        int warrantyYears,
        PaymentProcessor paymentProcessor
    ) {
        // 1. Create computer
        Computer computer = createComputerByType(systemType);
        
        // 2. Apply decorators
        ComputerComponent component = new BaseComputer(computer, getBasePrice(systemType));
        if (addRGB) component = new RGBLightingDecorator(component);
        if (addWaterCooling) component = new WaterCoolingDecorator(component);
        if (warrantyYears > 0) component = new ExtendedWarrantyDecorator(component, warrantyYears);
        
        // 3. Check inventory
        if (!inventoryManager.checkAvailability(systemType)) {
            return "ORDER FAILED: Out of stock";
        }
        
        // 4. Process payment
        if (!paymentProcessor.processPayment(component.getCost(), customerName)) {
            return "ORDER FAILED: Payment declined";
        }
        
        // 5. Reserve inventory
        inventoryManager.reserveItem(systemType);
        
        // 6. Schedule shipping
        String trackingNumber = shippingService.scheduleDelivery(customerName, address, computer.getName());
        
        // 7. Send notifications
        notificationService.sendOrderConfirmation(email, paymentProcessor.getTransactionId(), trackingNumber);
        
        // 8. Update statistics
        config.incrementComputersBuilt();
        
        return "ORDER CONFIRMED - Transaction: " + paymentProcessor.getTransactionId();
    }
}
```

#### Subsystems

**InventoryManager**
```java
public class InventoryManager {
    public boolean checkAvailability(String itemType);
    public void reserveItem(String itemType);
    public void restockItem(String itemType, int quantity);
}
```

**ShippingService**
```java
public class ShippingService {
    public String scheduleDelivery(String customerName, String address, String item);
    public String getDeliveryEstimate();
    public void cancelShipment(String trackingNumber);
}
```

**NotificationService**
```java
public class NotificationService {
    public void sendOrderConfirmation(String email, String txId, String tracking);
    public void sendShippingUpdate(String email, String tracking, String status);
    public void sendDeliveryNotification(String email);
}
```

### Key Benefits
1. **Simplification:** Complex multi-step process reduced to single method call
2. **Decoupling:** Clients don't need to know about subsystems
3. **Flexibility:** Subsystems can change without affecting clients
4. **Maintainability:** Centralized coordination logic

### Usage Example

**Without Facade:**
```java
// Client must coordinate all subsystems
Computer computer = createComputer();
ComputerComponent enhanced = applyDecorators(computer);
if (inventory.check()) {
    if (payment.process()) {
        inventory.reserve();
        String tracking = shipping.schedule();
        notifications.send();
        config.update();
    }
}
```

**With Facade:**
```java
// Simple single method call
String result = facade.placeCompleteOrder(
    "John Doe",
    "john@email.com",
    "123 Main St",
    "intel-gaming",
    true, true, 2,
    paymentProcessor
);
```

---

## Pattern Integration

### How Patterns Work Together

The three structural patterns integrate seamlessly with the five creational patterns from Lab 1:

```
┌─────────────────────────────────────────────────────────┐
│                    CLIENT                                │
│                 (ComputerShop)                           │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
         ┌───────────────────────┐
         │   FACADE PATTERN      │
         │  (Order Coordination) │
         └───────┬───────────────┘
                 │
        ┌────────┼────────┐
        ▼        ▼        ▼
   ┌─────────┐ ┌────┐ ┌──────┐
   │ADAPTER  │ │... │ │...   │
   │(Payment)│ │    │ │      │
   └─────────┘ └────┘ └──────┘
        │
        ▼
   ┌─────────────────┐
   │   DECORATOR     │
   │  (Enhancements) │
   └────────┬────────┘
            │
            ▼
   ┌──────────────────┐
   │  CREATIONAL      │
   │  PATTERNS        │
   │  (Lab 1)         │
   └──────────────────┘
```

### Specific Integrations

1. **Facade + Abstract Factory**
   ```java
   // Facade uses Abstract Factory internally
   ComputerPartsFactory factory = chooseFactory(systemType);
   Computer computer = buildWithFactory(factory);
   ```

2. **Facade + Decorator**
   ```java
   // Facade applies decorators based on options
   ComputerComponent component = new BaseComputer(computer, price);
   if (addRGB) component = new RGBLightingDecorator(component);
   ```

3. **Facade + Adapter**
   ```java
   // Facade uses adapted payment system
   boolean success = paymentProcessor.processPayment(cost, customer);
   ```

4. **Facade + Singleton**
   ```java
   // Facade accesses global configuration
   ConfigurationManager config = ConfigurationManager.getInstance();
   config.incrementComputersBuilt();
   ```

---

## Testing Strategy

### Unit Tests

Each pattern has dedicated test methods:

```java
@Test
void testAdapterPattern() {
    LegacyPaymentSystem legacy = new LegacyPaymentSystem();
    PaymentProcessor adapter = new PaymentAdapter(legacy);
    
    assertTrue(adapter.processPayment(100.0, "Test"));
    assertNotNull(adapter.getTransactionId());
}

@Test
void testDecoratorPattern() {
    ComputerComponent base = new BaseComputer(computer, 1000.0);
    ComputerComponent decorated = new RGBLightingDecorator(base);
    
    assertEquals(1150.0, decorated.getCost());
    assertTrue(decorated.getDescription().contains("RGB"));
}

@Test
void testFacadePattern() {
    ComputerOrderFacade facade = new ComputerOrderFacade();
    String result = facade.placeCompleteOrder(...);
    
    assertTrue(result.contains("ORDER CONFIRMED"));
}
```

### Integration Tests

```java
@Test
void testPatternsIntegration() {
    // Tests Facade using Adapter and Decorator together
    PaymentProcessor payment = new PaymentAdapter(new LegacyPaymentSystem());
    String result = facade.placeCompleteOrder(..., payment);
    
    assertTrue(result.contains("CONFIRMED"));
}
```

---

## Results and Conclusions

### Achievements

1. **Successfully implemented 3 structural patterns**
    - Adapter: Legacy payment system integration
    - Decorator: Dynamic feature enhancement
    - Facade: Simplified order processing

2. **Maintained backward compatibility**
    - All Lab 1 functionality still works
    - New patterns extend, not replace

3. **SOLID Principles adherence**
    - Single Responsibility: Each class has one purpose
    - Open/Closed: Extensible without modification
    - Liskov Substitution: Interfaces properly implemented
    - Interface Segregation: Small, focused interfaces
    - Dependency Inversion: Depend on abstractions

### Pattern Benefits Observed

**Adapter Pattern:**
- ✓ Integrated legacy system without modifying it
- ✓ Provided uniform interface for different payment systems
- ✓ Easy to add new payment providers

**Decorator Pattern:**
- ✓ Added features without subclass explosion
- ✓ Features composable in any combination
- ✓ Cost calculation automatic and accurate
- ✓ Easy to add new enhancement types

**Facade Pattern:**
- ✓ Reduced client complexity dramatically
- ✓ Centralized subsystem coordination
- ✓ Made system easier to use and understand
- ✓ Hid implementation details from clients

### Challenges and Solutions

**Challenge 1:** Integrating new patterns with existing creational patterns  
**Solution:** Facade serves as coordinator, using all patterns appropriately

**Challenge 2:** Maintaining flexibility while simplifying  
**Solution:** Facade provides simple interface but allows access to subsystems when needed

**Challenge 3:** Avoiding tight coupling  
**Solution:** All patterns use interfaces and dependency injection

### Real-World Applicability

These patterns solve real software engineering problems:

- **Adapter:** Common in API integrations, legacy system modernization
- **Decorator:** Used in I/O streams, UI components, middleware
- **Facade:** Essential in enterprise applications, API design

### Future Enhancements

1. Add more structural patterns (Proxy, Bridge, Composite)
2. Implement caching in Facade for repeated orders
3. Add Proxy for lazy-loading computer configurations
4. Use Bridge to separate computer abstraction from implementation
5. Apply Composite for computer component hierarchies

---

## Conclusions

The implementation of structural design patterns successfully extends the computer manufacturing system from Lab 1. The patterns demonstrate:

1. **Complementary Nature:** Structural patterns complement creational patterns perfectly
2. **Practical Value:** Each pattern solves a real, common software design problem
3. **Maintainability:** Code is easier to modify and extend
4. **Best Practices:** Adherence to SOLID principles and OOP best practices

The project showcases how design patterns work together to create a robust, flexible, and maintainable software system. The facade pattern particularly demonstrates the power of combining multiple patterns to achieve a simple, elegant solution to complex problems.

---

## References

1. Gang of Four (GoF) Design Patterns: Elements of Reusable Object-Oriented Software
2. "Head First Design Patterns" by Freeman & Freeman
3. "Design Patterns Explained" by Shalloway & Trott
4. Java Documentation - Interface and Inheritance
5. SOLID Principles Documentation
6. Martin Fowler - Enterprise Application Patterns