package ua.goit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.controllers.*;
import ua.goit.domains.*;
import ua.goit.domains.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Imagine {

    private HEmployeeController employeeController;
    private HDishController dishController;
    private HOrderController orderController;
    private HCookedDishController cookedDishController;
    private HIngredientController ingredientController;
    private HMenuController menuController;
    private HStorageController storageController;

    private static final Logger LOGGER = LoggerFactory.getLogger(Imagine.class);
    private PlatformTransactionManager txManager;

    private JPanel back;
    private JPanel actionPanel;

    private JFrame frame;

    private Box radioBox = new Box(BoxLayout.Y_AXIS);
    private Box actionBox = new Box(BoxLayout.Y_AXIS);

    private JTextArea textArea;

    private ButtonGroup buttonGroup;
    private ButtonGroup actionGroup;

    private JRadioButton employee;
    private JRadioButton menu;
    private JRadioButton dish;
    private JRadioButton order;
    private JRadioButton cookedDish;
    private JRadioButton storage;

    private JRadioButton add;
    private JRadioButton delete;
    private JRadioButton showAll;
    private JRadioButton find;
    private JRadioButton addDishToMenu;
    private JRadioButton deleteDishFromMenu;
    private JRadioButton changeQuantity;
    private JRadioButton showEnding;
    private JRadioButton addDishToOrder;
    private JRadioButton deleteDishFromOrder;
    private JRadioButton turnToClose;
    private JRadioButton showOpened;
    private JRadioButton showClosed;
    private JScrollPane scrollPane;

    public void setUpWindow() {

        LOGGER.info("Creation of the main frame and filling it...");

        frame = new JFrame("Restaurant");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        back = new JPanel(layout);

        back.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 125));
        back.setBackground(Color.LIGHT_GRAY);

        Box buttonBox = getButtonBox();

        setRadioBoxComponents();
        back.add(BorderLayout.SOUTH, buttonBox);
        back.add(BorderLayout.CENTER, radioBox);
        textArea = new JTextArea();

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        frame.getContentPane().add(back);
        frame.setBounds(600, 350, 1100, 300);
        frame.setVisible(true);

        setActionPanel();
    }

    private Box getButtonBox() {

        Box buttonBox = new Box(BoxLayout.X_AXIS);
        JButton clear = new JButton("Clear fields");
        clear.addActionListener(new ClearListener());

        buttonBox.add(clear);

        return buttonBox;
    }

    private void setRadioBoxComponents() {

        LOGGER.info("Creation of the first box of radiobuttons");

        buttonGroup = new ButtonGroup();

        employee = new JRadioButton("Employee");
        employee.addActionListener(new EmployeeListener());

        menu = new JRadioButton("Menu");
        menu.addActionListener(new MenuListener());

        dish = new JRadioButton("Dish");
        dish.addActionListener(new DishListener());

        order = new JRadioButton("Order");
        order.addActionListener(new OrderListener());

        cookedDish = new JRadioButton("CookedDish");
        cookedDish.addActionListener(new CookedDishListener());

        storage = new JRadioButton("Storage");
        storage.addActionListener(new StorageListener());

        buttonGroup.add(employee);
        buttonGroup.add(menu);
        buttonGroup.add(dish);
        buttonGroup.add(order);
        buttonGroup.add(cookedDish);
        buttonGroup.add(storage);

        radioBox.add(employee);
        radioBox.add(menu);
        radioBox.add(dish);
        radioBox.add(order);
        radioBox.add(cookedDish);
        radioBox.add(storage);
    }

    private void setActionPanel() {

        LOGGER.info("Creation of the second box of radiobuttons");

        BorderLayout layout = new BorderLayout();

        actionPanel = new JPanel(layout);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 125, 10, 10));
        actionPanel.setBackground(Color.LIGHT_GRAY);

        actionGroup = new ButtonGroup();

        add = new JRadioButton("add");
        add.addActionListener(new AddListener());

        delete = new JRadioButton("delete");
        delete.addActionListener(new DeleteListener());

        showAll = new JRadioButton("show all");
        showAll.addActionListener(new ShowAllListener());

        find = new JRadioButton("find");
        find.addActionListener(new FindListener());

        addDishToMenu = new JRadioButton("add dish to menu");
        addDishToMenu.addActionListener(new AddToMenuListener());

        deleteDishFromMenu = new JRadioButton("delete dish from menu");
        deleteDishFromMenu.addActionListener(new DeleteFromMenuListener());

        changeQuantity = new JRadioButton("change ingredient's quantity");
        changeQuantity.addActionListener(new ChangeQuantityListener());

        showEnding = new JRadioButton("show ending ingredients");
        showEnding.addActionListener(new EndingListener());

        addDishToOrder = new JRadioButton("add dish to order");
        addDishToOrder.addActionListener(new AddToOrderListener());

        deleteDishFromOrder = new JRadioButton("delete dish from order");
        deleteDishFromOrder.addActionListener(new DeleteFromOrderListener());

        turnToClose = new JRadioButton("change order status to \"closed\"");
        turnToClose.addActionListener(new TurnToCloseListener());

        showOpened = new JRadioButton("show all opened orders");
        showOpened.addActionListener(new ShowOpenedListener());

        showClosed = new JRadioButton("show all closed orders");
        showClosed.addActionListener(new ShowClosedListener());

        actionGroup.add(add);
        actionGroup.add(delete);
        actionGroup.add(showAll);
        actionGroup.add(find);
        actionGroup.add(addDishToMenu);
        actionGroup.add(deleteDishFromMenu);
        actionGroup.add(changeQuantity);
        actionGroup.add(showEnding);
        actionGroup.add(addDishToOrder);
        actionGroup.add(deleteDishFromOrder);
        actionGroup.add(turnToClose);
        actionGroup.add(showOpened);
        actionGroup.add(showClosed);

        actionBox.add(add);
        actionBox.add(delete);
        actionBox.add(showAll);
        actionBox.add(find);
        actionBox.add(addDishToMenu);
        actionBox.add(deleteDishFromMenu);
        actionBox.add(changeQuantity);
        actionBox.add(showEnding);
        actionBox.add(addDishToOrder);
        actionBox.add(deleteDishFromOrder);
        actionBox.add(turnToClose);
        actionBox.add(showOpened);
        actionBox.add(showClosed);

        actionPanel.add(BorderLayout.WEST, actionBox);
        actionPanel.add(BorderLayout.CENTER, scrollPane);

        frame.getContentPane().add(actionPanel);
        actionPanel.setVisible(false);
    }

    public class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("Button \"clear\" is selected");

            int i = radioBox.getComponentCount();
            int j = actionBox.getComponentCount();

            for (int k = 0; k < i; k++) {
                radioBox.remove(0);
            }

            for (int k = 0; k < j; k++) {
                actionBox.remove(0);
            }

            textArea.setText("");

            buttonGroup = new ButtonGroup();
            actionGroup = new ButtonGroup();


            for (int k = 0; k < radioBox.getComponentCount(); k++) {
                radioBox.getComponent(k).setVisible(true);
            }

            setRadioBoxComponents();
            actionPanel.repaint();
            setActionPanel();
        }
    }

    public class EmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Employee\" is selected");

            radioBox.getComponent(1).setVisible(false);
            radioBox.getComponent(2).setVisible(false);
            radioBox.getComponent(3).setVisible(false);
            radioBox.getComponent(4).setVisible(false);
            radioBox.getComponent(5).setVisible(false);

            actionBox.getComponent(4).setVisible(false);
            actionBox.getComponent(5).setVisible(false);
            actionBox.getComponent(6).setVisible(false);
            actionBox.getComponent(7).setVisible(false);
            actionBox.getComponent(8).setVisible(false);
            actionBox.getComponent(9).setVisible(false);
            actionBox.getComponent(10).setVisible(false);
            actionBox.getComponent(11).setVisible(false);
            actionBox.getComponent(12).setVisible(false);
            actionPanel.setVisible(true);
        }
    }

    public class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Menu\" is selected");

            radioBox.getComponent(0).setVisible(false);
            radioBox.getComponent(2).setVisible(false);
            radioBox.getComponent(3).setVisible(false);
            radioBox.getComponent(4).setVisible(false);
            radioBox.getComponent(5).setVisible(false);

            actionBox.getComponent(6).setVisible(false);
            actionBox.getComponent(7).setVisible(false);
            actionBox.getComponent(8).setVisible(false);
            actionBox.getComponent(9).setVisible(false);
            actionBox.getComponent(10).setVisible(false);
            actionBox.getComponent(11).setVisible(false);
            actionBox.getComponent(12).setVisible(false);
            actionPanel.setVisible(true);
        }
    }

    public class DishListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Dish\" is selected");

            radioBox.getComponent(0).setVisible(false);
            radioBox.getComponent(1).setVisible(false);
            radioBox.getComponent(3).setVisible(false);
            radioBox.getComponent(4).setVisible(false);
            radioBox.getComponent(5).setVisible(false);

            actionBox.getComponent(4).setVisible(false);
            actionBox.getComponent(5).setVisible(false);
            actionBox.getComponent(6).setVisible(false);
            actionBox.getComponent(7).setVisible(false);
            actionBox.getComponent(8).setVisible(false);
            actionBox.getComponent(9).setVisible(false);
            actionBox.getComponent(10).setVisible(false);
            actionBox.getComponent(11).setVisible(false);
            actionBox.getComponent(12).setVisible(false);
            actionPanel.setVisible(true);
        }
    }

    public class OrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Order\" is selected");

            radioBox.getComponent(0).setVisible(false);
            radioBox.getComponent(1).setVisible(false);
            radioBox.getComponent(2).setVisible(false);
            radioBox.getComponent(4).setVisible(false);
            radioBox.getComponent(5).setVisible(false);

            actionBox.getComponent(2).setVisible(false);
            actionBox.getComponent(4).setVisible(false);
            actionBox.getComponent(5).setVisible(false);
            actionBox.getComponent(6).setVisible(false);
            actionBox.getComponent(7).setVisible(false);

            actionPanel.setVisible(true);
        }
    }

    public class CookedDishListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Cooked dish\" is selected");

            radioBox.getComponent(1).setVisible(false);
            radioBox.getComponent(2).setVisible(false);
            radioBox.getComponent(3).setVisible(false);
            radioBox.getComponent(0).setVisible(false);
            radioBox.getComponent(5).setVisible(false);

            actionBox.getComponent(1).setVisible(false);
            actionBox.getComponent(3).setVisible(false);
            actionBox.getComponent(4).setVisible(false);
            actionBox.getComponent(5).setVisible(false);
            actionBox.getComponent(6).setVisible(false);
            actionBox.getComponent(7).setVisible(false);
            actionBox.getComponent(8).setVisible(false);
            actionBox.getComponent(9).setVisible(false);
            actionBox.getComponent(10).setVisible(false);
            actionBox.getComponent(11).setVisible(false);
            actionBox.getComponent(12).setVisible(false);
            actionPanel.setVisible(true);
        }
    }

    public class StorageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Storage\" is selected");

            radioBox.getComponent(1).setVisible(false);
            radioBox.getComponent(2).setVisible(false);
            radioBox.getComponent(3).setVisible(false);
            radioBox.getComponent(0).setVisible(false);
            radioBox.getComponent(4).setVisible(false);

            actionBox.getComponent(4).setVisible(false);
            actionBox.getComponent(5).setVisible(false);
            actionBox.getComponent(8).setVisible(false);
            actionBox.getComponent(9).setVisible(false);
            actionBox.getComponent(10).setVisible(false);
            actionBox.getComponent(11).setVisible(false);
            actionBox.getComponent(12).setVisible(false);
            actionPanel.setVisible(true);
        }
    }

    public class ShowAllListener implements ActionListener {

        @Override
        @Transactional
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Show all\" is selected");

            textArea.setText("");
            if (employee.isSelected()) {

                for (ua.goit.domains.Employee employee : employeeController.getAllEmployees()) {
                    textArea.append(employee.toString());
                    textArea.append("\n");
                }
            } else if (dish.isSelected()) {

                for (ua.goit.domains.Dish dish : dishController.getAllDishes()) {
                    textArea.append(dish.toString());
                    textArea.append("\n");
                }
            } else if (menu.isSelected()) {

                for (Menu menu : menuController.getAllMenus()) {
                    textArea.append(menu.toString());
                    textArea.append("\n");
                }
            } else if (cookedDish.isSelected()) {

                for (ua.goit.domains.CookedDish cookedDish : cookedDishController.getAllCookedDishes()) {
                    textArea.append(cookedDish.toString());
                    textArea.append("\n");
                }
            } else if (storage.isSelected()) {

                for (ua.goit.domains.Storage storage : storageController.getAllIngredients()) {
                    textArea.append(storage.toString());
                    textArea.append("\n");
                }
            }

        }
    }

    public class FindListener implements ActionListener {

        @Override
        @Transactional
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Find\" is selected");

            textArea.setText("");
            if (employee.isSelected()) {

                String employeeName = JOptionPane.showInputDialog("Please, enter name of the employee");
                textArea.append(employeeController.getByName(employeeName).toString());
            } else if (dish.isSelected()) {

                String dishName = JOptionPane.showInputDialog("Please, enter name of the dish");
                textArea.append(dishController.getByName(dishName).toString());
            } else if (menu.isSelected()) {

                String menuName = JOptionPane.showInputDialog("Please, enter name of the menu");
                textArea.append(menuController.getByName(menuName).toString());

            } else if (storage.isSelected()) {

                String ingredientName = JOptionPane.showInputDialog("Please, enter name of the ingredient");
                textArea.append(storageController.getByIngredientName(ingredientName).toString());
            } else if (order.isSelected()) {

                int k;
                while (true) {
                    try {
                        k = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the number of the order"));
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "You have to enter the number of an exist order");
                    }
                }

                textArea.append(String.valueOf(orderController.getById(k)));
            }
        }
    }

    public class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Add\" is selected");

            textArea.setText("");

            if (employee.isSelected()) {

                String name = JOptionPane.showInputDialog("Please, enter name of the employee");

                String secondName = JOptionPane.showInputDialog("Please, enter second name of the employee");

                Date birth;

                while (true) {
                    String birthday = JOptionPane.showInputDialog(frame, "Please, enter date of birth of the employee");
                    SimpleDateFormat format = new SimpleDateFormat();
                    format.applyPattern("dd.MM.yyyy");

                    try {
                        birth = format.parse(birthday);
                        break;
                    } catch (ParseException e1) {
                        JOptionPane.showMessageDialog(frame, "You entered wrong date format. Please, enter date at format: dd.MM.yyyy");
                    }
                }

                String phone = JOptionPane.showInputDialog("Please, enter phone of the employee");

                Object[] o = {"COOK", "WAITER", "MANAGER"};

                Position position;

                label:
                while (true) {

                    String s = (String) JOptionPane.showInputDialog(
                            frame,
                            "Choose the employee position",
                            "Position Dialog",
                            JOptionPane.PLAIN_MESSAGE, null,
                            o,
                            "COOK");

                    switch (s) {
                        case "COOK":
                            position = Position.COOK;
                            break label;
                        case "WAITER":
                            position = Position.WAITER;
                            break label;
                        case "MANAGER":
                            position = Position.MANAGER;
                            break label;
                        default:
                            JOptionPane.showMessageDialog(frame, "You have to choose one of presenting positions for new employee");
                            break;
                    }
                }

                int salary;

                while (true) {
                    try {
                        salary = Integer.parseInt(JOptionPane.showInputDialog("Please, enter salary of the employee"));
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please, enter a natural number");
                    }
                }
                employeeController.addEmployee(name, secondName, birth, phone, position, salary);

                Employee newEmployee = new Employee();

                newEmployee.setName(name);
                newEmployee.setSecondName(secondName);
                newEmployee.setBirthDate(birth);
                newEmployee.setPosition(position);
                newEmployee.setPhone(phone);
                newEmployee.setSalary(salary);

                textArea.append("New employee has been added");
                textArea.append("\n");
                textArea.append(newEmployee.toString());

            } else if (dish.isSelected()) {

                String dishName = JOptionPane.showInputDialog("Please, enter name of the dish");
                Category category = categoryCreation();

                int price;
                int weight;

                while (true) {
                    try {
                        price = Integer.parseInt(JOptionPane.showInputDialog("Please, enter price of the dish"));
                        weight = Integer.parseInt(JOptionPane.showInputDialog("Please, enter weight of the dish"));
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please, enter a natural numbers to price and weight fields");
                    }
                }
                List<Ingredient> ingredients = new ArrayList<>();

                while (true) {

                    String ingredientName = JOptionPane.showInputDialog(frame, "Please, enter the name of ingredient for the new dish");

                    if (ingredientName == null) {
                        break;
                    }
                    Ingredient ingredient = ingredientController.getByName(ingredientName);
                    ingredients.add(ingredient);
                }

                dishController.addDish(dishName, category, price, weight, ingredients);

                Dish newDish = new Dish();

                newDish.setName(dishName);
                newDish.setCategory(category);
                newDish.setPrice(price);
                newDish.setWeight(weight);
                newDish.setIngredient(ingredients);

                textArea.append("New dish has been added to the dish list");
                textArea.append("\n");
                textArea.append(newDish.toString());

            } else if (menu.isSelected()) {

                Menu newMenu = new Menu();

                newMenu.setName(JOptionPane.showInputDialog("Please, enter name of the menu"));

                /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                newMenu.setDishName(JOptionPane.showInputDialog("Please, enter name of the dish"));
                newMenu.setCategory(JOptionPane.showInputDialog("Please, enter name of the menu's category"));
*/
                menuController.addMenu(newMenu);

                textArea.append("New menu has been added");
                textArea.append("\n");
                textArea.append(newMenu.toString());

            } else if (storage.isSelected()) {

                ua.goit.domains.Ingredient newIngredient = new ua.goit.domains.Ingredient();

                String name = JOptionPane.showInputDialog("Please, enter name of the ingredient");
                newIngredient.setName(name);

                ua.goit.domains.Storage newStorage = new ua.goit.domains.Storage();

                while (true) {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Please, enter id of the ingredient"));
                        newStorage.setId(id);
                        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please, enter quantity of ingredients"));
                        newStorage.setQuantity(quantity);
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please, enter a natural numbers to id and quantity fields");
                    }
                }


                /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                newStorage.setIngredientName(name);

                storageController.addIngredientToStorage(newIngredient, newStorage);
