package md.utm.tmps.lsp;

// This class can also be substituted for Notification.
public class SmsNotification extends Notification {
    public SmsNotification(String recipient, String message) {
        super(recipient, message);
    }

    @Override
    public void send() {
        // SMS messages have a length limit, which is a specific behavior,
        // but it doesn't violate the base class contract.
        String trimmedMessage = message.length() > 160 ? message.substring(0, 160) : message;
        System.out.printf("Sending SMS to %s: %s\n", recipient, trimmedMessage);
    }
}