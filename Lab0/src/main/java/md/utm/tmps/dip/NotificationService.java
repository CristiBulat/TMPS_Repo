package md.utm.tmps.dip;

// A high-level module that depends on the MessageSender abstraction, not a concrete implementation.
public class NotificationService {
    private final MessageSender messageSender;

    // Dependency is injected via the constructor
    public NotificationService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendNotification(String recipient, String message) {
        messageSender.sendMessage(recipient, message);
    }
}
