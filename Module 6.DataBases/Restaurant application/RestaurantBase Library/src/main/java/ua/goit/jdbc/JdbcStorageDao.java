package ua.goit.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Ingredient;
import ua.goit.essences.Storage;
import ua.goit.interfaces.IngredientDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStorageDao implements ua.goit.interfaces.StorageDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStorageDao.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addIngredientToStorage(Ingredient ingredient, Storage storage) {

        LOGGER.info("Connecting to database. Running method is addIngredientToStorage");

        IngredientDao ingredientDao = new JdbcIngredientDao();
        Ingredient addedIngredient = ingredientDao.findIngredientByName(ingredient.getName());

        if (addedIngredient != null) {

            updateQuantity(ingredient, storage.getQuantity());
        } else {

            ingredientDao.addIngredient(ingredient);

            try (Connection connection = dataSource.getConnection()) {

                PreparedStatement statement1 = connection.prepareStatement("INSERT INTO storage VALUES (?, ?)");

                statement1.setInt(1, storage.getId());
                statement1.setInt(2, storage.getQuantity());
                statement1.setString(3, storage.getIngredientName());
                statement1.executeUpdate();

                LOGGER.info("New ingredient with name " + ingredient.getName() + " is added to storage");
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while connecting to DB ", e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeIngredientFromStorage(String name) {

        LOGGER.info("Connecting to database.Running method is removeIngredient");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM storage WHERE id = (select ingridient.id from ingridient where ingridient.name = ? )")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, name);
            statement.executeUpdate();

            LOGGER.info("Ingredient with name " + name + " is deleted from storage");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Storage> getAllIngredients() {

        String sql = "SELECT storage.ingridient_id, storage.quantity, ingridient.name FROM storage, ingridient WHERE storage.ingridient_id = ingridient.id";
        LOGGER.info("Connecting to database.Running method is getIngredients");

        return getIngredients(sql);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Storage> getAllEndingIngredients() {

        String sql = "SELECT storage.ingridient_id, storage.quantity, ingridient.name FROM storage, ingridient WHERE storage.ingridient_id = ingridient.id and quantity < 10";
        LOGGER.info("Connecting to database.Running method is getAllEndingIngredients");

        return getIngredients(sql);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private List<Storage> getIngredients(String query) {

        List<Storage> storageList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Successfully connected to DB.");
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                Storage storage = createStorage(set);
                storageList.add(storage);
            }

            LOGGER.info("List of ingredients has been received");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return storageList;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Storage findIngredientByName(String name) {

        LOGGER.info("Connecting to database.Running method is findIngredientByName");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT storage.ingridient_id, storage.quantity, ingridient.name FROM storage, ingridient WHERE ingridient.name = ?")) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Storage storage = createStorage(resultSet);
                LOGGER.info("Storage has been found by name" + name);
                return storage;
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
    public void updateQuantity(Ingredient ingredient, int quantity) {

        LOGGER.info("Connecting to database.Running method is updateQuantity");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE storage SET quantity = quantity + ? where ingridient_id = (select id from ingridient where name = ?)")) {

            LOGGER.info("Successfully connected to DB");

            statement.setInt(1, quantity);
            statement.setString(2, ingredient.getName());
            statement.executeUpdate();

            LOGGER.info("Quantity of ingredient with name " + ingredient.getName() + " has been changed");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    private Storage createStorage(ResultSet set) throws SQLException {

        Storage storage = new Storage();

        storage.setId(set.getInt("ingridient_id"));
        storage.setQuantity(set.getInt("quantity"));
        storage.setIngredientName(set.getString("name"));
        return storage;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
