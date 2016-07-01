package ua.goit.controllers;

import ua.goit.domains.Ingredient;
import ua.goit.domains.Storage;
import ua.goit.interfaces.StorageDao;

import java.util.List;

public class HStorageController {

    private StorageDao storageDao;
    private HIngredientController ingredientController;

    public void addIngredientToStorage(String name, int quantity) {

        ingredientController.addIngredient(name);
        Ingredient ingredient = ingredientController.getByName(name);
        storageDao.addIngredientToStorage(ingredient, quantity);
    }

    public void removeIngredientFromStorage(String name) {

        storageDao.removeIngredientFromStorage(name);
    }

    public List<Storage> getAllIngredients() {

        return storageDao.getAllIngredients();
    }

    public List<Storage> getEndingIngredients() {

        return storageDao.getAllEndingIngredients();
    }

    public Storage getByIngredientName(String name) {

        return storageDao.findIngredientByName(name);
    }

    public void updateQuantity(String ingredientName, int addingAmount) {

        storageDao.updateQuantity(ingredientName, addingAmount);
    }

    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    public void setIngredientController(HIngredientController ingredientController) {
        this.ingredientController = ingredientController;
    }
}