package md.utm.tmps.domain.adapter;

import java.util.UUID;

// Adapter Pattern
// Legacy payment system with incompatible interface
public class LegacyPaymentSystem {
    private String lastTransactionCode;

    public String makeTransaction(String client, double sum) {
        // Old system uses different method signature and returns transaction code
        this.lastTransactionCode = "LEG-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.printf("Legacy System: Processing $%.2f for %s\n", sum, client);
        System.out.println("Legacy Transaction Code: " + lastTransactionCode);
        return lastTransactionCode;
    }

    public void cancelTransaction(String code) {
        System.out.println("Legacy System: Cancelling transaction " + code);
    }

    public String getLastTransaction() {
        return lastTransactionCode;
    }
}