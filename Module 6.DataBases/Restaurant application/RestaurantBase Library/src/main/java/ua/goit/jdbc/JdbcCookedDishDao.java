package ua.goit.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.CookedDish;
import ua.goit.interfaces.CookedDishDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCookedDishDao implements CookedDishDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcCookedDishDao.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addCookedDish(CookedDish dish) {

        LOGGER.info("Connecting to database. Running method is addDish");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO cooked_dish VALUES (?, ?, ?, ?, ?, ?)")) {

            LOGGER.info("Successfully connected to DB");

            statement.setInt(1, dish.getNumber());
            statement.setString(2, dish.getName());
            statement.setString(3, dish.getCategory());
            statement.setInt(4, dish.getEmployeeId());
            statement.setInt(5, dish.getOrderNumber());
            statement.setDate(6, new Date(dish.getDate().getTime()));
            statement.executeUpdate();

            LOGGER.info("New cooked dish is added");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<CookedDish> getAllCookedDishes() {

        LOGGER.info("Connecting to database.Running method is getAllCookedDishes");

        List<CookedDish> cookedDishes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {


            LOGGER.info("Successfully connected to DB.");

            String sql = "select * from cooked_dish";
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                CookedDish cookedDish = createCookedDish(set);
                cookedDishes.add(cookedDish);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return cookedDishes;
    }

    private CookedDish createCookedDish(ResultSet set) throws SQLException {

        CookedDish dish = new CookedDish();

        dish.setNumber(set.getInt("dish_number"));
        dish.setName(set.getString("dish_name"));
        dish.setCategory(set.getString("category"));
        dish.setEmployeeId(set.getInt("employee_id"));
        dish.setOrderNumber(set.getInt("order_number"));
        dish.setDate(set.getDate("data"));
        return dish;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
