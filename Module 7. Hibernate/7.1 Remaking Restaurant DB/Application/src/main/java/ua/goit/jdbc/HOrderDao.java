package ua.goit.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.domains.Orders;
import ua.goit.interfaces.DishDao;
import ua.goit.interfaces.OrderDao;

import java.util.List;

public class HOrderDao implements OrderDao {

    private SessionFactory sessionFactory;
    private DishDao dishDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(HOrderDao.class);


    @Override
    @Transactional
    public void createNewOrder(Orders order) {

        LOGGER.info("Connecting to database. Running method is createNewOrder");
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    @Transactional
    public void removeOrder(int id) {

        LOGGER.info("Connecting to database. Running method is removeOrder");
        Orders order = findOrderById(id);
        sessionFactory.getCurrentSession().delete(order);
    }

    @Override
    @Transactional
    public void addDishToOrder(int orderNumber, String name) {

        LOGGER.info("Connecting to database. Running method is addDishToOrder");

        Orders order = findOrderById(orderNumber);
        Dish dish = dishDao.findDishByName(name);

        if (order.getIsClosed() == "opened") {

            order.getDishes().add(dish);
        } else {
            throw new RuntimeException("The order " + orderNumber + " is already closed");
        }
    }

    @Override
    @Transactional
    public void deleteDishFromOrder(String name, int orderNumber) {

        LOGGER.info("Connecting to database. Running method is deleteDishFromOrder");

        Orders order = findOrderById(orderNumber);
        Dish dish = dishDao.findDishByName(name);

        if (order.getIsClosed() == "opened") {

            order.getDishes().remove(dish);
        } else {

            LOGGER.error("The order " + orderNumber + " is already closed");
            throw new RuntimeException("The order " + orderNumber + " is already closed");
        }
    }

    @Override
    @Transactional
    public void turnToClosed(int id) {

        LOGGER.info("Connecting to database. Running method is turnToClosed");
        Orders order = findOrderById(id);
        order.setClosed("closed");

    }

    @Override
    @Transactional
    public List<Orders> getAllOpenedOrders() {

        LOGGER.info("Connecting to database. Running method is getAllOpenedOrders");
        return sessionFactory.getCurrentSession().createQuery("select e from Orders e where isClosed = \'opened\'").list();
    }

    @Override
    @Transactional
    public List<Orders> getAllClosedOrders() {

        LOGGER.info("Connecting to database. Running method is getAllClosedOrders");
        return sessionFactory.getCurrentSession().createQuery("select e from Orders e where isClosed = \'closed\'").list();
    }

    @Override
    @Transactional
    public Orders findOrderById(Integer id) {

        LOGGER.info("Connecting to database. Running method is findOrderById");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Orders e where e.id=:id");
        query.setParameter("id", id);
        Orders order = (Orders) query.uniqueResult();

        if (id == null) {

            LOGGER.error("Cannot find order with name " + id);
            throw new RuntimeException("Cannot find order with name " + id);
        }
        return order;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}