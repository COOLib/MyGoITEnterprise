package ua.goit.domains;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Orders {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "order_number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Waiter waiter;

    @ManyToMany
    @JoinTable(
            name = "dish_orders",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Dish> dishes;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "date")
    private Date date;

    @Column(name = "is_closed")
    private String isClosed;

    public Orders() {}

    public Orders(int number, Waiter waiter, List<Dish> dishes, int tableNumber, Date date, String isClosed) {
        this.number = number;
        this.waiter = waiter;
        this.dishes = dishes;
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

    public Employee getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders order = (Orders) o;

        if (tableNumber != order.tableNumber) return false;
        if (waiter != null ? !waiter.equals(order.waiter) : order.waiter != null) return false;
        if (dishes != null ? !dishes.equals(order.dishes) : order.dishes != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        return isClosed != null ? isClosed.equals(order.isClosed) : order.isClosed == null;

    }

    @Override
    public int hashCode() {
        int result = waiter != null ? waiter.hashCode() : 0;
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        result = 31 * result + tableNumber;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (isClosed != null ? isClosed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number + ",\n" +
                "dishes=" + '\n' + dishes + ",\n" +
                "tableNumber=" + tableNumber +
                ", date=" + date +
                ", isClosed='" + isClosed + '\'' +
                '}' + '\n';
    }
}