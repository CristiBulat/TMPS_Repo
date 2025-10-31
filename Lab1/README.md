# Lab 1 - Creational Design Patterns

**Course:** Tehnici si Metode de Proiectare Software (TMPS)  
**Student:** Bulat Cristian
**Date:** October 2025

## 🎯 Project Overview

This project demonstrates all five creational design patterns using a practical **Computer Manufacturing System** in Java:

1. **Singleton Pattern**
2. **Factory Method Pattern**
3. **Abstract Factory Pattern**
4. **Builder Pattern**
5. **Prototype Pattern**

The domain models a computer manufacturing shop where customers can order pre-configured systems, build custom computers, or clone existing configurations.

## 🔧 Requirements

- **Java:** JDK 17 or higher
- **Build Tool:** Maven 3.6+
- **Testing:** JUnit 5

## 🏗️ Project Structure

```
Lab1/
├── pom.xml
├── README.md
├── LAB_REPORT.md
├── QUICK_START.md
└── src/
    ├── main/java/md/utm/tmps/lab1/
    │   ├── Main.java
    │   ├── client/
    │   │   └── ComputerShop.java
    │   └── domain/
    │       ├── models/
    │       │   ├── Computer.java
    │       │   ├── CPU.java
    │       │   ├── GPU.java
    │       │   ├── RAM.java
    │       │   └── Storage.java
    │       └── factory/
    │           ├── factorymethod/
    │           │   ├── ComponentFactory.java
    │           │   ├── CPUFactory.java
    │           │   └── GPUFactory.java
    │           ├── abstractfactory/
    │           │   ├── ComputerPartsFactory.java
    │           │   ├── IntelPartsFactory.java
    │           │   └── AMDPartsFactory.java
    │           ├── builder/
    │           │   ├── ComputerBuilder.java
    │           │   └── GamingComputerBuilder.java
    │           ├── prototype/
    │           │   └── ComputerPrototypeRegistry.java
    │           └── singleton/
    │               └── ConfigurationManager.java
    └── test/java/md/utm/tmps/lab1/
        └── CreationalPatternsTest.java
```

## 🚀 How to Run

See the `QUICK_START.md` file for detailed instructions on how to compile and run the project.

**Quick commands:**
```bash
# Compile
mvn compile

# Run the demonstration
mvn exec:java -Dexec.mainClass="md.utm.tmps.lab1.Main"

# Run tests
mvn test
```

## 📚 Creational Patterns Implemented

### 1. Singleton Pattern
**Purpose:** Ensures only one instance of a class exists throughout the application.

**Implementation:** `ConfigurationManager` - A thread-safe singleton that manages global configurations and tracks statistics.

**Key Features:**
- Double-checked locking for thread safety
- Private constructor prevents external instantiation
- Stores application-wide settings

### 2. Factory Method Pattern
**Purpose:** Defines an interface for creating objects, but lets subclasses decide which class to instantiate.

**Implementation:**
- `ComponentFactory<T>` - Abstract factory with factory method
- `CPUFactory` - Creates different CPU models
- `GPUFactory` - Creates different GPU models

**Key Features:**
- Encapsulates object creation logic
- Easy to extend with new product types
- Follows Open/Closed Principle

### 3. Abstract Factory Pattern
**Purpose:** Provides an interface for creating families of related objects without specifying their concrete classes.

**Implementation:**
- `ComputerPartsFactory` - Abstract factory interface
- `IntelPartsFactory` - Creates Intel/NVIDIA based systems
- `AMDPartsFactory` - Creates AMD based systems

**Key Features:**
- Creates complete, compatible component sets
- Ensures parts work well together
- Easy to swap entire product families

### 4. Builder Pattern
**Purpose:** Separates the construction of a complex object from its representation.

**Implementation:**
- `ComputerBuilder` - Builder interface
- `GamingComputerBuilder` - Concrete builder for gaming PCs

**Key Features:**
- Fluent interface for readable code
- Step-by-step construction
- Built-in validation (e.g., gaming PCs must have GPU)

### 5. Prototype Pattern
**Purpose:** Creates new objects by cloning existing ones.

**Implementation:**
- `ComputerPrototypeRegistry` - Manages pre-configured prototypes
- `Computer.clone()` - Deep copy implementation

**Key Features:**
- Pre-configured templates (Budget Gaming, Workstation, Office)
- Deep cloning of all components
- Fast object creation from templates

## 🧪 Testing

The project includes comprehensive JUnit 5 tests covering:
- Individual pattern functionality
- Pattern validation rules
- Integration between patterns
- Edge cases and error handling

Run tests with: `mvn test`

## 💡 Design Decisions

1. **Domain Choice:** Computer manufacturing system chosen because:
    - Natural fit for all five patterns
    - Real-world applicability
    - Complex enough to showcase pattern benefits

2. **Package Structure:** Organized by pattern type for clarity:
    - `domain/models` - Core business objects
    - `domain/factory/*` - Separated by pattern
    - `client` - Pattern usage demonstration

3. **Cloneable Implementation:** All component models implement `Cloneable` to support the Prototype pattern with proper deep copying.

4. **Thread Safety:** Singleton uses double-checked locking for thread-safe lazy initialization.

## 📖 Additional Documentation

- `LAB_REPORT.md` - Detailed explanation of each pattern implementation
- `QUICK_START.md` - Step-by-step guide to run the project

## ✅ Pattern Benefits Demonstrated

- **Code Reusability:** Patterns promote reuse of proven solutions
- **Flexibility:** Easy to extend and modify
- **Maintainability:** Clear separation of concerns
- **Testability:** Patterns facilitate unit testing
- **Best Practices:** Implements SOLID principles


