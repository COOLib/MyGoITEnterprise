package ua.goit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.controllers.EmployeeController;

/**
 * Created by COOLib on 24.05.2016.
 */
public class Main {

    private EmployeeController employeeController;

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Main main = context.getBean(Main.class);
        main.start();
    }

    private void start() {
        employeeController.getAllEmployees().forEach(System.out::println);

        System.out.println();
        System.out.println(employeeController.getEmployeeByName("George"));
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }
}
