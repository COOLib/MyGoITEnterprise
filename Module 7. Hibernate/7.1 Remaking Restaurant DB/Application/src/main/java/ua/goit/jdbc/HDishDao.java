package ua.goit.jdbc;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.interfaces.DishDao;

import java.util.List;

public class HDishDao implements DishDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addDish(Dish dish) {

        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public void removeDish(String name) {

       Dish dish = findDishByName(name);

        sessionFactory.getCurrentSession().delete(dish);
    }

    @Override
    @Transactional
    public Dish findDishByName(String name) {

       Dish dish = sessionFactory.getCurrentSession().get(Dish.class, name);

        if (dish == null) {
            throw new RuntimeException("Cannot find dish with name " + name);
        }
        return dish;
    }

    @Override
    @Transactional
    public List<Dish> getAllDishes() {

        return sessionFactory.getCurrentSession().createQuery("select e from Dish e").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}