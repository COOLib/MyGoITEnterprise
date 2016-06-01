package ua.goit.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Dish;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDishDao implements ua.goit.interfaces.DishDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcDishDao.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDish(Dish dish) {

        LOGGER.info("Connecting to database. Running method is addDish");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO dish (name, category, price, weight) VALUES (?, ?, ?, ?)")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, dish.getName());
            statement.setString(2, dish.getCategory());
            statement.setInt(3, dish.getPrice());
            statement.setInt(4, dish.getWeight());
            statement.executeUpdate();

            LOGGER.info("New dish has been added");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeDish(String name) {

        LOGGER.info("Connecting to database.Running method is removeDish");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM dish WHERE name = ?")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, name);
            statement.executeUpdate();

            LOGGER.info("Dish with name " + name + " has been deleted");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Dish findDishByName(String name) {

        LOGGER.info("Connecting to database.Running method is findDishByName");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM dish WHERE name = ?")) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Dish dish = createDish(resultSet);
                LOGGER.info("Dish has been found by name" + name);
                return dish;
            } else {

                LOGGER.error("Cannot find dish with name " + name);
                throw new RuntimeException("Cannot find dish with name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Dish> getAllDishes() {

        LOGGER.info("Connecting to database.Running method is getAllDishes");

        List<Dish> dishList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {


            LOGGER.info("Successfully connected to DB.");

            String sql = "select * from employee";
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                Dish dish = createDish(set);
                dishList.add(dish);
            }

            LOGGER.info("List of dishes has been received");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return dishList;
    }

    private Dish createDish(ResultSet set) throws SQLException {

        Dish dish = new Dish();

        dish.setName(set.getString("name"));
        dish.setCategory(set.getString("category"));
        dish.setPrice(set.getInt("price"));
        dish.setWeight(set.getInt("weight"));
        return dish;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
