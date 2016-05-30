package ua.goit.essences;

import java.util.Date;

/**
 * Created by COOLib on 24.05.2016.
 */
public class CookedDish {

    private int number;
    private String name;
    private String category;
    private int employeeId;
    private int orderNumber;
    private Date date;

    public CookedDish() {
    }

    public CookedDish(int number, String name, String category, int employeeId, int orderNumber, Date date) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.employeeId = employeeId;
        this.orderNumber = orderNumber;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", employeeId=" + employeeId +
                ", orderNumber=" + orderNumber +
                ", date=" + date +
                '}';
    }
}
