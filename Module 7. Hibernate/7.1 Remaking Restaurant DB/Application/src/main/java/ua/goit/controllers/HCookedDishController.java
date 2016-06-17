package ua.goit.controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.CookedDish;
import ua.goit.interfaces.CookedDishDao;

import java.util.List;

/**
 * Created by COOLib on 13.06.2016.
 */
public class HCookedDishController {

    private CookedDishDao cookedDishDao;

    @Transactional
    public void addCookedDish(CookedDish cookedDish) {

        cookedDishDao.addCookedDish(cookedDish);
    }

    @Transactional
    public List<CookedDish> getAllCookedDishes() {

        return cookedDishDao.getAllCookedDishes();
    }

    public void setCookedDishDao(CookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }
}
