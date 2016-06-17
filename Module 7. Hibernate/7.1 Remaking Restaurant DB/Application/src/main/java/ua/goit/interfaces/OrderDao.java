package ua.goit.interfaces;

import ua.goit.domains.Orders;

import java.util.List;

/**
 * Created by COOLib on 01.06.2016.
 */
public interface OrderDao {

    void createNewOrder(Orders order);

    void removeOrder(int id);

    void deleteDishFromOrder(String name, int orderNumber);

    void addDishToOrder(int orderNumber, String name);

    void turnToClosed(int id);

    List<Orders> getAllOpenedOrders();

    List<Orders> getAllClosedOrders();

    Orders findOrderById(Integer id);
}
