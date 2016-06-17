package ua.goit.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.domains.Ingredient;
import ua.goit.domains.Storage;
import ua.goit.interfaces.IngredientDao;
import ua.goit.interfaces.StorageDao;

import java.util.List;

public class HStorageDao implements StorageDao {

    private SessionFactory sessionFactory;
    private IngredientDao ingredientDao;

    @Override
    public void addIngredientToStorage(Ingredient ingredient, int quantity) {

        Storage storage = new Storage();

        storage.setId(ingredient.getId());
        storage.setQuantity(quantity);
        sessionFactory.getCurrentSession().save(storage);
    }

    @Override
    public void removeIngredientFromStorage(String name) {

        Ingredient ingredient = ingredientDao.findIngredientByName(name);
        Storage storage = findStorageById(ingredient.getId());

        sessionFactory.getCurrentSession().delete(storage);
    }

    @Override
    public List<Storage> getAllIngredients() {

        return sessionFactory.getCurrentSession().createQuery("select e from Storage e").list();
    }

    @Override
    public List<Storage> getAllEndingIngredients() {

        return sessionFactory.getCurrentSession().createQuery("select e from Storage e where quantity <= 10").list();
    }

    @Override
    public Storage findIngredientByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Storage e where e.id = " +
                "(select o.id from ingredient o where o.name = name)");
        query.setParameter("name", name);

        Storage storage = (Storage) query.uniqueResult();

        if (storage == null) {
            throw new RuntimeException("Cannot find ingredient with name " + name + " at storage");
        }

        return storage;
    }

    private Storage findStorageById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Storage e where e.id = id");
        query.setParameter("id", id);

        Storage storage = (Storage) query.uniqueResult();

        if (storage == null) {
            throw new RuntimeException("Cannot find storage element with id " + id);
        }

        return storage;
    }

    @Override
    public void updateQuantity(String ingredientName, int quantity) {

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
