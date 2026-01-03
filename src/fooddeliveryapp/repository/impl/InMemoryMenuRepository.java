package fooddeliveryapp.repository.impl;

import fooddeliveryapp.model.MenuItem;
import fooddeliveryapp.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Using In-Memory for now with pre-populated data, can be easily swapped for File/DB
public class InMemoryMenuRepository implements MenuRepository {
    private List<MenuItem> items = new ArrayList<>();

    public InMemoryMenuRepository() {
        populateInitialMenu();
    }

    private void populateInitialMenu() {
        items.add(new MenuItem(1, "Classic Beef Burger", 65.00, "Burgers"));
        items.add(new MenuItem(2, "Cheeseburger Deluxe", 75.00, "Burgers"));
        items.add(new MenuItem(3, "Chicken Mayo Pizza", 120.00, "Pizza"));
        items.add(new MenuItem(4, "Pepperoni Feast", 135.00, "Pizza"));
        items.add(new MenuItem(5, "Crispy Chips (Large)", 35.00, "Sides"));
        items.add(new MenuItem(6, "Grilled Chicken Wrap", 85.00, "Wraps"));
        items.add(new MenuItem(7, "Coca-Cola 500ml", 22.00, "Drinks"));
        items.add(new MenuItem(8, "Water 500ml", 15.00, "Drinks"));
        items.add(new MenuItem(9, "Chocolate Brownie", 45.00, "Desserts"));
    }

    @Override
    public List<MenuItem> getAllItems() {
        return new ArrayList<>(items);
    }

    @Override
    public MenuItem getItemById(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addItem(MenuItem item) {
        items.add(item);
    }
}
