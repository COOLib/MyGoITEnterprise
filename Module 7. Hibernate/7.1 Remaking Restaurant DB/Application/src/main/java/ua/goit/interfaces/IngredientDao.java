package ua.goit.interfaces;

import ua.goit.domains.Ingredient;

/**
 * Created by COOLib on 30.05.2016.
 */
public interface IngredientDao {

    Ingredient findIngredientByName(String name);

    void addIngredient(Ingredient ingredient);
}
