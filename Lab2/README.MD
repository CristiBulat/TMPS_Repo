# Lab 2 - Structural Design Patterns

**Course:** Tehnici si Metode de Proiectare Software (TMPS)  
**Student:** Bulat Cristian  
**Date:** November 2025

## 🎯 Project Overview

This project demonstrates three structural design patterns in a Computer Shop system:

1. **Adapter Pattern**
2. **Decorator Pattern**
3. **Facade Pattern**

The implementation is completely **independent** and self-contained, with its own simplified domain model focused on demonstrating structural patterns effectively.

## 🔧 Requirements

- **Java:** JDK 17 or higher
- **Build Tool:** Maven 3.6+
- **Testing:** JUnit 5

## 🏗️ Project Structure

```
Lab2/
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
    │   │   │   └── ComputerFactory.java
    │   │   ├── adapter/
    │   │   │   ├── PaymentProcessor.java
    │   │   │   ├── LegacyPaymentSystem.java
    │   │   │   ├── PaymentAdapter.java
    │   │   │   └── ModernPaymentProcessor.java
    │   │   ├── decorator/
    │   │   │   ├── ComputerComponent.java
    │   │   │   ├── BaseComputer.java
    │   │   │   ├── ComputerDecorator.java
    │   │   │   ├── RGBLightingDecorator.java
    │   │   │   ├── WaterCoolingDecorator.java
    │   │   │   └── ExtendedWarrantyDecorator.java
    │   │   └── facade/
    │   │       └── ComputerOrderFacade.java
    │   └── utilities/
    │       ├── InventoryManager.java
    │       ├── ShippingService.java
    │       └── NotificationService.java
    └── test/java/md/utm/tmps/
        └── StructuralPatternsTest.java
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

## 📚 Structural Patterns Implemented

### 1. Adapter Pattern
**Purpose:** Allows incompatible interfaces to work together by wrapping an existing class with a new interface.

**Implementation:** Integration of a legacy payment system with the modern payment processing interface.

**Key Classes:**
- `PaymentProcessor` - Target interface
- `LegacyPaymentSystem` - Adaptee (incompatible legacy system)
- `PaymentAdapter` - Adapter that makes legacy system compatible
- `ModernPaymentProcessor` - Modern implementation of the interface

**Benefits:**
- Reuse existing legacy code without modification
- Maintains single interface for payment processing
- Easy to add more payment providers

### 2. Decorator Pattern
**Purpose:** Dynamically adds new functionality to objects without altering their structure.

**Implementation:** Enhancing computers with optional features like RGB lighting, water cooling, and extended warranty.

**Key Classes:**
- `ComputerComponent` - Component interface
- `BaseComputer` - Concrete component
- `ComputerDecorator` - Abstract decorator
- `RGBLightingDecorator` - Adds RGB lighting (+$150)
- `WaterCoolingDecorator` - Adds custom cooling (+$300)
- `ExtendedWarrantyDecorator` - Adds warranty coverage (+$200/year)

**Benefits:**
- Flexible alternative to subclassing
- Features can be combined in any order
- Cost calculated automatically
- Open/Closed Principle adherence

### 3. Facade Pattern
**Purpose:** Provides a simplified interface to a complex subsystem.

**Implementation:** `ComputerOrderFacade` simplifies the complete ordering process.

**Subsystems:**
- `InventoryManager` - Stock management
- `ShippingService` - Delivery scheduling
- `NotificationService` - Customer notifications
- Payment processing (Adapter)
- Computer building (Factory)
- Enhancement application (Decorator)

**Benefits:**
- Reduces coupling between clients and subsystems
- Simple interface for complex operations
- Centralizes subsystem interactions

## 🔗 Pattern Integration

The project demonstrates how structural patterns work together:

1. **Facade** orchestrates:
    - **Adapter** for payment processing
    - **Decorator** for computer enhancements
    - Multiple subsystems (inventory, shipping, notifications)

2. **Decorator** enables:
    - Dynamic feature addition without class explosion
    - Flexible cost calculation

3. **Adapter** provides:
    - Uniform interface for different payment systems
    - Legacy system integration

## 🧪 Testing

Comprehensive JUnit 5 tests covering:
- Adapter pattern interface compatibility
- Decorator pattern chaining and cost calculation
- Facade pattern subsystem coordination
- Integration between all patterns

**Test Coverage:**
- 17 unit and integration tests
- All patterns tested individually and together
- Edge cases and error handling

## 💡 Design Decisions

1. **Independent Implementation:** No dependencies on Lab 1 patterns
2. **Simple Domain Model:** Focused Computer class for clarity
3. **Factory Utility:** `ComputerFactory` provides pre-configured computers
4. **Payment Integration:** Demonstrates Adapter with legacy/modern systems
5. **Enhancement System:** Decorator allows flexible customization

## 📖 Additional Documentation

- `LAB_REPORT.md` - Detailed pattern explanations and implementation notes
- `QUICK_START.md` - Step-by-step execution guide

## ✅ Benefits Demonstrated

- **Adapter:** Legacy system integration without code changes
- **Decorator:** Runtime feature composition without subclass explosion
- **Facade:** Simplified client code for complex operations
- **SOLID Principles:** All patterns maintain SOLID adherence
- **Pattern Synergy:** Shows how patterns complement each other

## 🎓 Learning Objectives

This lab demonstrates:
- How to integrate incompatible interfaces (Adapter)
- How to add responsibilities dynamically (Decorator)
- How to simplify complex subsystems (Facade)
- How structural patterns solve real-world problems
- How patterns work together cohesively

## 🔄 Key Differences from Lab 1

**Lab 1** focused on **Creational Patterns**:
- How to create objects (Singleton, Factory, Builder, Prototype)
- Object instantiation mechanisms

**Lab 2** focuses on **Structural Patterns**:
- How to compose objects (Decorator, Adapter)
- How to simplify access (Facade)
- Object composition and relationships

Both labs use the same domain (Computer Shop) but with different focuses and independent implementations.