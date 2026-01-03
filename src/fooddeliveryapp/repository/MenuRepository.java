package fooddeliveryapp.repository;

import fooddeliveryapp.model.MenuItem;
import java.util.List;

public interface MenuRepository {
    List<MenuItem> getAllItems();
    MenuItem getItemById(int id);
    void addItem(MenuItem item);
    // Add update/delete if needed later
}
