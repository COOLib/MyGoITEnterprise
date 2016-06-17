package ua.goit.jdbc;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Ingredient;
import ua.goit.interfaces.IngredientDao;

public class HIngredientDao implements IngredientDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Ingredient findIngredientByName(String name) {

        Ingredient ingredient = sessionFactory.getCurrentSession().get(Ingredient.class, name);

        if (ingredient == null) {
            throw new RuntimeException("Cannot find ingredient with name " + name);
        }
        return ingredient;
    }

    @Override
    @Transactional
    public void addIngredient(Ingredient ingredient) {

        sessionFactory.getCurrentSession().save(ingredient);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}