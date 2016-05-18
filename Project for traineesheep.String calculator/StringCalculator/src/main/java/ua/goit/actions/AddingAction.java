package ua.goit.actions;

public class AddingAction implements Action {

    private boolean isUnary = false;

    public double unaryAction(double argument1) {

        throw new ArithmeticException("If  the action is adding, the second argument have to to be here!");
    }

    public double binaryAction(double argument1, double argument2) {

        return argument1 + argument2;
    }

    public boolean isUnary() {
        return isUnary;
    }

}
