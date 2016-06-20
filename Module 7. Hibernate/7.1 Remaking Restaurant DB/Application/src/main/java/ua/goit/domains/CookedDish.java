package ua.goit.domains;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cooked_dish")
public class CookedDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "dish_number")
    private int number;

    @Column(name = "dish_id")
    private int id;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "data")
    private Date date;

    public CookedDish() {
    }

    public CookedDish(int number, int id, int employeeId, int orderNumber, Date date) {
        this.number = number;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CookedDish that = (CookedDish) o;

        if (id != that.id) return false;
        if (employeeId != that.employeeId) return false;
        if (orderNumber != that.orderNumber) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + employeeId;
        result = 31 * result + orderNumber;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CookedDish{" + "\n" +
                "number=" + number + ",\n" +
                "id=" + id + ",\n" +
                "employeeId=" + employeeId + ",\n" +
                "orderNumber=" + orderNumber + ",\n" +
                "date=" + date +
                '}' + "\n";
    }
}