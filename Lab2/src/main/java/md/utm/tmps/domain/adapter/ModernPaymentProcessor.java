package md.utm.tmps.domain.adapter;

import java.util.UUID;

// Adapter Pattern
// Modern payment processor that implements the interface natively
public class ModernPaymentProcessor implements PaymentProcessor {
    private String lastTransactionId;

    @Override
    public boolean processPayment(double amount, String customerName) {
        this.lastTransactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.printf("Modern System: Processing payment of $%.2f for %s\n", amount, customerName);
        System.out.println("Transaction ID: " + lastTransactionId);
        return true;
    }

    @Override
    public String getTransactionId() {
        return lastTransactionId;
    }

    @Override
    public void refund(String transactionId) {
        System.out.println("Modern System: Processing refund for " + transactionId);
    }
}