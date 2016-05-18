package ua.goit.actions;

/**
 * Created by COOLib on 15.05.2016.
 */
public class TanAction implements Action {

    private boolean isUnary = true;

    @Override
    public double unaryAction(double argument1) {

        return Math.tan(argument1) ;
    }

    @Override
    public double binaryAction(double argument1, double argument2) {

        throw new ArithmeticException("If  the action is tangent, the second argument have to to be here!");
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
