package ua.goit.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Menu;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by COOLib on 29.05.2016.
 */
public class JdbcMenuDao implements ua.goit.interfaces.MenuDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDao.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addMenu(Menu menu) {

        LOGGER.info("Connecting to database. Running method is addMenu");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO restraunt_menu (name, dish_name, category) VALUES (?, ?, ?)")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, menu.getName());
            statement.setString(2, menu.getDishName());
            statement.setString(3, menu.getCategory());
            statement.executeUpdate();

            LOGGER.info("New menu has been added");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeMenu(String name) {

        LOGGER.info("Connecting to database.Running method is removeMenu");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM restraunt_menu WHERE name = ?")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, name);
            statement.executeUpdate();

            LOGGER.info("Menu with name " + name + " has been deleted");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Menu findMenuByName(String name) {

        LOGGER.info("Connecting to database.Running method is findMenuByName");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM restraunt_menu WHERE name = ?")) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Menu menu = createMenu(resultSet);
                LOGGER.info("Menu has been found by name" + name);
                return menu;
            } else {

                LOGGER.error("Cannot find menu with name " + name);
                throw new RuntimeException("Cannot find menu with name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Menu> getAllMenus() {

        LOGGER.info("Connecting to database.Running method is getAllMenus");

        List<Menu> menuList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {


            LOGGER.info("Successfully connected to DB.");

            String sql = "SELECT DISTINCT name FROM restraunt_menu";
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                Menu menu = createMenu(set);
                menuList.add(menu);
            }

            LOGGER.info("List of menus has been received");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return menuList;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDishToMenu(Menu menu, String dishName) {

        LOGGER.info("Connecting to database. Running method is addDishToMenu");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO restraunt_menu (name, dish_name, category) VALUES (?, ?, ?)") ) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, menu.getName());
            statement.setString(2, dishName);
            statement.setString(3, menu.getCategory());
            statement.executeUpdate();

            LOGGER.info("New dish has been added to menu" + menu.getName());
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeDishFromMenu(Menu menu, String dishName) {

        LOGGER.info("Connecting to database.Running method is removeDishFromMenu");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM restraunt_menu WHERE dish_name = ? and name = ?")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, dishName);
            statement.setString(2, menu.getName());
            statement.executeUpdate();

            LOGGER.info("Dish with name " + dishName + " has been deleted from menu " + menu.getName());
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    private Menu createMenu(ResultSet set) throws SQLException {

        Menu menu = new Menu();

        menu.setName(set.getString("name"));
        menu.setDishName(set.getString("dish_name"));
        menu.setCategory(set.getString("category"));

        return menu;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
