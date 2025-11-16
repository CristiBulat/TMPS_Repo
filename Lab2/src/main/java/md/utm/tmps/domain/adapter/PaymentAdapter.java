package md.utm.tmps.domain.adapter;

// Adapter Pattern
// Adapter that makes legacy payment system compatible with modern interface
public class PaymentAdapter implements PaymentProcessor {
    private final LegacyPaymentSystem legacySystem;
    private String currentTransactionId;

    public PaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public boolean processPayment(double amount, String customerName) {
        try {
            // Adapt the old interface to new interface
            this.currentTransactionId = legacySystem.makeTransaction(customerName, amount);
            System.out.println("✓ Adapter: Payment processed successfully");
            return true;
        } catch (Exception e) {
            System.out.println("✗ Adapter: Payment failed - " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getTransactionId() {
        return currentTransactionId != null ? currentTransactionId : legacySystem.getLastTransaction();
    }

    @Override
    public void refund(String transactionId) {
        // Adapt refund method
        legacySystem.cancelTransaction(transactionId);
        System.out.println("✓ Adapter: Refund completed");
    }
}