package ua.goit.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Ingredient;
import ua.goit.domains.Storage;
import ua.goit.interfaces.StorageDao;

import java.util.List;

public class HStorageDao implements StorageDao {

    private SessionFactory sessionFactory;
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
        Storage storage = findIngredientByName(name);

        int quantity = storage.getQuantity() * (-1);
        updateQuantity(name, quantity);
    }

    @Override
    @Transactional
    public List getAllIngredients() {

        LOGGER.info("Connecting to database. Running method is getAllIngredients");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        return session.createCriteria(Storage.class).list();
    }

    @Override
    @Transactional
    public List<Storage> getAllEndingIngredients() {

        LOGGER.info("Connecting to database. Running method is getAllEndingIngredients");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        return session.createCriteria(Storage.class)
                .add(Restrictions.le("quantity", 10))
                .list();
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

    @Override
    @Transactional
    public void updateQuantity(String ingredientName, int quantity) {

        LOGGER.info("Connecting to database. Running method is updateQuantity");

        Storage storage = findIngredientByName(ingredientName);
        int realQuantity = storage.getQuantity();

        if (realQuantity + quantity >= 0) {
            storage.setQuantity(realQuantity + quantity);
        } else {

            LOGGER.error("Cannot update quantity at storage, because it will be less than zero.");
            throw new RuntimeException("Cannot update quantity at storage, because it will be less than zero.");
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}