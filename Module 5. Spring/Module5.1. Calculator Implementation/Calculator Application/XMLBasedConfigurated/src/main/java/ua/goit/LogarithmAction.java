package ua.goit;

public class LogarithmAction implements Action {

    private boolean isUnary = true;

    @Override
    public double unaryAction(double v) {

        if (v < 1) {

            throw new ArithmeticException("I don't like this action! Number must be more than 1.");
        } else {

            return Math.log(v);
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
