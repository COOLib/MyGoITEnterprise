package ua.goit.interfaces;

import ua.goit.domains.Ingredient;
import ua.goit.domains.Storage;

import java.util.List;

/**
 * Created by COOLib on 30.05.2016.
 */
public interface StorageDao {

    void addIngredientToStorage(Ingredient ingredient, int quantity);

    void removeIngredientFromStorage(String name);

    List getAllIngredients();

    List getAllEndingIngredients();

    Storage findIngredientByName(String name);

    void updateQuantity(String ingredientName, int quantity);
}