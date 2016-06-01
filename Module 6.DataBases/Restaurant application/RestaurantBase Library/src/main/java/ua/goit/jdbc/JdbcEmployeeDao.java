package ua.goit.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.essences.Employee;
import ua.goit.interfaces.EmployeeDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeDao implements EmployeeDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDao.class);


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addEmployee(Employee employee) {

        LOGGER.info("Connecting to database. Running method is addEmployee");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO EMPLOYEE (name, second_name, birth_date, phone, position, salary) VALUES (?, ?, ?, ?, ?, ?)")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSecondName());
            statement.setDate(3, new Date(employee.getBirthDate().getTime()));
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getPosition());
            statement.setInt(6, employee.getSalary());
            statement.executeUpdate();

            LOGGER.info("New employee has been added");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeEmployee(String name) {

        LOGGER.info("Connecting to database.Running method is removeEmployee");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE name = ?")) {

            LOGGER.info("Successfully connected to DB");

            statement.setString(1, name);
            statement.executeUpdate();

            LOGGER.info("Employee with name " + name + " has been deleted");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee findEmployeeByName(String name) {

        LOGGER.info("Connecting to database.Running method is findByName");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE name = ?")) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Employee employee = createEmployee(resultSet);
                LOGGER.info("Employee has been found by name " + name);
                return employee;
            } else {
                LOGGER.error("Cannot find employee with name " + name);
                throw new RuntimeException("Cannot find employee with name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getAllEmployees() {

        LOGGER.info("Connecting to database.Running method is getAllEmployees");

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {


            LOGGER.info("Successfully connected to DB.");

            String sql = "select * from employee";
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                Employee employee = createEmployee(set);
                employeeList.add(employee);
            }

            LOGGER.info("List of employees has been received");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private Employee createEmployee(ResultSet set) throws SQLException {

        Employee employee = new Employee();

        employee.setId(set.getInt("id"));
        employee.setName(set.getString("name"));
        employee.setSecondName(set.getString("second_name"));
        employee.setBirthDate(set.getDate("birth_date"));
        employee.setPhone(set.getString("phone"));
        employee.setPosition(set.getString("position"));
        employee.setSalary(set.getInt("salary"));
        return employee;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
