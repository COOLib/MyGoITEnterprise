package ua.goit.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by COOLib on 29.05.2016.
 */
public class JdbcOrderDao implements ua.goit.interfaces.OrderDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDao.class);


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void createNewOrder(Order order) {

        LOGGER.info("Connecting to database. Running method is addOrder");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO dish_order (employee, table_number, date, is_closed) VALUES (?, ?, ?, 'opened')")) {

            LOGGER.info("Successfully connected to DB");

            statement.setInt(1, order.getEmployee());
            statement.setInt(2, order.getTableNumber());
            statement.setDate(3, new Date(order.getDate().getTime()));
            statement.executeUpdate();

            LOGGER.info("New order has been added");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeOrder(int id) {

        LOGGER.info("Connecting to database.Running method is removeOrder");

        Order order = findOrderById(id);

        if (order.isClosed().equals("opened")) {

            deleteAllDishesFromOrder(order, id);

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM dish_order WHERE order_number = ? and is_closed = 'opened'")) {

                LOGGER.info("Successfully connected to DB");

                statement.setInt(1, id);
                statement.executeUpdate();

                LOGGER.info("Order with number " + id + " has been deleted");
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while connecting to DB ", e);
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.warn("Order with number " + id + " cannot be deleted, because it is already closed");
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteDishFromOrder(String name, int orderNumber) {

        Order order = findOrderById(orderNumber);

        if (order.isClosed().equals("opened")) {

            LOGGER.info("Connecting to database.Running method is deleteDishesFromOrder");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM dish_order_dish WHERE dish_name = ? AND order_number = (select order_number from dish_order where order_number = ?)")) {

                LOGGER.info("Successfully connected to DB");

                statement.setString(1, name);
                statement.setInt(2, orderNumber);
                statement.executeUpdate();

                LOGGER.info("Dish with name " + name + " from order with id " + orderNumber + " has been deleted");
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while connecting to DB ", e);
                throw new RuntimeException(e);
            }
        } else {

            LOGGER.warn("Dish with name " + name + " from order with number " + orderNumber + " cannot be deleted, because the order is already closed");
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDishToOrder(String name, int orderNumber) {

        Order order = findOrderById(orderNumber);

        if (order.isClosed().equals("opened")) {

            LOGGER.info("Connecting to database.Running method is addDishToOrder");

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO dish_order_dish VALUES (?, ?)")) {

                LOGGER.info("Successfully connected to DB");

                statement.setInt(1, orderNumber);
                statement.setString(2, name);
                statement.executeUpdate();

                LOGGER.info("Dish with name " + name + " has been added to order with id " + orderNumber);
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while connecting to DB ", e);
                throw new RuntimeException(e);
            }
        } else {

            LOGGER.warn("Dish with name " + name + " cannot be added to order with number " + orderNumber + ", because the order is already closed");
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void turnToClosed(int id) {

        Order order = findOrderById(id);

        LOGGER.info("Connecting to database.Running method is turnToClosed");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE dish_order SET is_closed = 'closed' WHERE order_number = ?")) {

            LOGGER.info("Successfully connected to DB");

            statement.setInt(1, id);
            statement.executeUpdate();

            LOGGER.info("Order with number " + id + "has been closed");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getAllOpefedOrders() {

        String sql = "SELECT * FROM dish_order where is_closed = 'opened'";

        return getOrders(sql);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> getAllClosedOrders() {

        String sql = "SELECT * FROM dish_order where is_closed = 'closed'";

        return getOrders(sql);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private Order findOrderById(int id) {

        LOGGER.info("Connecting to database. Running method is findOrderById");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM dish_order WHERE order_number = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Order order = createOrder(resultSet);
                LOGGER.info("Order has been found by id " + id);
                return order;
            } else {
                LOGGER.error("Cannot find order with id " + id);
                throw new RuntimeException("Cannot find order with id " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private List<Order> getOrders(String sql) {

        LOGGER.info("Connecting to database.Running method is getAllOpenedOrders");

        List<Order> orderList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Successfully connected to DB.");
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                Order order = createOrder(set);
                orderList.add(order);
            }

            LOGGER.info("List of employees has been received");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return orderList;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private void deleteAllDishesFromOrder(Order order, int orderId) {

        LOGGER.info("Connecting to database.Running method is deleteDishesFromOrder");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM dish_order_dish WHERE order_number = (select order_number from dish_order where order_number = ?)")) {

            LOGGER.info("Successfully connected to DB");

            statement.setInt(1, orderId);
            statement.executeUpdate();

            LOGGER.info("All dishes from order with id " + orderId + " have been deleted");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    private Order createOrder(ResultSet set) throws SQLException {

        Order order = new Order();

        order.setNumber(set.getInt("order_number"));
        order.setEmployee(set.getInt("employee"));
        order.setTableNumber(set.getInt("table_number"));
        order.setDate(set.getDate("date"));
        order.setClosed(set.getString("is_closed"));
        return order;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
