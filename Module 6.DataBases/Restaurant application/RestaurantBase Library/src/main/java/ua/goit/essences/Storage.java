package ua.goit.essences;

/**
 * Created by COOLib on 24.05.2016.
 */
public class Storage {

    private int id;
    private int quantity;
    private String ingredientName;

    public Storage() {}

    public Storage(int id, int quantity, String ingredientName) {
        this.id = id;
        this.quantity = quantity;
        this.ingredientName = ingredientName;
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

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", ingredientName='" + ingredientName + '\'' +
                '}';
    }
}
