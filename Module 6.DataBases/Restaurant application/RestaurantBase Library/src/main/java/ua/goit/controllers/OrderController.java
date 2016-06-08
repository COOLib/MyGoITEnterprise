package ua.goit.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Order;
import ua.goit.interfaces.OrderDao;

import java.util.List;

/**
 * Created by COOLib on 01.06.2016.
 */
public class OrderController {

    private PlatformTransactionManager txManager;
    private OrderDao orderDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void createNewOrder(Order order) {

        orderDao.createNewOrder(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeOrder(int id) {

        orderDao.removeOrder(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDishFromOrder(String name, int orderNumber) {

        orderDao.deleteDishFromOrder(name, orderNumber);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDishToOrder(String name, int orderNumber) {

        orderDao.addDishToOrder(orderNumber, name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void turnToClosed(int id) {

        orderDao.turnToClosed(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> getAllOpenedOrders() {

        return orderDao.getAllOpenedOrders();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> getAllClosedOrders() {

        return orderDao.getAllClosedOrders();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order getOrderById(int id) {

        return orderDao.findOrderById(id);
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
