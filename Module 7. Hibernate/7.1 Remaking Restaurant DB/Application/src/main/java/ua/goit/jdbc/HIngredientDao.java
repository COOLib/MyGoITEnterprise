package ua.goit.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Ingredient;
import ua.goit.interfaces.IngredientDao;

public class HIngredientDao implements IngredientDao {

    private SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(HIngredientDao.class);

    @Override
    @Transactional
    public Ingredient findIngredientByName(String name) {

        LOGGER.info("Connecting to database. Running method is findIngredientByName");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Ingredient e where e.name=:name");
        query.setParameter("name", name);
        Ingredient ingredient = (Ingredient) query.uniqueResult();

        if (ingredient == null) {
            LOGGER.error("Cannot find ingredient with name " + name);
            throw new RuntimeException("Cannot find ingredient with name " + name);
        }
        return ingredient;
    }

    @Override
    @Transactional
    public void addIngredient(Ingredient ingredient) {

        LOGGER.info("Connecting to database. Running method is addIngredient");
        sessionFactory.getCurrentSession().save(ingredient);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}