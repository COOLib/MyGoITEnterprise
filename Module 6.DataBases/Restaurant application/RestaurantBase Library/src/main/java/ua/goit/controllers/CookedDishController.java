package ua.goit.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.CookedDish;
import ua.goit.interfaces.CookedDishDao;

import java.util.List;

/**
 * Created by COOLib on 30.05.2016.
 */
public class CookedDishController {

    private PlatformTransactionManager txManager;
    private CookedDishDao cookedDish;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(CookedDish dish) {

        cookedDish.addCookedDish(dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CookedDish> getAllCookedDishes() {

        return cookedDish.getAllCookedDishes();
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }
}
