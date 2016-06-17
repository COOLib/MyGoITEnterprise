package ua.goit.jdbc;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domains.Employee;
import ua.goit.interfaces.EmployeeDao;

import java.util.List;

public class HEmployeeDao implements EmployeeDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addEmployee(Employee employee) {

        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public void removeEmployee(String name) {

        Employee employee = findEmployeeByName(name);

        Session session = sessionFactory.getCurrentSession();

        session.delete(employee);
    }

    @Override
    @Transactional
    public Employee findEmployeeByName(String name) {

        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, name);

        if (employee == null) {
            throw new RuntimeException("Cannot find employee with name" + name);
        }
        return employee;
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {

        return sessionFactory.getCurrentSession().createQuery("select o from Employee o").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}