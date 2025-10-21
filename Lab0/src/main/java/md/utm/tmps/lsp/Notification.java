package md.utm.tmps.lsp;

// Liskov Substitution Principle (LSP)
// Base class for all notifications. Subclasses must be substitutable for this class.
public abstract class Notification {
    protected String recipient;
    protected String message;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public abstract void send();

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}