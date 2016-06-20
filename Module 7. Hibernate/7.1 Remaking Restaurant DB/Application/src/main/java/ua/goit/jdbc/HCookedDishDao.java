package ua.goit.jdbc;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.CookedDish;
import ua.goit.interfaces.CookedDishDao;

import java.util.List;

public class HCookedDishDao implements CookedDishDao {


    private SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(HCookedDishDao.class);

    @Override
    @Transactional
    public void addCookedDish(CookedDish dish) {

        LOGGER.info("Connecting to database. Running method is addCookedDish");
        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public List<CookedDish> getAllCookedDishes() {

        LOGGER.info("Connecting to database. Running method is getAllCookedDishes");
        return sessionFactory.getCurrentSession().createQuery("select e from CookedDish e").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}