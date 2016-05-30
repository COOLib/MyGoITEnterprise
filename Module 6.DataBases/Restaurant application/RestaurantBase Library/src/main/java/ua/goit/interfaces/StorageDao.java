package ua.goit.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Ingredient;
import ua.goit.essences.Storage;

import java.util.List;

/**
 * Created by COOLib on 30.05.2016.
 */
public interface StorageDao {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addIngredientToStorage(Ingredient ingredient, Storage storage);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void removeIngredientFromStorage(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<Storage> getAllIngredients();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<Storage> getAllEndingIngredients();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Storage findIngredientByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateQuantity(Ingredient ingredient, int quantity);
}
