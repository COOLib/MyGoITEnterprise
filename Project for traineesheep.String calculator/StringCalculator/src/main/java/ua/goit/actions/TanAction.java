package ua.goit.actions;

import java.math.BigDecimal;

/**
 * Created by COOLib on 15.05.2016.
 */
public class TanAction implements Action {

    private boolean isUnary = true;

    @Override
    public BigDecimal unaryAction(BigDecimal argument1) {

        return BigDecimal.valueOf(Math.tan(argument1.doubleValue()));
    }

    @Override
    public BigDecimal binaryAction(BigDecimal argument1, BigDecimal argument2) {

        throw new ArithmeticException("If  the action is tangent, the second argument have to to be here!");
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }
}
