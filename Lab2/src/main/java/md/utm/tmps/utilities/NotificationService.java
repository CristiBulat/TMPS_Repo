package md.utm.tmps.utilities;

// Facade Pattern - Subsystem
// Sends notifications to customers
public class NotificationService {

    public void sendOrderConfirmation(String email, String transactionId, String trackingNumber) {
        System.out.println("   Sending confirmation email to: " + email);
        System.out.println("   Transaction ID: " + transactionId);
        System.out.println("   Tracking Number: " + trackingNumber);
        System.out.println("   ✓ Email sent successfully");
    }

    public void sendShippingUpdate(String email, String trackingNumber, String status) {
        System.out.println("   Sending shipping update to: " + email);
        System.out.println("   Tracking: " + trackingNumber);
        System.out.println("   Status: " + status);
    }

    public void sendDeliveryNotification(String email) {
        System.out.println("   Sending delivery notification to: " + email);
        System.out.println("   Your order has been delivered!");
    }
}