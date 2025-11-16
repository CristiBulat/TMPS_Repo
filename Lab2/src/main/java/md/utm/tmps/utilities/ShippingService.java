package md.utm.tmps.utilities;

import java.util.UUID;

// Facade Pattern - Subsystem
// Handles shipping and delivery
public class ShippingService {

    public String scheduleDelivery(String customerName, String address, String itemDescription) {
        String trackingNumber = "SHIP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        System.out.println("   Creating shipping label...");
        System.out.println("   Customer: " + customerName);
        System.out.println("   Address: " + address);
        System.out.println("   Item: " + itemDescription);
        System.out.println("   Tracking Number: " + trackingNumber);

        return trackingNumber;
    }

    public String getDeliveryEstimate() {
        return "3-5 business days";
    }

    public void cancelShipment(String trackingNumber) {
        System.out.println("   Cancelled shipment: " + trackingNumber);
    }
}