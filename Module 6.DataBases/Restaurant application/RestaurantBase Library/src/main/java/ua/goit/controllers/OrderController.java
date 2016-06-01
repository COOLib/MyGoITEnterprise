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
    void createNewOrder(Order order) {

        orderDao.createNewOrder(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void removeOrder(int id) {

        orderDao.removeOrder(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteDishFromOrder(String name, int orderNumber) {

        orderDao.deleteDishFromOrder(name, orderNumber);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void addDishToOrder(String name, int orderNumber) {

        orderDao.addDishToOrder(name, orderNumber);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void turnToClosed(int id) {

        orderDao.turnToClosed(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    List<Order> getAllOpefedOrders() {

        return orderDao.getAllOpefedOrders();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    List<Order> getAllClosedOrders() {

        return orderDao.getAllClosedOrders();
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
