package ua.goit.actions;

public class MultiplyingAction implements Action {

    private boolean isUnary = false;

    @Override
    public double unaryAction(double v) {

        throw new ArithmeticException("If  the action is multiplying, the second argument have to to be here!");
    }

    @Override
    public double binaryAction(double v, double v1) {

        return v * v1;
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
