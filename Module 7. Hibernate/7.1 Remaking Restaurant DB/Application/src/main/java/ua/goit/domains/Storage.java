package ua.goit.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "storage")
public class Storage {

    @Column(name = "ingredient_id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    public Storage() {}

    public Storage(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (id != storage.id) return false;
        return quantity == storage.quantity;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
