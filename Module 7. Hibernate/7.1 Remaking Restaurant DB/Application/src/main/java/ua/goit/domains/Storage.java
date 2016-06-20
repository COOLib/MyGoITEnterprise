package ua.goit.domains;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "ingredient_id")
    private int ingredientId;

    @Column(name = "quantity")
    private int quantity;

    public Storage() {}

    public Storage(int ingredientId, int quantity) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
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

        if (ingredientId != storage.ingredientId) return false;
        return quantity == storage.quantity;

    }

    @Override
    public int hashCode() {
        int result = ingredientId;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id + ",\n" +
                "ingredientId=" + ingredientId + ",\n" +
                "quantity=" + quantity +
                '}';
    }
}