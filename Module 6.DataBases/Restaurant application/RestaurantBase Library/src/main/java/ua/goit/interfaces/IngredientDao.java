package ua.goit.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Ingredient;

/**
 * Created by COOLib on 30.05.2016.
 */
public interface IngredientDao {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Ingredient findIngredientByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addIngredient(Ingredient ingredient);
}
