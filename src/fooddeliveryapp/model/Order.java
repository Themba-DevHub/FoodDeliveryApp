package fooddeliveryapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Customer customer;
    private List<OrderItem> items;
    private double totalAmount;
    private String status;
    private LocalDateTime orderTime;
    private double deliveryFee;
    private double tax;

    public Order(Customer customer) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
        this.status = "PENDING";
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void calculateTotal(double taxRate, double deliveryFee) {
        double subtotal = items.stream().mapToDouble(OrderItem::getSubtotal).sum();
        this.tax = subtotal * taxRate;
        this.deliveryFee = deliveryFee;
        this.totalAmount = subtotal + this.tax + this.deliveryFee;
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public double getDeliveryFee() { return deliveryFee; }
    public double getTax() { return tax; }
}
