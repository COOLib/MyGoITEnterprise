package ua.goit.controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Dish;
import ua.goit.domains.Orders;
import ua.goit.interfaces.DishDao;
import ua.goit.interfaces.EmployeeDao;
import ua.goit.interfaces.OrderDao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by COOLib on 13.06.2016.
 */
public class HOrderController {

    private OrderDao orderDao;
    private EmployeeDao employeeDao;
    private DishDao dishDao;

    @Transactional
    public void addOrder(String waiterName, List<String> dishes, int tableNumber) {

        Orders order = new Orders();
        order.setWaiter(employeeDao.findEmployeeByName(waiterName));
        order.setDishes(createDishes(dishes));
        order.setTableNumber(tableNumber);
        order.setDate(new Date());
        order.setClosed("opened");

        orderDao.createNewOrder(order);
    }

    private List<Dish> createDishes(List<String> dishes) {

        List<Dish> result = dishes.stream().map(dishName -> dishDao.findDishByName(dishName)).collect(Collectors.toList());

        return result;
    }

    @Transactional
    public void deleteOrder(int id) {

        orderDao.removeOrder(id);
    }

    @Transactional
    public void turnToClosed(int id) {

        orderDao.turnToClosed(id);
    }

    @Transactional
    public Orders getById(int id) {

        return orderDao.findOrderById(id);
    }

    @Transactional
    public List<Orders> getAllClosed() {

        return orderDao.getAllClosedOrders();
    }

    @Transactional
    public List<Orders> getAllOpened() {

        return orderDao.getAllOpenedOrders();
    }

    @Transactional

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
