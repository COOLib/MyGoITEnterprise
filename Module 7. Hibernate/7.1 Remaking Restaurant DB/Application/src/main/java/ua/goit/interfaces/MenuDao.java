package ua.goit.interfaces;

import ua.goit.domains.Menu;

import java.util.List;

/**
 * Created by COOLib on 31.05.2016.
 */
public interface MenuDao {

    void addMenu(Menu menu);

    void removeMenu(String name);

    Menu findMenuByName(String name);

    List<Menu> getAllMenus();

    void addDishToMenu(String menuName, String dishName);

    void removeDishFromMenu(String menuName, String dishName);
}
