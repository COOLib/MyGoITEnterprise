package ua.goit.domains;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "dish")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "ingredient_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Ingredient> ingredient;

    @Column(name = "price")
    private int price;

    @Column(name = "weight")
    private int weight;

    public Dish() {}

    public Dish(int id,String name, Category category, int price, int weight) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (price != dish.price) return false;
        if (weight != dish.weight) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (category != dish.category) return false;
        return ingredient != null ? ingredient.equals(dish.ingredient) : dish.ingredient == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" + "\n" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category + ",\n" +
                "ingredient=" + ingredient + "\n" +
                "price=" + price +
                ", weight=" + weight +
                '}' + "\n";
    }
}