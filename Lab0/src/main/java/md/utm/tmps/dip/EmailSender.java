package md.utm.tmps.dip;

// A low-level module that implements the abstraction.
public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String recipient, String message) {
        System.out.printf("DIP: Sending Email via provider to %s: %s\n", recipient, message);
    }
}
