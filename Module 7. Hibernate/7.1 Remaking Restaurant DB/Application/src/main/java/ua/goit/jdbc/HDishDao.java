package ua.goit.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.interfaces.DishDao;

import java.util.List;

public class HDishDao implements DishDao {

    private SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(HDishDao.class);

    @Override
    @Transactional
    public void addDish(Dish dish) {

        LOGGER.info("Connecting to database. Running method is addDish");
        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public void removeDish(String name) {

        LOGGER.info("Connecting to database. Running method is removeDish");
        Dish dish = findDishByName(name);
        sessionFactory.getCurrentSession().delete(dish);
    }

    @Override
    @Transactional
    public Dish findDishByName(String name) {

        LOGGER.info("Connecting to database. Running method is findDishByName");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Dish e where e.name=:name");
        query.setParameter("name", name);
        Dish dish = (Dish) query.uniqueResult();

        if (dish == null) {
            LOGGER.error("Cannot find dish with name " + name);
            throw new RuntimeException("Cannot find dish with name " + name);
        }
        return dish;
    }

    @Override
    @Transactional
    public List<Dish> getAllDishes() {

        LOGGER.info("Connecting to database. Running method is getAllDishes");
        return sessionFactory.getCurrentSession().createQuery("select e from Dish e").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}