# Lab Report: Creational Design Patterns

**Course:** Tehnici si Metode de Proiectare Software  
**Author:** Bulat Cristian
**Date:** October 2025

## 1. Singleton Pattern

### Concept
The Singleton pattern ensures that a class has only one instance throughout the application's lifetime and provides a global point of access to it. This is useful for managing shared resources like configuration settings, logging, or connection pools.

### Implementation
**Class:** `ConfigurationManager`

The implementation uses the **double-checked locking** technique for thread-safe lazy initialization:

```java
public static ConfigurationManager getInstance() {
    if (instance == null) {
        synchronized (ConfigurationManager.class) {
            if (instance == null) {
                instance = new ConfigurationManager();
            }
        }
    }
    return instance;
}
```

**Key Features:**
- Private constructor prevents external instantiation
- Volatile keyword ensures visibility across threads
- Manages application-wide configurations
- Tracks global statistics (computers built)

**Usage in Project:**
```java
ConfigurationManager config = ConfigurationManager.getInstance();
config.setConfiguration("warranty", "2 years");
config.incrementComputersBuilt();
```

**Verification:** Unit tests confirm that multiple calls to `getInstance()` return the same object reference.

---

## 2. Factory Method Pattern

### Concept
The Factory Method pattern defines an interface for creating objects but lets subclasses decide which class to instantiate. It delegates the instantiation logic to subclasses, promoting loose coupling and adherence to the Open/Closed Principle.

### Implementation
**Abstract Factory:** `ComponentFactory<T>`  
**Concrete Factories:** `CPUFactory`, `GPUFactory`

The abstract factory defines the factory method:
```java
public abstract T createComponent(String type);
```

Concrete factories implement this method to create specific components:
```java
public class CPUFactory extends ComponentFactory<CPU> {
    @Override
    public CPU createComponent(String type) {
        return switch (type.toLowerCase()) {
            case "intel-i9" -> new CPU("Intel", "Core i9-12900K", 16, 3.2);
            case "amd-r9" -> new CPU("AMD", "Ryzen 9 5950X", 16, 3.4);
            // ... more cases
        };
    }
}
```

**Key Benefits:**
- Encapsulates object creation logic
- Easy to add new product types without modifying existing code
- Centralizes component specifications

**Usage in Project:**
```java
ComponentFactory<CPU> cpuFactory = new CPUFactory();
CPU cpu = cpuFactory.createComponent("intel-i9");
```

---

## 3. Abstract Factory Pattern

### Concept
The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. It ensures that products from the same family are used together, maintaining compatibility.

### Implementation
**Abstract Factory Interface:** `ComputerPartsFactory`  
**Concrete Factories:** `IntelPartsFactory`, `AMDPartsFactory`

The abstract factory defines methods for creating each component:
```java
public interface ComputerPartsFactory {
    CPU createCPU();
    GPU createGPU();
    RAM createRAM();
    Storage createStorage();
}
```

Each concrete factory creates a compatible family of products:
```java
public class IntelPartsFactory implements ComputerPartsFactory {
    @Override
    public CPU createCPU() {
        return new CPU("Intel", "Core i7-12700K", 12, 3.6);
    }
    
    @Override
    public GPU createGPU() {
        return new GPU("NVIDIA", "RTX 3080", 10);
    }
    // ... creates matching RAM and Storage
}
```

**Key Benefits:**
- Guarantees component compatibility
- Easy to swap entire product families
- Isolates concrete classes from client code

**Usage in Project:**
```java
ComputerPartsFactory factory = new IntelPartsFactory();
Computer computer = new Computer("Intel System");
computer.setCpu(factory.createCPU());
computer.setGpu(factory.createGPU());
// All components are compatible Intel/NVIDIA parts
```

---

## 4. Builder Pattern

### Concept
The Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations. It provides a fluent interface for step-by-step object construction.

### Implementation
**Builder Interface:** `ComputerBuilder`  
**Concrete Builder:** `GamingComputerBuilder`

The builder provides a fluent API:
```java
public interface ComputerBuilder {
    ComputerBuilder setName(String name);
    ComputerBuilder setCPU(CPU cpu);
    ComputerBuilder setGPU(GPU gpu);
    ComputerBuilder setRAM(RAM ram);
    ComputerBuilder setStorage(Storage storage);
    ComputerBuilder setCaseType(String caseType);
    Computer build();
}
```

The concrete builder implements validation logic:
```java
@Override
public Computer build() {
    if (computer.getGpu() == null) {
        throw new IllegalStateException("Gaming PC must have a GPU!");
    }
    return computer;
}
```

**Key Benefits:**
- Readable, fluent API (method chaining)
- Enforces construction constraints
- Separates construction from representation
- Can have multiple builders for different computer types

