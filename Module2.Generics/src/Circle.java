/**
 * Created by COOLib on 19.03.2016.
 */
public class Circle implements Task<Double> {

    private int radius;
    private double square;

    public Circle(int radius) {

        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void execute() {
        square = Math.PI * radius * radius;

    }

    @Override
    public Double getResult() {
        return square;
    }
}

