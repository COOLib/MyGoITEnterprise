package ua.goit.jdbc;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.CookedDish;
import ua.goit.interfaces.CookedDishDao;

import java.util.List;

public class HCookedDishDao implements CookedDishDao {


    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addCookedDish(CookedDish dish) {

        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public List<CookedDish> getAllCookedDishes() {

        return sessionFactory.getCurrentSession().createQuery("select e from CookedDish e").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
