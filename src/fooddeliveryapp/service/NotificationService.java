package fooddeliveryapp.service;

import fooddeliveryapp.model.Order;

public class NotificationService {

    public void sendOrderConfirmation(Order order) {
        System.out.println("Email sent to " + order.getCustomer().getEmail() + ": Order #" + order.getId() + " confirmed.");
    }

    public void simulateOrderTracking(Order order) {
        System.out.println("\nORDER STATUS UPDATES:");
        updateStatus("Order Received");
        updateStatus("Being Prepared");
        updateStatus("Out for Delivery");
        updateStatus("Delivered");
    }

    private void updateStatus(String status) {
        System.out.println("📦 " + status);
        // Simulate delay
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
