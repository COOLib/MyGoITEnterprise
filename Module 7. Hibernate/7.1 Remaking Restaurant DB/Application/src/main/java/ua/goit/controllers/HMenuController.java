package ua.goit.controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.domains.Menu;
import ua.goit.interfaces.DishDao;
import ua.goit.interfaces.MenuDao;

import java.util.List;

public class HMenuController {


    private MenuDao menuDao;
    private DishDao dishDao;

    @Transactional
    public List<Menu> getAllMenus() {

        return menuDao.getAllMenus();
    }

    @Transactional
    public void addMenu(String name, List<Dish> dishes) {

        Menu menu = new Menu();

        menu.setName(name);
        menu.setDishes(dishes);

        menuDao.addMenu(menu);
    }

    @Transactional
    public void deleteMenu(String name) {

        menuDao.removeMenu(name);
    }

    @Transactional
    public Menu getByName(String name) {

        return menuDao.findMenuByName(name);
    }

    @Transactional
    public void addDishToMenu(String menuName, String dishName) {

        Menu menu = menuDao.findMenuByName(menuName);
        Dish dish = dishDao.findDishByName(dishName);

        menu.getDishes().add(dish);
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
