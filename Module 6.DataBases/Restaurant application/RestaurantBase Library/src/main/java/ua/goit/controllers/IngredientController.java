package ua.goit.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Ingredient;
import ua.goit.interfaces.IngredientDao;

/**
 * Created by COOLib on 30.05.2016.
 */
public class IngredientController {

    private PlatformTransactionManager txManager;
    private IngredientDao ingredientDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Ingredient getInredientByName(String name) {

       return ingredientDao.findIngredientByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addIngredient(Ingredient ingredient) {

        ingredientDao.addIngredient(ingredient);
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}
