package ua.goit.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.domains.Menu;
import ua.goit.interfaces.DishDao;
import ua.goit.interfaces.MenuDao;

import java.util.List;

public class HMenuDao implements MenuDao {

    private SessionFactory sessionFactory;
    private DishDao dishDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(HMenuDao.class);

    @Override
    @Transactional
    public void addMenu(Menu menu) {

        LOGGER.info("Connecting to database. Running method is addMenu");
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    @Transactional
    public void removeMenu(String name) {

        LOGGER.info("Connecting to database. Running method is removeMenu");
        Menu menu = findMenuByName(name);
        sessionFactory.getCurrentSession().delete(menu);
    }

    @Override
    @Transactional
    public Menu findMenuByName(String name) {

        LOGGER.info("Connecting to database. Running method is findMenuByName");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Menu e where e.name=:name");
        query.setParameter("name", name);
        Menu menu = (Menu) query.uniqueResult();

        if (menu == null) {
            LOGGER.error("Cannot find menu with name " + name);
            throw new RuntimeException("Cannot find menu with name " + name);
        }
        return menu;
    }

    @Override
    @Transactional
    public List<Menu> getAllMenus() {

        LOGGER.info("Connecting to database. Running method is getAllMenus");
        return sessionFactory.getCurrentSession().createQuery("select e from Menu e").list();
    }

    @Override
    @Transactional
    public void addDishToMenu(String menuName, String dishName) {

        LOGGER.info("Connecting to database. Running method is addDishToMenu");
        Menu menu = findMenuByName(menuName);

        Dish dish = dishDao.findDishByName(dishName);
        menu.getDishes().add(dish);
    }

    @Override
    @Transactional
    public void removeDishFromMenu(String menuName, String dishName) {

        LOGGER.info("Connecting to database. Running method is removeDishFromMenu");
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