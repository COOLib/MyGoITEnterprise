package ua.goit.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Dish;

import java.util.List;

/**
 * Created by COOLib on 29.05.2016.
 */
public interface DishDao {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addDish(Dish dish);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void removeDish(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Dish findDishByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<Dish> getAllDishes();
}
