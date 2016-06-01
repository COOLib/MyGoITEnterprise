package ua.goit.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Dish;
import ua.goit.interfaces.DishDao;

import java.util.List;

public class DishController {

    private PlatformTransactionManager txManager;
    private DishDao dishDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> getAllDishes() {

        return dishDao.getAllDishes();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Dish getDishByName(String name) {

        return dishDao.findDishByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(Dish dish) {

        dishDao.addDish(dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeDish(String name) {

        dishDao.removeDish(name);
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
