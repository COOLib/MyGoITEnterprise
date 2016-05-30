package ua.goit.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Ingredient;
import ua.goit.essences.Storage;
import ua.goit.interfaces.StorageDao;

import java.util.List;

/**
 * Created by COOLib on 30.05.2016.
 */
public class StorageController {

    private PlatformTransactionManager txManager;
    private StorageDao storageDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addIngredientToStorage(Ingredient ingredient, Storage storage) {

        storageDao.addIngredientToStorage(ingredient, storage);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeIngredientFromStorage(String name) {

        storageDao.removeIngredientFromStorage(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Storage> getAllIngredients() {

        return storageDao.getAllIngredients();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Storage> getAllEndingIngredients() {

        return storageDao.getAllEndingIngredients();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Storage getIngredientByName(String name) {

        return storageDao.findIngredientByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateQuantity(Ingredient ingredient, int quantity) {

        storageDao.updateQuantity(ingredient,quantity);
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }
}
