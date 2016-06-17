package ua.goit.controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Ingredient;
import ua.goit.interfaces.IngredientDao;

/**
 * Created by COOLib on 13.06.2016.
 */
public class HIngredientController {

    private IngredientDao ingredientDao;

    @Transactional
    public Ingredient getByName(String name) {

        return ingredientDao.findIngredientByName(name);
    }

    @Transactional
    public void addIngredient(String name) {

        Ingredient ingredient = ingredientDao.findIngredientByName(name);

        if (ingredient == null) {

            Ingredient ingredient1 = new Ingredient();
            ingredient.setName(name);

            ingredientDao.addIngredient(ingredient1);
        }
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}