*/
                textArea.append("New ingredient has been added");
                textArea.append("\n");
                textArea.append(newIngredient.toString());

            } else if (cookedDish.isSelected()) {

                ua.goit.domains.CookedDish newCookedDish = new ua.goit.domains.CookedDish();

                newCookedDish.setName(JOptionPane.showInputDialog("Please, enter name of the dish"));
                newCookedDish.setCategory(JOptionPane.showInputDialog("Please, enter category of the cooked dish"));

                while (true) {

                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Please, enter id of the employee cooked this dish"));
                        newCookedDish.setEmployeeId(id);
                        int orderNum = Integer.parseInt(JOptionPane.showInputDialog("Please, enter number of the order for which this dish was cooked"));
                        newCookedDish.setOrderNumber(orderNum);
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please, enter a natural numbers to id of the employee and number of the order fields");
                    }
                }

                String birthday = JOptionPane.showInputDialog("Please, enter date of adding dish to order");
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");
                Date dishToOrder = null;

                try {
                    dishToOrder = format.parse(birthday);
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(frame, "You entered wrong date format. Please, enter date at format: dd.MM.yyyy");
                }

                newCookedDish.setDate(dishToOrder);

                cookedDishController.addDish(newCookedDish);

                textArea.append("New cooked dish has been added");
                textArea.append("\n");
                textArea.append(newCookedDish.toString());

            } else if (order.isSelected()) {

                ua.goit.domains.Order newOrder = new ua.goit.domains.Order();

                while (true) {

                    try {
                        int employee = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the id of the employee which brought this order"));
                        newOrder.setEmployee(employee);
                        int table = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the number of the table"));
                        newOrder.setTableNumber(table);
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please, enter a natural numbers id of the employee and number of the table fields");
                    }
                }

                String orderDate = JOptionPane.showInputDialog("Please, enter date of adding of this order");
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");
                Date dishOrder = null;

                try {
                    dishOrder = format.parse(orderDate);
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(frame, "You entered wrong date format. Please, enter date at format: dd.MM.yyyy");
                }

                newOrder.setDate(dishOrder);
                newOrder.setClosed(JOptionPane.showInputDialog("Please, enter the message \"opened\" to add this order to open orders"));

                orderController.createNewOrder(newOrder);

                textArea.append("New order has been created");
                textArea.append("\n");
                textArea.append(newOrder.toString());
            }
        }
    }

    private Category categoryCreation() {

        LOGGER.info("New dish category creation");

        Object[] o = {"FIRST_DISH", "SECOND_DISH", "SALAD", "DESSERT", "SNACK"};
        Category category = null;

        while (true) {

            String s = (String) JOptionPane.showInputDialog(
                    frame,
                    "Please, chose the category of the new dish",
                    "Category Dialog",
                    JOptionPane.PLAIN_MESSAGE, null,
                    o,
                    "FIRST_DISH");

            if (s.equals("FIRST_DISH")) {
                category = Category.FIRST_DISH;
                break;
            } else if (s.equals("SECOND_DISH")) {
                category = Category.SECOND_DISH;
                break;
            } else if (s.equals("SALAD")) {
                category = Category.SALAD;
                break;
            } else if (s.equals("DESSERT")) {
                category = Category.DESSERT;
            } else if (s.equals("SNACK")) {
                category = Category.SNACK;
            } else {
                JOptionPane.showMessageDialog(frame, "You have to choose one of presenting categories for new dish");
            }
        }
        return category;
    }



        while (true) {
            try {
                int price = Integer.parseInt(JOptionPane.showInputDialog("Please, enter price of the dish"));
                newDish.setPrice(price);
                int weight = Integer.parseInt(JOptionPane.showInputDialog("Please, enter weight of the dish"));
                newDish.setWeight(weight);
                break;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Please, enter a natural numbers to price and weight fields");
            }
        }

        dishController.addDish(newDish);

        textArea.append("New dish has been added to the dish list");
        textArea.append("\n");
        textArea.append(newDish.toString());


    public class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Delete\" is selected");

            textArea.setText("");

            if (employee.isSelected()) {

                String name = JOptionPane.showInputDialog("Please, enter the name of the employee you want to remove from the DB");
                employeeController.removeEmployee(name);

                textArea.append("Employee with name " + name + " has been removed from DB");

            } else if (menu.isSelected()) {

                String name = JOptionPane.showInputDialog("Please, enter the name of the menu you want to remove from the DB");
                menuController.removeMenu(name);

                textArea.append("Menu with name " + name + " has been removed from DB");

            } else if (dish.isSelected()) {

                String name = JOptionPane.showInputDialog("Please, enter the name of the dish you want to remove from the DB");
                dishController.removeDish(name);

                textArea.append("Dish with name " + name + " has been removed from DB");

            } else if (order.isSelected()) {

                while (true) {

                    try {
                        int number = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the number of the order you want to remove from the DB"));
                        orderController.removeOrder(number);

                        textArea.append("Order with number " + number + " has been removed from DB");
                        break;

                    } catch (Exception ex) {
                        JOptionPane.showInputDialog(frame, "Please, enter a natural number number of the order which you want to delete from DB");
                    }
                }

            } else if (storage.isSelected()) {

                String name = JOptionPane.showInputDialog("Please, enter the name of the ingredient you want to remove from the DB");
                menuController.removeMenu(name);

                textArea.append("Menu with name " + name + " has been removed from DB");
            }
        }
    }

    public class AddToMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Add to menu\" is selected");

            textArea.setText("");

            String menuName = JOptionPane.showInputDialog("Please, enter the name of the menu in which you want to insert a dish");
            Menu newMenu = menuController.getMenuForAddingDish(menuName);

            String dishName = JOptionPane.showInputDialog("Please, enter the name of rhe dish which you want to insert at the menu " + menuName);

            ua.goit.domains.Dish dish = dishController.getDishByName(dishName);

            if (dish != null) {

                menuController.addDishToMenu(newMenu, dishName, dish.getCategory());
            } else {

                dishCreation();
                menuController.addDishToMenu(newMenu, dishName, dish.getCategory());
            }

            textArea.append("New dish with name " + dishName + " has been added to the menu " + menuName);
        }
    }

    public class DeleteFromMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Delete from menu\" is selected");

            textArea.setText("");

            String menuName = JOptionPane.showInputDialog("Please, enter the name of the menu from which you want to delete a dish");
            Menu newMenu = menuController.getMenuForAddingDish(menuName);

            String dishName = JOptionPane.showInputDialog("Please, enter the name of the dish which you want to remove from the menu " + menuName);

            menuController.removeDishFromMenu(newMenu, dishName);

            textArea.append("Dish with name " + dishName + " has been removed from menu " + menuName);
        }
    }

    public class ChangeQuantityListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Change quantity\" is selected");

            textArea.setText("");

            String ingredientName = JOptionPane.showInputDialog("Please, enter the name of the ingredient you want to change quantity");
            int quantity;

            ua.goit.domains.Ingredient newIngredient = ingredientController.getInredientByName(ingredientName);
            ua.goit.domains.Storage newStorage = storageController.getIngredientByName(ingredientName);

            while (true) {
                try {
                    quantity = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the number which you want to add or subtract from the amount the ingredient " + ingredientName));

                    if (newStorage.getQuantity() + quantity < 0) {

                        JOptionPane.showMessageDialog(frame, "The amount of the ingredient " + ingredientName + " can't be less than zero");
                    } else {
                        break;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "You have to enter a number to the quantity field");
                }
            }

            storageController.updateQuantity(newIngredient, quantity);
            textArea.append("Quantity of the ingredient " + ingredientName + " was updated");
        }
    }

    public class EndingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Show ending ingredients\" is selected");

            textArea.setText("");

            for (ua.goit.domains.Storage storage : storageController.getAllEndingIngredients()) {
                textArea.append(storage.toString());
                textArea.append("\n");
            }
        }
    }

    public class AddToOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Add dish to order\" is selected");

            textArea.setText("");

            int orderNum;
            while (true) {
                try {
                    orderNum = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the number of the order in which you want to add some dish"));
                    break;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Please, enter number of exist open order");
                }
            }
            ua.goit.domains.Order newOrder = orderController.getOrderById(orderNum);
            String dishNameToOrder = JOptionPane.showInputDialog("Please, enter the name of the dish you want to add to the order");
            orderController.addDishToOrder(dishNameToOrder, newOrder.getNumber());

            textArea.append("Dish " + dishNameToOrder + " has been added to order " + orderNum);
        }
    }

    public class DeleteFromOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Delete dish from order\" is selected");

            textArea.setText("");

            String dishNameFromOrder = JOptionPane.showInputDialog("Please, enter the name of the dish you want to delete from the order");

            int orderNum;
            while (true) {
                try {
                    orderNum = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the number of the order"));
                    break;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Please, enter number of exist open order");
                }
            }
            orderController.deleteDishFromOrder(dishNameFromOrder, orderNum);

            textArea.append("Dish " + dishNameFromOrder + " has been deleted from the order " + orderNum);
        }
    }

    public class TurnToCloseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Turn order to close state\" is selected");

            textArea.setText("");

            int orderNum;
            while (true) {
                try {
                    orderNum = Integer.parseInt(JOptionPane.showInputDialog("Please, enter the number of the order you want to close"));
                    break;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Please, enter number of exist open order");
                }
            }

            orderController.turnToClosed(orderNum);
        }
    }

    public class ShowOpenedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Show all opened orders\" is selected");

            textArea.setText("");

            for (ua.goit.domains.Order order : orderController.getAllOpenedOrders()) {
                textArea.append(order.toString());
                textArea.append("\n");
            }
        }
    }

    public class ShowClosedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOGGER.info("RadioButton \"Show all closed orders\" is selected");

            textArea.setText("");

            for (ua.goit.domains.Order order : orderController.getAllClosedOrders()) {
                textArea.append(order.toString());
                textArea.append("\n");
            }
        }
    }

    public void setEmployeeController(HEmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishController(HDishController dishController) {
        this.dishController = dishController;
    }

    public void setOrderController(HOrderController orderController) {
        this.orderController = orderController;
    }

    public void setCookedDishController(HCookedDishController cookedDishController) {
        this.cookedDishController = cookedDishController;
    }

    public void setIngredientController(HIngredientController ingredientController) {
        this.ingredientController = ingredientController;
    }

    public void setMenuController(HMenuController menuController) {
        this.menuController = menuController;
    }

    public void setStorageController(HStorageController storageController) {
        this.storageController = storageController;

    }
}