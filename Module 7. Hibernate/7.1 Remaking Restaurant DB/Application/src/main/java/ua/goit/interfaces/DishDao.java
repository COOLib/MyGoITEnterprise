package ua.goit.interfaces;

import ua.goit.domains.Dish;

import java.util.List;

/**
 * Created by COOLib on 29.05.2016.
 */
public interface DishDao {

    void addDish(Dish dish);

    void removeDish(String name);

    Dish findDishByName(String name);

    List<Dish> getAllDishes();
}
