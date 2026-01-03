package fooddeliveryapp.repository.impl;

import fooddeliveryapp.model.Order;
import fooddeliveryapp.repository.OrderRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOrderRepository implements OrderRepository {
    private static final String FILE_NAME = "orders.dat";
    private List<Order> orders;

    public FileOrderRepository() {
        this.orders = loadOrders();
    }

    @Override
    public void save(Order order) {
        orders.removeIf(o -> o.getId().equals(order.getId()));
        orders.add(order);
        saveOrdersToFile();
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public Order findById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void saveOrdersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            System.err.println("Error saving orders: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Order> loadOrders() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading orders: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
