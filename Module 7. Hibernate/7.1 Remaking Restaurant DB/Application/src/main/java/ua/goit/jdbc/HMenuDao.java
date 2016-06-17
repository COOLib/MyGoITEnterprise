package ua.goit.jdbc;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.domains.Menu;
import ua.goit.interfaces.DishDao;
import ua.goit.interfaces.MenuDao;

import java.util.List;

public class HMenuDao implements MenuDao {

    private SessionFactory sessionFactory;
    private DishDao dishDao;

    @Override
    @Transactional
    public void addMenu(Menu menu) {

        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    @Transactional
    public void removeMenu(String name) {

        Menu menu = findMenuByName(name);

        sessionFactory.getCurrentSession().delete(menu);
    }

    @Override
    @Transactional
    public Menu findMenuByName(String name) {

        Menu menu = sessionFactory.getCurrentSession().get(Menu.class, name);

        if (menu == null) {
            throw new RuntimeException("Cannot find menu with name " + name);
        }
        return menu;
    }

    @Override
    @Transactional
    public List<Menu> getAllMenus() {

        return sessionFactory.getCurrentSession().createQuery("select e from Menu e").list();
    }

    @Override
    @Transactional
    public void addDishToMenu(String menuName, String dishName) {

        Menu menu = findMenuByName(menuName);

        Dish dish = dishDao.findDishByName(dishName);
        menu.getDishes().add(dish);
    }

    @Override
    @Transactional
    public void removeDishFromMenu(String menuName, String dishName) {

        Menu menu = findMenuByName(menuName);
        Dish dish = dishDao.findDishByName(dishName);

        menu.getDishes().remove(dish);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}