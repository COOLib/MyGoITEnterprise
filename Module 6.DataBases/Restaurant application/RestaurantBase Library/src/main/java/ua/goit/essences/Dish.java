package ua.goit.essences;

/**
 * Created by COOLib on 24.05.2016.
 */
public class Dish {

    private String name;
    private String category;
    private int price;
    private int weight;

    public Dish() {}

    public Dish(String name, String category, int price, int weight) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
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
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
