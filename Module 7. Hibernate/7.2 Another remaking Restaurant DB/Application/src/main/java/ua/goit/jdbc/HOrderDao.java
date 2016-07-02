package ua.goit.jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

        if (order.getIsClosed().equals("opened")) {

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

        if (order.getIsClosed().equals("opened")) {

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

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Orders> allOpened = session.createCriteria(Orders.class)
                .add(Restrictions.like("isClosed", "opened"))
                .list();
        return allOpened;
    }

    @Override
    @Transactional
    public List<Orders> getAllClosedOrders() {

        LOGGER.info("Connecting to database. Running method is getAllClosedOrders");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Orders> allClosed = session.createCriteria(Orders.class)
                .add(Restrictions.like("isClosed", "closed"))
                .list();
        return allClosed;

    }

    @Override
    @Transactional
    public Orders findOrderById(Integer id) {

        LOGGER.info("Connecting to database. Running method is findOrderById");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Orders.class)
                .add(Restrictions.eq("number", id));

        Orders order = (Orders) criteria.uniqueResult();

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