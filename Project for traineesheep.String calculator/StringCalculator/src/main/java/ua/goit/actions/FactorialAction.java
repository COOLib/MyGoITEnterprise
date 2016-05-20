package ua.goit.actions;

import java.math.BigDecimal;

/**
 * Created by COOLib on 20.04.2016.
 */
public class FactorialAction implements Action {

    private boolean isUnary = true;

    @Override
    public BigDecimal unaryAction(BigDecimal v) {

        if (v.doubleValue() > 25 || v.doubleValue() < -25) {

            throw new ArithmeticException("This number is too big for doing the action of factorial");
        }

        double accuracy = 0.0001;

        long number = v.longValue();

        if (v.doubleValue() - number > accuracy) {

            BigDecimal result = BigDecimal.valueOf(Math.exp(Math.log(factorial(number)) + (v.doubleValue() - number) * Math.log(number + 1)));

            return result;
        } else {

            return BigDecimal.valueOf(factorial(v.longValue()));
        }
    }

    @Override
    public BigDecimal binaryAction(BigDecimal v, BigDecimal v1) {

        throw new ArithmeticException("If  the action is factorial, the second argument is not needed here!");
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }

    private double factorial(long n) {

        double res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }

        return res;
    }
}
