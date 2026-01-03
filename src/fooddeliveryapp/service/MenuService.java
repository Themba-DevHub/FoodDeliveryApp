package fooddeliveryapp.service;

import fooddeliveryapp.model.MenuItem;
import fooddeliveryapp.repository.MenuRepository;
import java.util.List;

public class MenuService {
    private MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<MenuItem> getFullMenu() {
        return menuRepository.getAllItems();
    }

    public MenuItem getMenuItem(int id) {
        return menuRepository.getItemById(id);
    }
}
