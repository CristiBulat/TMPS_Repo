# Lab 1 - SOLID Principles Demonstration (LSP, ISP, DIP)

**Course:** Tehnici si Metode de Proiectare Software
**Student:** [Your Name]
**Date:** October 2025

## 🎯 Project Overview

This project demonstrates three of the five SOLID principles using a practical Notification System example in Java:

1.  **Liskov Substitution Principle (LSP)**
2.  **Interface Segregation Principle (ISP)**
3.  **Dependency Inversion Principle (DIP)**

Each principle is implemented in a separate package with clear examples, JUnit 5 tests, and detailed documentation.

## 🔧 Requirements

-   **Java:** JDK 17 or higher
-   **Build Tool:** Maven 3.6+
-   **Testing:** JUnit 5

## 🚀 How to Run

See the `QUICK_START.md` file for detailed instructions on how to compile and run the project and its tests.

## 📚 SOLID Principles Implemented

### 1. Liskov Substitution Principle (LSP)
> "Objects of a superclass should be replaceable with objects of a subclass without breaking the application."

-   **Implementation:** The `lsp` package contains an abstract `Notification` class. The `EmailNotification` and `SmsNotification` classes extend it. A method that accepts a `Notification` object can work with either an email or an SMS without any issues.

### 2. Interface Segregation Principle (ISP)
> "A client shouldn’t be forced to implement an interface that it doesn’t use."

-   **Implementation:** The `isp` package defines a small, focused `Formattable` interface with a single `format` method. Classes like `JsonFormatter` and `PlainTextFormatter` can implement this without being burdened by irrelevant methods.

### 3. Dependency Inversion Principle (DIP)
> "High-level modules should not depend on low-level modules. Both should depend on abstractions."

-   **Implementation:** In the `dip` package, the high-level `NotificationService` does not depend on the concrete `EmailSender`. Instead, it depends on the `MessageSender` interface (abstraction). This allows us to easily swap the `EmailSender` with another implementation (like an `SmsSender`) without changing the `NotificationService`.