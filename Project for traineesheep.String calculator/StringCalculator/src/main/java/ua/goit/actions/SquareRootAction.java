package ua.goit.actions;

import java.math.BigDecimal;

public class SquareRootAction implements Action {

private boolean isUnary = true;

    @Override
    public BigDecimal unaryAction(BigDecimal v) {

        if (v.doubleValue() < 0) {

            throw new ArithmeticException("The number must be more than zero!");
        } else {

            return BigDecimal.valueOf(Math.sqrt(v.doubleValue()));
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
