package ua.goit.actions;

public class DivisionAction implements Action {

    private boolean isUnary = false;

    @Override
    public double unaryAction(double v) {

        throw new ArithmeticException("If  the action is division, the second argument have to to be here!");
    }

    @Override
    public double binaryAction(double v, double v1) {

        if (v1 == 0) {

            throw new ArithmeticException("The divider must not to be equals zero!!");
        } else {

            return v / v1;
        }
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
