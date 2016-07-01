package ua.goit.interfaces;

import ua.goit.domains.Cook;
import ua.goit.domains.Employee;
import ua.goit.domains.Waiter;

import java.util.List;


public interface EmployeeDao {

    void addEmployee(Employee employee);

    void addWaiter(Waiter waiter);

    void addCook(Cook cook);

    void removeEmployee(String name);

    Employee findEmployeeByName(String name);

    List<Employee> getAllEmployees();
}