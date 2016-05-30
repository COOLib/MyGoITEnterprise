package ua.goit.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ua.goit.essences.Employee;
import ua.goit.interfaces.EmployeeDao;

import java.util.List;

/**
 * Created by COOLib on 27.05.2016.
 */
public class EmployeeController {

    private PlatformTransactionManager txManager;
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {

        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));

        try {
            List<Employee> result = employeeDao.getAllEmployees();
            txManager.commit(status);
            return result;
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee getEmployeeByName(String name) {

        return employeeDao.findEmployeeByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addEmployee(Employee employee) {

        employeeDao.addEmployee(employee);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeEmployee(String name) {

        employeeDao.removeEmployee(name);
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
