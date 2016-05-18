package ua.goit.actions;

public class PowerAction implements Action {

    private boolean isUnary = false;

    @Override
    public double unaryAction(double v) {

        throw new ArithmeticException("If  the action is multiplying, the second argument have to to be here!");
    }

    @Override
    public double binaryAction(double v, double v1) {

        double result = Math.pow(v, v1);
            return result;
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
