package md.utm.tmps;

import md.utm.tmps.dip.*;
import md.utm.tmps.isp.*;
import md.utm.tmps.lsp.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- SOLID Principles Lab 1 Demonstration ---");

        // 1. Liskov Substitution Principle (LSP)
        System.out.println("\n1. Liskov Substitution Principle (LSP)");
        Notification email = new EmailNotification("test@example.com", "LSP Test", "This is an email.");
        Notification sms = new SmsNotification("+123456789", "This is an SMS.");
        sendNotification(email);
        sendNotification(sms);

        // 2. Interface Segregation Principle (ISP)
        System.out.println("\n2. Interface Segregation Principle (ISP)");
        Formattable jsonFormatter = new JsonFormatter();
        System.out.println("JSON Format: " + jsonFormatter.format("Hello ISP"));
        Formattable textFormatter = new PlainTextFormatter();
        System.out.println("Text Format: " + textFormatter.format("Hello ISP"));

        // 3. Dependency Inversion Principle (DIP)
        System.out.println("\n3. Dependency Inversion Principle (DIP)");
        MessageSender emailSender = new EmailSender();
        NotificationService service = new NotificationService(emailSender);
        service.sendNotification("client@company.com", "Your invoice is ready.");
    }

    // This method works with any subclass of Notification, demonstrating LSP
    public static void sendNotification(Notification notification) {
        notification.send();
    }
}