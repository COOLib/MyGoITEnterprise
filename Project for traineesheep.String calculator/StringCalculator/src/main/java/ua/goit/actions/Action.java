package ua.goit.actions;

public interface Action {

    public double unaryAction(double argument1);

    public double binaryAction(double argument1, double argument2);

    public boolean isUnary();
}
