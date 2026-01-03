package fooddeliveryapp.service;

import fooddeliveryapp.model.Customer;
import fooddeliveryapp.model.MenuItem;
import fooddeliveryapp.model.Order;
import fooddeliveryapp.model.OrderItem;
import fooddeliveryapp.repository.OrderRepository;

public class OrderService {
    private OrderRepository orderRepository;
    private static final double TAX_RATE = 0.15;
    private static final double DELIVERY_FEE = 35.00;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Customer customer) {
        return new Order(customer);
    }

    public void addItemToOrder(Order order, MenuItem item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        order.addItem(new OrderItem(item, quantity));
    }

    public void placeOrder(Order order) {
        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot place an empty order.");
        }
        order.calculateTotal(TAX_RATE, DELIVERY_FEE);
        order.setStatus("PLACED");
        orderRepository.save(order);
    }
    
    public double getDeliveryFee() { return DELIVERY_FEE; }
    public double getTaxRate() { return TAX_RATE; }
}
