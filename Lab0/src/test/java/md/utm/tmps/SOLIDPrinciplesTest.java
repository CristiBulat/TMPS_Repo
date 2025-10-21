package md.utm.tmps;

import md.utm.tmps.dip.*;
import md.utm.tmps.isp.*;
import md.utm.tmps.lsp.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SOLIDPrinciplesTest {

    @Test
    @DisplayName("✅ LSP: Subclasses should be substitutable for their base class")
    void testLiskovSubstitutionPrinciple() {
        Notification email = new EmailNotification("test@example.com", "Subject", "Message");
        Notification sms = new SmsNotification("12345", "Short message");

        // Both can be passed to a method expecting the base class
        assertDoesNotThrow(() -> Main.sendNotification(email));
        assertDoesNotThrow(() -> Main.sendNotification(sms));
        assertEquals("test@example.com", email.getRecipient());
    }

    @Test
    @DisplayName("✅ ISP: Classes implement only the interfaces they need")
    void testInterfaceSegregationPrinciple() {
        Formattable jsonFormatter = new JsonFormatter();
        String jsonResult = jsonFormatter.format("test");
        assertTrue(jsonResult.contains("\"message\": \"test\""));

        Formattable textFormatter = new PlainTextFormatter();
        String textResult = textFormatter.format("test");
        assertTrue(textResult.contains("PlainText: test"));
    }

    @Test
    @DisplayName("✅ DIP: High-level modules depend on abstractions")
    void testDependencyInversionPrinciple() {
        // Mock MessageSender to test NotificationService in isolation
        class MockSender implements MessageSender {
            String recipient, message;
            int callCount = 0;
            @Override
            public void sendMessage(String recipient, String message) {
                this.recipient = recipient;
                this.message = message;
                this.callCount++;
            }
        }

        MockSender mockSender = new MockSender();
        NotificationService service = new NotificationService(mockSender);
        service.sendNotification("mock@test.com", "DIP Test");

        assertEquals(1, mockSender.callCount);
        assertEquals("mock@test.com", mockSender.recipient);
        assertEquals("DIP Test", mockSender.message);
    }
}
