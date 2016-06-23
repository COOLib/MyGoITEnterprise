package ua.goit.controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Ingredient;
import ua.goit.interfaces.IngredientDao;

public class HIngredientController {

    private IngredientDao ingredientDao;

    @Transactional
    public Ingredient getByName(String name) {

        return ingredientDao.findIngredientByName(name);
    }

    @Transactional
    public void addIngredient(String name) {

        Ingredient ingredient = new Ingredient();

        ingredient.setName(name);
        ingredientDao.addIngredient(ingredient);
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}