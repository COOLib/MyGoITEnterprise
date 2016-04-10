package ua.goit;

public class Rectangle implements Task<Double> {

    private int a;
    private int b;
    private Double res;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public void execute() {
        res = Double.valueOf(a * b);
    }

    @Override
    public Double getResult() {
        return res;
    }
}
