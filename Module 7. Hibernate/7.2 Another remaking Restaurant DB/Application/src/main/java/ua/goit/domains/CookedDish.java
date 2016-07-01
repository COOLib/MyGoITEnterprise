package ua.goit.domains;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cooked_dish")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CookedDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "dish_number")
    private int number;

    @Column(name = "dish_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Cook cook;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "data")
    private Date date;

    public CookedDish() {
    }

    public CookedDish(int number, int id, Cook cook, int orderNumber, Date date) {
        this.number = number;
        this.id = id;
        this.cook = cook;
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

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
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
        if (orderNumber != that.orderNumber) return false;
        if (cook != null ? !cook.equals(that.cook) : that.cook != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cook != null ? cook.hashCode() : 0);
        result = 31 * result + orderNumber;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CookedDish{" + "\n" +
                "number=" + number + ",\n" +
                "id=" + id + ",\n" +
                "orderNumber=" + orderNumber + ",\n" +
                "date=" + date +
                '}' + "\n";
    }
}