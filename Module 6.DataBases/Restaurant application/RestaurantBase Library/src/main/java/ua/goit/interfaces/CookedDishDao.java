package ua.goit.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.CookedDish;

import java.util.List;

/**
 * Created by COOLib on 29.05.2016.
 */
public interface CookedDishDao {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addCookedDish(CookedDish dish);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<CookedDish> getAllCookedDishes();
}
