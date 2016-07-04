package ua.goit.domains;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "waiter")
@PrimaryKeyJoinColumn(name = "employee_id",referencedColumnName = "id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Waiter extends Employee {

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "waiter")

    @Fetch(FetchMode.SELECT)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Orders> orders;

    public Waiter() {}

    public Waiter(int id, String name, String secondName, Date birthDate, String phone, int salary, Position position, List<Orders> orders) {
        super(id, name, secondName, birthDate, phone, salary);
        this.position = position;
        this.orders = orders;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Waiter waiter = (Waiter) o;

        if (position != waiter.position) return false;
        return orders != null ? orders.equals(waiter.orders) : waiter.orders == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    public String toString() {

        String s = super.toString() +
                "position=" + position + ",\n" +
                "orders=" + '\n' + orders + '\n';
        return s;
    }
}
