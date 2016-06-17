package ua.goit.jdbc;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.domains.Orders;
import ua.goit.interfaces.DishDao;
import ua.goit.interfaces.OrderDao;

import java.util.List;

public class HOrderDao implements OrderDao {

    private SessionFactory sessionFactory;
    private DishDao dishDao;

    @Override
    @Transactional
    public void createNewOrder(Orders order) {

        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    @Transactional
    public void removeOrder(int id) {

        Orders order = findOrderById(id);

        sessionFactory.getCurrentSession().delete(order);
    }

    @Override
    @Transactional
    public void addDishToOrder(int orderNumber, String name) {

        Orders order = findOrderById(orderNumber);
        Dish dish = dishDao.findDishByName(name);

        order.getDishes().add(dish);
    }

    @Override
    @Transactional
    public void deleteDishFromOrder(String name, int orderNumber) {

        Orders order = findOrderById(orderNumber);
        Dish dish = dishDao.findDishByName(name);

        order.getDishes().remove(dish);
    }

    @Override
    @Transactional
    public void turnToClosed(int id) {

        Orders order = findOrderById(id);

        order.setClosed("closed");

    }

    @Override
    @Transactional
    public List<Orders> getAllOpenedOrders() {

        return sessionFactory.getCurrentSession().createQuery("select e from Orders e where isClosed = \"opened\"").list();
    }

    @Override
    @Transactional
    public List<Orders> getAllClosedOrders() {

        return sessionFactory.getCurrentSession().createQuery("select e from Orders e where isClosed = \"closed\"").list();
    }

    @Override
    @Transactional
    public Orders findOrderById(Integer id) {

        Orders order = sessionFactory.getCurrentSession().get(Orders.class, id);

        if (id == null) {
            throw new RuntimeException("Cannot find order with name " + id);
        }
        return order;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}