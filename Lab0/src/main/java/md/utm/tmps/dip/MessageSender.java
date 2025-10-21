package md.utm.tmps.dip;

// Dependency Inversion Principle (DIP)
// An abstraction (interface) that high-level modules depend on.
public interface MessageSender {
    void sendMessage(String recipient, String message);
}