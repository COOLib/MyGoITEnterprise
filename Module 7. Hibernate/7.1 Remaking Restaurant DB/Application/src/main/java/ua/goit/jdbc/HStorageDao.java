package ua.goit.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Ingredient;
import ua.goit.domains.Storage;
import ua.goit.interfaces.IngredientDao;
import ua.goit.interfaces.StorageDao;

import java.util.List;

public class HStorageDao implements StorageDao {

    private SessionFactory sessionFactory;
    private IngredientDao ingredientDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(HStorageDao.class);

    @Override
    @Transactional
    public void addIngredientToStorage(Ingredient ingredient, int quantity) {

        LOGGER.info("Connecting to database. Running method is addIngredientToStorage");

        Storage storage = new Storage();

        storage.setIngredientId(ingredient.getId());
        storage.setQuantity(quantity);
        sessionFactory.getCurrentSession().save(storage);
    }

    @Override
    @Transactional
    public void removeIngredientFromStorage(String name) {

        LOGGER.info("Connecting to database. Running method is removeIngredientFromStorage");

        Ingredient ingredient = ingredientDao.findIngredientByName(name);
        Storage storage = findStorageByIngredientId(ingredient.getId());

        sessionFactory.getCurrentSession().delete(storage);
    }

    @Override
    @Transactional
    public List<Storage> getAllIngredients() {

        LOGGER.info("Connecting to database. Running method is getAllIngredients");
        return sessionFactory.getCurrentSession().createQuery("select e from Storage e").list();
    }

    @Override
    @Transactional
    public List<Storage> getAllEndingIngredients() {

        LOGGER.info("Connecting to database. Running method is getAllEndingIngredients");
        return sessionFactory.getCurrentSession().createQuery("select e from Storage e where quantity <= 10").list();
    }

    @Override
    @Transactional
    public Storage findIngredientByName(String name) {

        LOGGER.info("Connecting to database. Running method is findIngredientByName");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Storage e where e.id=" +
                "(select o.id from Ingredient o where o.name=:name)");
        query.setParameter("name", name);

        Storage storage = (Storage) query.uniqueResult();

        if (storage == null) {
            LOGGER.error("Cannot find ingredient with name " + name + " at storage");
            throw new RuntimeException("Cannot find ingredient with name " + name + " at storage");

        }

        return storage;
    }

    @Transactional
    private Storage findStorageByIngredientId(int id) {

        LOGGER.info("Connecting to database. Running method is findStorageById");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Storage e where e.ingredientId=:id");
        query.setParameter("id", id);
        Storage storage = (Storage) query.uniqueResult();

        if (storage == null) {

            LOGGER.error("Cannot find storage element with id " + id);
            throw new RuntimeException("Cannot find storage element with id " + id);
        }

        return storage;
    }

    @Override
    @Transactional
    public void updateQuantity(String ingredientName, int quantity) {

        LOGGER.info("Connecting to database. Running method is updateQuantity");

        Storage storage = findIngredientByName(ingredientName);
        int realQuantity = storage.getQuantity();
        storage.setQuantity(realQuantity + quantity);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}