package ua.goit.controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.*;
import ua.goit.interfaces.EmployeeDao;

import java.util.Date;
import java.util.List;

public class HEmployeeController {

    private EmployeeDao employeeDao;

    @Transactional
    public void addEmployee(String name, String surname, Date birth, String phone, int salary) {

        Employee employee = new Employee();

        employee.setName(name);
        employee.setSecondName(surname);
        employee.setBirthDate(birth);
        employee.setPhone(phone);
        employee.setSalary(salary);

        employeeDao.addEmployee(employee);
    }

    @Transactional
    public void addWaiter(String name, String surname, Date birth, String phone, int salary, Position position) {

        Waiter waiter = new Waiter();

        waiter.setName(name);
        waiter.setSecondName(surname);
        waiter.setBirthDate(birth);
        waiter.setPhone(phone);
        waiter.setSalary(salary);
        waiter.setPosition(position);

        employeeDao.addWaiter(waiter);
    }

    @Transactional
    public void addCook(String name, String surname, Date birth, String phone, int salary, Position position) {

        Cook cook = new Cook();

        cook.setName(name);
        cook.setSecondName(surname);
        cook.setBirthDate(birth);
        cook.setPhone(phone);
        cook.setSalary(salary);
        cook.setPosition(position);

        employeeDao.addCook(cook);
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