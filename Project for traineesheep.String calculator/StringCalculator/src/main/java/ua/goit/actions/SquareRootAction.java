package ua.goit.actions;

public class SquareRootAction implements Action {

private boolean isUnary = true;

    @Override
    public double unaryAction(double v) {

        if (v < 0) {

            throw new ArithmeticException("The number must be more than zero!");
        } else {

            return Math.sqrt(v);
        }
    }

    @Override
    public double binaryAction(double v, double v1) {

        throw new ArithmeticException("If  the action is division, the second argument have to to be here!");
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
