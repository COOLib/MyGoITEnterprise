package ua.goit.domains;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @Column(name = "salary")
    private int salary;

    public Employee() {
    }

    public Employee(int id, String name, String secondName, Date birthDate, String phone, Position position, int salary) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (salary != employee.salary) return false;
        if (!name.equals(employee.name)) return false;
        if (secondName != null ? !secondName.equals(employee.secondName) : employee.secondName != null) return false;
        if (birthDate != null ? !birthDate.equals(employee.birthDate) : employee.birthDate != null) return false;
        if (!phone.equals(employee.phone)) return false;
        return position == employee.position;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + phone.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + salary;
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" + "\n" +
                "id=" + id + ",\n" +
                "name='" + name + '\'' + ",\n" +
                "secondName='" + secondName + "\',\n" +
                "birthDate=" + birthDate + ",\n" +
                "phone='" + phone + "\',\n" +
                "position='" + position + "\',\n" +
                "salary=" + salary +
                '}' + "\n";
    }
}