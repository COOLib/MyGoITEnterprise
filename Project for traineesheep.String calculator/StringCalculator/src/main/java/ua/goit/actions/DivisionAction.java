package ua.goit.actions;

import java.math.BigDecimal;

public class DivisionAction implements Action {

    private boolean isUnary = false;

    @Override
    public BigDecimal unaryAction(BigDecimal v) {

        throw new ArithmeticException("If  the action is division, the second argument have to to be here!");
    }

    @Override
    public BigDecimal binaryAction(BigDecimal v, BigDecimal v1) {

        if (v1.doubleValue() == 0) {

            throw new ArithmeticException("The divider must not to be equals zero!!");
        } else {

            return v.divide(v1);
        }
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
