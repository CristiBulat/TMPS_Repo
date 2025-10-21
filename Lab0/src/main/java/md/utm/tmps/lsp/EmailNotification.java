package md.utm.tmps.lsp;

// This class can be substituted for Notification without breaking the program.
public class EmailNotification extends Notification {
    private String subject;

    public EmailNotification(String recipient, String subject, String message) {
        super(recipient, message);
        this.subject = subject;
    }

    @Override
    public void send() {
        System.out.printf("Sending Email to %s with subject '%s': %s\n", recipient, subject, message);
    }

    public String getSubject() {
        return subject;
    }
}
