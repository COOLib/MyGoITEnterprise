package ua.goit.essences;

/**
 * Created by COOLib on 24.05.2016.
 */
public class Menu {

    private String name;
    private String dishName;
    private String category;

    public Menu() {}

    public Menu(String name, String dishName, String category) {
        this.name = name;
        this.dishName = dishName;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", dishName='" + dishName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
