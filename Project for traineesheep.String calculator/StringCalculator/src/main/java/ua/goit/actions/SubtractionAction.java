package ua.goit.actions;

import java.math.BigDecimal;

public class SubtractionAction implements Action {

    private boolean isUnary = false;

    public BigDecimal unaryAction(BigDecimal argument1) {

        throw new ArithmeticException("If  the action is subtraction, the second argument have to to be here!");
    }

    public BigDecimal binaryAction(BigDecimal argument1, BigDecimal argument2) {

        return argument1.subtract(argument2);
    }

    public boolean isUnary() {
        return isUnary;
    }
}