**Usage in Project:**
```java
Computer pc = new GamingComputerBuilder()
    .setName("Ultimate Gaming Rig")
    .setCPU(cpu)
    .setGPU(gpu)
    .setRAM(ram)
    .setStorage(storage)
    .build();
```

---

## 5. Prototype Pattern

### Concept
The Prototype pattern creates new objects by copying (cloning) existing instances rather than creating them from scratch. This is useful when object creation is expensive or when you need pre-configured templates.

### Implementation
**Prototype Registry:** `ComputerPrototypeRegistry`  
**Cloneable Models:** All component classes implement `Cloneable`

The registry maintains pre-configured prototypes:
```java
public Computer getPrototype(String key) {
    Computer prototype = prototypes.get(key);
    if (prototype == null) {
        throw new IllegalArgumentException("Prototype not found: " + key);
    }
    return prototype.clone();
}
```

Deep cloning is implemented in the `Computer` class:
```java
@Override
public Computer clone() {
    try {
        Computer cloned = (Computer) super.clone();
        if (this.cpu != null) cloned.cpu = this.cpu.clone();
        if (this.gpu != null) cloned.gpu = this.gpu.clone();
        // ... clone all components
        return cloned;
    } catch (CloneNotSupportedException e) {
        throw new RuntimeException("Clone not supported", e);
    }
}
```

**Pre-configured Prototypes:**
- **Budget Gaming PC:** AMD Ryzen 5, RTX 3060, 16GB RAM
- **Workstation:** Intel Xeon, RTX A6000, 128GB RAM
- **Office PC:** Intel i3, Integrated Graphics, 8GB RAM

**Key Benefits:**
- Fast object creation from templates
- Reduces initialization overhead
- Easy to manage pre-configured options
- Independent clones (deep copy)

**Usage in Project:**
```java
ComputerPrototypeRegistry registry = new ComputerPrototypeRegistry();
Computer pc = registry.getPrototype("budget-gaming");
pc.setName("John's Gaming PC");
```

---

## Pattern Integration

The project demonstrates how patterns work together in a cohesive system:

1. **Singleton** (`ConfigurationManager`) provides global settings used by all patterns
2. **Factory Method** creates individual components
3. **Abstract Factory** ensures component compatibility
4. **Builder** assembles components with validation
5. **Prototype** provides quick access to pre-configured systems

### Real-World Scenario
A computer shop (`ComputerShop` client) uses:
- **Singleton** for shop-wide settings and statistics
- **Factory Method** when customers want specific components
- **Abstract Factory** for pre-built compatible systems (Intel vs AMD)
- **Builder** for custom configurations with validation
- **Prototype** for popular configurations that customers can quickly order

---

## Testing Strategy

### Unit Tests
Each pattern has dedicated tests:
- **Singleton:** Verifies single instance and data sharing
- **Factory Method:** Tests component creation and types
- **Abstract Factory:** Validates family compatibility
- **Builder:** Tests fluent API and validation
- **Prototype:** Confirms deep copying and independence

### Integration Test
Tests all patterns working together in a realistic workflow.

---

## Conclusions

### Benefits Observed

1. **Code Reusability:** Patterns provide proven, reusable solutions
2. **Maintainability:** Clear separation of concerns
3. **Flexibility:** Easy to extend without modifying existing code
4. **Testability:** Patterns facilitate unit testing through abstraction
5. **Best Practices:** Implements SOLID principles naturally

### Challenges Addressed

1. **Object Creation Complexity:** Patterns simplify complex instantiation
2. **Component Compatibility:** Abstract Factory ensures matching parts
3. **Configuration Management:** Singleton provides centralized control
4. **Template Management:** Prototype enables efficient cloning

### Pattern Selection Guidelines

- Use **Singleton** for shared resources requiring single instance
- Use **Factory Method** when object creation varies by subtype
- Use **Abstract Factory** for families of related objects
- Use **Builder** for complex objects with many optional parameters
- Use **Prototype** when objects are expensive to create or need templates

### SOLID Principles Adherence

- **Single Responsibility:** Each class has one clear purpose
- **Open/Closed:** Patterns allow extension without modification
- **Liskov Substitution:** Interfaces enable proper substitution
- **Interface Segregation:** Small, focused interfaces
- **Dependency Inversion:** Code depends on abstractions

---

## Future Improvements

1. Add more builder types (WorkstationBuilder, OfficeBuilder)
2. Implement Object Pool pattern for component reuse
3. Add more product families (ARM-based systems)
4. Enhance validation rules in builders
5. Add persistence for configurations and prototypes

---

## References

- Gang of Four (GoF) Design Patterns
- "Head First Design Patterns" by Freeman & Freeman
- Java Documentation on Cloneable interface
- SOLID Principles documentation