package ua.goit.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Employee;

import java.util.List;


public interface EmployeeDao {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addEmployee(Employee employee);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void removeEmployee(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Employee findEmployeeByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<Employee> getAllEmployees();
}
