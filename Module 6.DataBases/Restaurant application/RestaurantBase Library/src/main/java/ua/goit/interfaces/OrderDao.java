package ua.goit.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Order;

import java.util.List;

/**
 * Created by COOLib on 01.06.2016.
 */
public interface OrderDao {
    @Transactional(propagation = Propagation.REQUIRED)
    void createNewOrder(Order order);

    @Transactional(propagation = Propagation.REQUIRED)
    void removeOrder(int id);

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteDishFromOrder(String name, int orderNumber);

    @Transactional(propagation = Propagation.REQUIRED)
    void addDishToOrder(String name, int orderNumber);

    @Transactional(propagation = Propagation.REQUIRED)
    void turnToClosed(int id);

    @Transactional(propagation = Propagation.REQUIRED)
    List<Order> getAllOpefedOrders();

    @Transactional(propagation = Propagation.REQUIRED)
    List<Order> getAllClosedOrders();
}
