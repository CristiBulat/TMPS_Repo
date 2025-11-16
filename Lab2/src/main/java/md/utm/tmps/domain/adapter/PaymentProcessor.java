package md.utm.tmps.domain.adapter;

// Adapter Pattern
// Modern payment interface that the shop expects
public interface PaymentProcessor {
    boolean processPayment(double amount, String customerName);
    String getTransactionId();
    void refund(String transactionId);
}