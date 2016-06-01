package ua.goit.essences;

import java.util.Date;

/**
 * Created by COOLib on 24.05.2016.
 */
public class Order {

    private int number;
    private int employee;
    private int tableNumber;
    private Date date;
    private String isClosed;

    public Order() {}

    public Order(int number, int employee, int tableNumber, Date date, String isClosed) {
        this.number = number;
        this.employee = employee;
        this.tableNumber = tableNumber;
        this.date = date;
        this.isClosed = isClosed;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String isClosed() {
        return isClosed;
    }

    public void setClosed(String closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", employee=" + employee +
                ", tableNumber=" + tableNumber +
                ", date=" + date +
                ", isClosed=" + isClosed +
                '}';
    }
}
