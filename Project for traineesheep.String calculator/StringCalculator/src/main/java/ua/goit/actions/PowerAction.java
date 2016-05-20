package ua.goit.actions;

import java.math.BigDecimal;

public class PowerAction implements Action {

    private boolean isUnary = false;

    @Override
    public BigDecimal unaryAction(BigDecimal v) {

        throw new ArithmeticException("If  the action is multiplying, the second argument have to to be here!");
    }

    @Override
    public BigDecimal binaryAction(BigDecimal v, BigDecimal v1) {

        BigDecimal result =v.pow(v1.intValue());
            return result;
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
