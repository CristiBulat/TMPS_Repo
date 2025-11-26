# Lab 3 - Behavioral Design Patterns

**Course:** Tehnici si Metode de Proiectare Software (TMPS)  
**Student:** Bulat Cristian  
**Date:** November 2025

## 🎯 Project Overview

This project demonstrates three behavioral design patterns in a Computer Shop Order Management system:

1. **Observer Pattern**
2. **Strategy Pattern**
3. **Command Pattern**

Behavioral patterns focus on communication between objects, defining how objects interact and distribute responsibilities. This lab extends the computer shop domain from previous labs with sophisticated order management capabilities.

## 🔧 Requirements

- **Java:** JDK 17 or higher
- **Build Tool:** Maven 3.6+
- **Testing:** JUnit 5

## 🏗️ Project Structure

```
Lab3/
├── pom.xml
├── README.md
├── LAB_REPORT.md
├── QUICK_START.md
└── src/
    ├── main/java/md/utm/tmps/
    │   ├── Main.java
    │   ├── client/
    │   │   └── ComputerShop.java
    │   ├── domain/
    │   │   ├── models/
    │   │   │   ├── Computer.java
    │   │   │   ├── ComputerFactory.java
    │   │   │   └── Order.java
    │   │   ├── observer/
    │   │   │   ├── OrderObserver.java
    │   │   │   ├── OrderSubject.java
    │   │   │   ├── OrderManager.java
    │   │   │   ├── CustomerNotificationObserver.java
    │   │   │   ├── InventoryObserver.java
    │   │   │   └── AnalyticsObserver.java
    │   │   ├── strategy/
    │   │   │   ├── PricingStrategy.java
    │   │   │   ├── PricingContext.java
    │   │   │   ├── RegularPricingStrategy.java
    │   │   │   ├── StudentDiscountStrategy.java
    │   │   │   ├── BulkOrderStrategy.java
    │   │   │   └── HolidaySaleStrategy.java
    │   │   └── command/
    │   │       ├── OrderCommand.java
    │   │       ├── PlaceOrderCommand.java
    │   │       ├── CancelOrderCommand.java
    │   │       ├── UpdateOrderStatusCommand.java
    │   │       └── OrderCommandInvoker.java
    │   └── utilities/
    │       └── OrderHistory.java
    └── test/java/md/utm/tmps/
        └── BehavioralPatternsTest.java
```

## 🚀 How to Run

See the `QUICK_START.md` file for detailed instructions.

**Quick commands:**
```bash
# Compile
mvn compile

# Run the demonstration
mvn exec:java -Dexec.mainClass="md.utm.tmps.Main"

# Run tests
mvn test
```

## 📚 Behavioral Patterns Implemented

### 1. Observer Pattern
**Purpose:** Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically.

**Implementation:** Order status changes trigger notifications to multiple systems.

**Key Classes:**
- `OrderSubject` - Subject interface with attach/detach/notify methods
- `OrderObserver` - Observer interface with update method
- `OrderManager` - Concrete subject managing orders and notifications
- `CustomerNotificationObserver` - Sends customer emails
- `InventoryObserver` - Updates stock levels
- `AnalyticsObserver` - Tracks statistics and revenue

**Benefits:**
- Loose coupling between order management and dependent systems
- Easy to add new observers without modifying existing code
- Automatic notification of all interested parties

### 2. Strategy Pattern
**Purpose:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

**Implementation:** Different pricing algorithms can be applied to orders.

**Key Classes:**
- `PricingStrategy` - Strategy interface
- `PricingContext` - Context that uses strategies
- `RegularPricingStrategy` - No discount
- `StudentDiscountStrategy` - 15% discount
- `BulkOrderStrategy` - Tiered discount (5%/10%/20%)
- `HolidaySaleStrategy` - Seasonal discounts (Black Friday, etc.)

**Benefits:**
- Pricing logic separated from order logic
- Easy to add new pricing strategies
- Can change pricing algorithm at runtime
- No conditional statements for pricing decisions

### 3. Command Pattern
**Purpose:** Encapsulates a request as an object, allowing parameterization, queuing, logging, and undoable operations.

**Implementation:** Order operations are encapsulated as command objects.

**Key Classes:**
- `OrderCommand` - Command interface with execute/undo
- `PlaceOrderCommand` - Creates and confirms orders
- `CancelOrderCommand` - Cancels existing orders
- `UpdateOrderStatusCommand` - Changes order status
- `OrderCommandInvoker` - Executes commands and maintains history

**Benefits:**
- Operations can be undone
- Complete operation history
- Commands can be queued or scheduled
- Decouples request sender from receiver

## 🔗 Pattern Integration

The three patterns work together seamlessly:

```
┌─────────────────────────────────────────────────────────┐
│                    CLIENT                                │
│                 (ComputerShop)                           │
└────────────────────┬────────────────────────────────────┘
                     │
         ┌───────────┴───────────┐
         │                       │
         ▼                       ▼
┌─────────────────┐    ┌─────────────────┐
│ COMMAND PATTERN │    │STRATEGY PATTERN │
│    (Invoker)    │    │   (Pricing)     │
└────────┬────────┘    └────────┬────────┘
         │                      │
         └──────────┬───────────┘
                    │
                    ▼
         ┌─────────────────┐
         │ OBSERVER PATTERN│
         │ (OrderManager)  │
         └────────┬────────┘
                  │
    ┌─────────────┼─────────────┐
    │             │             │
    ▼             ▼             ▼
┌────────┐  ┌─────────┐  ┌──────────┐
│Customer│  │Inventory│  │Analytics │
│Observer│  │Observer │  │Observer  │
└────────┘  └─────────┘  └──────────┘
```

**Integration Flow:**
1. **Command** encapsulates order operations
2. **Strategy** calculates pricing within commands
3. **Observer** notifies all systems when order state changes

## 🧪 Testing

Comprehensive JUnit 5 tests covering:
- Observer registration and notification
- Strategy calculations for all discount types
- Command execution and undo functionality
- Integration between all patterns

**Run tests with:** `mvn test`

## 💡 Design Decisions

1. **Observer for Notifications:** Natural fit for multiple systems needing order updates
2. **Strategy for Pricing:** Allows flexible, runtime-changeable discount calculations
3. **Command for Operations:** Enables undo functionality and operation tracking
4. **Single Client:** ComputerShop orchestrates all patterns as required

## 📖 Additional Documentation

- `LAB_REPORT.md` - Detailed pattern explanations and theory
- `QUICK_START.md` - Step-by-step execution guide

## ✅ Pattern Benefits Demonstrated

| Pattern | Key Benefit | Example in Project |
|---------|-------------|-------------------|
| Observer | Automatic notifications | Multiple systems react to order changes |
| Strategy | Runtime flexibility | Switch between pricing algorithms |
| Command | Undo support | Cancel placed orders |

## 🎓 Learning Objectives

This lab demonstrates:
- How behavioral patterns handle object communication
- Event-driven architecture with Observer
- Algorithm encapsulation with Strategy
- Operation encapsulation with Command
- How patterns complement each other