package ua.goit.actions;

import java.math.BigDecimal;

public class LogarithmAction implements Action {

    private boolean isUnary = true;

    @Override
    public BigDecimal unaryAction(BigDecimal v) {

        if (v.doubleValue() < 1) {

            throw new ArithmeticException("I don't like this action! Number must be more than 1.");
        } else {

            return BigDecimal.valueOf(Math.log(v.doubleValue()));
        }
    }

    @Override
    public BigDecimal binaryAction(BigDecimal v, BigDecimal v1) {

        throw new ArithmeticException("If  the action is division, the second argument have to to be here!");
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
