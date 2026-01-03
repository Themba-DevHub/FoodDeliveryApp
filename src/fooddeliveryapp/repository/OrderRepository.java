package fooddeliveryapp.repository;

import fooddeliveryapp.model.Order;
import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
    Order findById(String id);
}
