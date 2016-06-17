package ua.goit.controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Employee;
import ua.goit.domains.Position;
import ua.goit.interfaces.EmployeeDao;

import java.util.Date;
import java.util.List;

/**
 * Created by COOLib on 12.06.2016.
 */
public class HEmployeeController {

    private EmployeeDao employeeDao;

    @Transactional
    public void addEmployee(String name, String surname, Date birth, String phone, Position position, int salary) {

        Employee employee = new Employee();

        employee.setName(name);
        employee.setSecondName(surname);
        employee.setBirthDate(birth);
        employee.setPhone(phone);
        employee.setPosition(position);
        employee.setSalary(salary);

        employeeDao.addEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(String name) {

        employeeDao.removeEmployee(name);
    }

    @Transactional
    public Employee getByName(String name) {

        return employeeDao.findEmployeeByName(name);
    }

    @Transactional
    public List<Employee> getAllEmployees() {

        return employeeDao.getAllEmployees();
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


}
