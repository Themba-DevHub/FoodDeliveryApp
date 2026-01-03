package fooddeliveryapp;

import fooddeliveryapp.repository.MenuRepository;
import fooddeliveryapp.repository.OrderRepository;
import fooddeliveryapp.repository.impl.InMemoryMenuRepository;
import fooddeliveryapp.repository.impl.FileOrderRepository;
import fooddeliveryapp.service.MenuService;
import fooddeliveryapp.service.NotificationService;
import fooddeliveryapp.service.OrderService;
import fooddeliveryapp.ui.ConsoleUI;

/**
 * Main application entry point.
 */
public class FoodDeliveryApp {

    public static void main(String[] args) {
        // Dependency Injection / Composition Root
        
        // Repositories
        MenuRepository menuRepository = new InMemoryMenuRepository();
        OrderRepository orderRepository = new FileOrderRepository();
        
        // Services
        MenuService menuService = new MenuService(menuRepository);
        OrderService orderService = new OrderService(orderRepository);
        NotificationService notificationService = new NotificationService();
        
        // UI
        ConsoleUI consoleUI = new ConsoleUI(menuService, orderService, notificationService);
        
        // Start Application
        consoleUI.start();
    }
}
