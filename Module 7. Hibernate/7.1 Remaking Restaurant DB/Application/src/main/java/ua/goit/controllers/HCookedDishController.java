package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.CookedDish;
import ua.goit.interfaces.CookedDishDao;
import ua.goit.interfaces.DishDao;
import ua.goit.interfaces.EmployeeDao;
import ua.goit.interfaces.OrderDao;

import java.util.Date;
import java.util.List;

public class HCookedDishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HCookedDishController.class);

    private CookedDishDao cookedDishDao;
    private EmployeeDao employeeDao;
    private DishDao dishDao;
    private OrderDao orderDao;

    @Transactional
    public void addCookedDish(String employeeName, String dishName, int orderNumber) {

        CookedDish cookedDish = new CookedDish();

        cookedDish.setEmployeeId(employeeDao.findEmployeeByName(employeeName).getId());
        cookedDish.setId(dishDao.findDishByName(dishName).getId());
        cookedDish.setDate(new Date());

        if (orderDao.findOrderById(orderNumber).getDishes().contains(dishDao.findDishByName(dishName))) {
            cookedDish.setOrderNumber(orderNumber);
        } else {
            String s = "Order " + orderNumber + " not contains the dish with name " + dishName +
                    ". This dish cannot be added to the list of the cooked dishes.";

            LOGGER.error(s);
            throw new RuntimeException(s);
        }

        cookedDishDao.addCookedDish(cookedDish);
    }

    @Transactional
    public List<CookedDish> getAllCookedDishes() {

        return cookedDishDao.getAllCookedDishes();
    }

    public void setCookedDishDao(CookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}