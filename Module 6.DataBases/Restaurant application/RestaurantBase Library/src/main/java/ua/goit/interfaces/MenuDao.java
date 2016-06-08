package ua.goit.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Menu;

import java.util.List;

/**
 * Created by COOLib on 31.05.2016.
 */
public interface MenuDao {
    @Transactional(propagation = Propagation.REQUIRED)
    void addMenu(Menu menu);

    @Transactional(propagation = Propagation.REQUIRED)
    void removeMenu(String name);

    @Transactional(propagation = Propagation.REQUIRED)
    List<Menu> findMenuByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<Menu> getAllMenus();

    @Transactional(propagation = Propagation.REQUIRED)
    void addDishToMenu(Menu menu, String dishName, String category);

    @Transactional(propagation = Propagation.REQUIRED)
    void removeDishFromMenu(Menu menu, String dishName);

    @Transactional(propagation = Propagation.REQUIRED)
    Menu getMenuForAddingDish(String name);
}
