package ua.goit.interfaces;

import ua.goit.domains.Employee;

import java.util.List;


public interface EmployeeDao {

    void addEmployee(Employee employee);

    void removeEmployee(String name);

    Employee findEmployeeByName(String name);

    List<Employee> getAllEmployees();
}
