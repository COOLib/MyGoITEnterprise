package ua.goit.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Ingredient;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by COOLib on 29.05.2016.
 */
public class JdbcIngredientDao implements ua.goit.interfaces.IngredientDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDao.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Ingredient findIngredientByName(String name) {

        Ingredient ingredient;
        LOGGER.info("Connecting to database. Running method is findIngredientByName");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingridient WHERE name = ?")) {

            LOGGER.info("Successfully connected to DB");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("Result set is got");
            if (resultSet.next()) {

                ingredient = createIngredient(resultSet);
                LOGGER.info("Ingredient has been found by name" + name);

            } else {
                LOGGER.error("Cannot find ingredient with name " + name);
                throw new RuntimeException("Cannot find ingredient with name " + name);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
        return ingredient;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addIngredient(Ingredient ingredient) {

        boolean exist = false;

        try {
            Ingredient existIngredient = findIngredientByName(ingredient.getName());
            exist = true;
        } catch (RuntimeException e) {
            LOGGER.error("Cannot find ingredient with name " + ingredient.getName());
        }
        LOGGER.info("Connecting to database. Running method is addIngredient");

        if (!exist) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO ingridient (name) VALUES (?)")) {

                LOGGER.info("Successfully connected to DB");

                statement.setString(1, ingredient.getName());
                statement.executeUpdate();

                LOGGER.info("New ingredient has been added");
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while connecting to DB ", e);
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.error("Such ingredient is already exists " + ingredient.getName());
        }
    }

    private Ingredient createIngredient(ResultSet set) throws SQLException {

        Ingredient ingredient = new Ingredient();

        ingredient.setId(set.getInt("id"));
        ingredient.setName(set.getString("name"));
        return ingredient;
    }
}
