package ua.goit.essences;

import java.util.Date;

/**
 * Created by COOLib on 24.05.2016.
 */
public class Employee {

    private int id;
    private String name;
    private String secondName;
    private Date birthDate;
    private String phone;
    private String position;
    private int salary;

    public Employee() {}

    public Employee(int id, String name, String secondName, Date birthDate, String phone, String position, int salary) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
