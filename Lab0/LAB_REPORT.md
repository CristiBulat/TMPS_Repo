# Lab Report: SOLID Principles (LSP, ISP, DIP)

## 1. Liskov Substitution Principle (LSP)

### Concept
The Liskov Substitution Principle states that any subclass should be substitutable for its parent class without causing errors or unexpected behavior.

### Implementation
-   **`Notification` (abstract class):** Defines the base contract with an abstract `send()` method.
-   **`EmailNotification` & `SmsNotification` (subclasses):** Both extend `Notification` and provide their own `send()` implementation.
-   **Verification:** The `Main.sendNotification(Notification n)` method demonstrates this. It can accept any object that is a `Notification` (like `EmailNotification` or `SmsNotification`) and function correctly, proving that the subclasses are perfectly substitutable.

## 2. Interface Segregation Principle (ISP)

### Concept
The Interface Segregation Principle suggests that it is better to have many small, client-specific interfaces than one large, general-purpose interface.

### Implementation
-   **`Formattable` (interface):** This interface is highly specialized. It contains only one method: `format(String message)`.
-   **`JsonFormatter` & `PlainTextFormatter`:** These classes implement the `Formattable` interface. They are not forced to implement any methods they don't need, such as `parse()` or `validate()`, which might exist in a larger, "fat" interface. This keeps the classes lean and focused.

## 3. Dependency Inversion Principle (DIP)

### Concept
This principle states that high-level modules should depend on abstractions, not on concrete low-level implementations. This decouples the code and makes it more flexible.

### Implementation
-   **`MessageSender` (interface):** This is the abstraction. It defines a `sendMessage` contract.
-   **`EmailSender` (low-level module):** This is a concrete implementation of `MessageSender`.
-   **`NotificationService` (high-level module):** This service does not know about `EmailSender`. It only knows about the `MessageSender` interface. The specific sender (`EmailSender`) is "injected" through the constructor. This means we can easily create a `PushNotificationSender` later and give it to the `NotificationService` without changing a single line of code in the service itself.