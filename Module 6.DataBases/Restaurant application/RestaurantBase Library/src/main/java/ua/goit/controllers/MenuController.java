package ua.goit.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Menu;
import ua.goit.interfaces.MenuDao;

import java.util.List;

/**
 * Created by COOLib on 31.05.2016.
 */
public class MenuController {

    private PlatformTransactionManager txManager;
    private MenuDao menuDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Menu> getAllMenus() {

        return menuDao.getAllMenus();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Menu getMenuByName(String name) {

        return menuDao.findMenuByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addMenu(Menu menu) {

        menuDao.addMenu(menu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeMenu(String name) {

        menuDao.removeMenu(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeDishFromMenu(Menu menu, String name) {

        menuDao.removeDishFromMenu(menu, name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDishToMenu(Menu menu, String name) {

        menuDao.addDishToMenu(menu, name);
    }


    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }
}
