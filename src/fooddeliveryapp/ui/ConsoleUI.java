package fooddeliveryapp.ui;

import fooddeliveryapp.model.Customer;
import fooddeliveryapp.model.MenuItem;
import fooddeliveryapp.model.Order;
import fooddeliveryapp.model.OrderItem;
import fooddeliveryapp.service.MenuService;
import fooddeliveryapp.service.NotificationService;
import fooddeliveryapp.service.OrderService;
import fooddeliveryapp.util.ValidationUtils;
import java.util.List;
import java.util.UUID;

public class ConsoleUI {
    private MenuService menuService;
    private OrderService orderService;
    private NotificationService notificationService;

    public ConsoleUI(MenuService menuService, OrderService orderService, NotificationService notificationService) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.notificationService = notificationService;
    }

    public void start() {
        System.out.println("===========================================");
        System.out.println("      Welcome to QuickEats Food Delivery");
        System.out.println("===========================================");

        Customer customer = registerCustomer();
        Order currentOrder = orderService.createOrder(customer);

        boolean ordering = true;
        while (ordering) {
            displayMenu();
            int choice = ValidationUtils.getValidInt("\nEnter item number to add (0 to finish): ", 0, menuService.getFullMenu().size());

            if (choice == 0) {
                ordering = false;
            } else {
                MenuItem selectedItem = menuService.getMenuItem(choice);
                if (selectedItem != null) {
                    int quantity = ValidationUtils.getValidInt("Enter quantity: ", 1, 50);
                    orderService.addItemToOrder(currentOrder, selectedItem, quantity);
                    System.out.println("Added " + quantity + " x " + selectedItem.getName() + " to your order.");
                } else {
                    System.out.println("Invalid selection.");
                }
            }
        }

        if (currentOrder.getItems().isEmpty()) {
            System.out.println("No items selected. Exiting.");
            return;
        }

        displayOrderSummary(currentOrder);
        
        String confirmation = ValidationUtils.getValidString("\nConfirm order? (Y/N): ");
        if (confirmation.equalsIgnoreCase("Y")) {
            orderService.placeOrder(currentOrder);
            System.out.println("\nOrder placed successfully!");
            notificationService.sendOrderConfirmation(currentOrder);
            notificationService.simulateOrderTracking(currentOrder);
            System.out.println("\nThank you for ordering with QuickEats!");
        } else {
            System.out.println("Order cancelled.");
        }
        System.out.println("===========================================");
    }

    private Customer registerCustomer() {
        System.out.println("\nPlease enter your details:");
        String name = ValidationUtils.getValidString("Name: ");
        String email = ValidationUtils.getValidString("Email: ");
        String phone = ValidationUtils.getValidString("Phone: ");
        String address = ValidationUtils.getValidString("Delivery Address: ");
        return new Customer(UUID.randomUUID().toString(), name, email, phone, address);
    }

    private void displayMenu() {
        System.out.println("\nMENU:");
        List<MenuItem> menu = menuService.getFullMenu();
        for (MenuItem item : menu) {
            System.out.printf("%d. %s%n", item.getId(), item.toString());
        }
    }

    private void displayOrderSummary(Order order) {
        order.calculateTotal(orderService.getTaxRate(), orderService.getDeliveryFee());
        
        System.out.println("\nORDER SUMMARY:");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Address: " + order.getCustomer().getAddress());
        System.out.println("-------------------------------------------");
        
        for (OrderItem item : order.getItems()) {
            System.out.println(item.toString());
        }

        System.out.println("-------------------------------------------");
        System.out.printf("Subtotal:         R%.2f%n", order.getTotalAmount() - order.getTax() - order.getDeliveryFee());
        System.out.printf("Tax (15%%):        R%.2f%n", order.getTax());
        System.out.printf("Delivery Fee:     R%.2f%n", order.getDeliveryFee());
        System.out.println("-------------------------------------------");
        System.out.printf("TOTAL:            R%.2f%n", order.getTotalAmount());
    }
}
