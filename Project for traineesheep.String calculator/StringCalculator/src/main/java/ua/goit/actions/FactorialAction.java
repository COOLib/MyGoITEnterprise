package ua.goit.actions;

/**
 * Created by COOLib on 20.04.2016.
 */
public class FactorialAction implements Action {

    private boolean isUnary = true;

    @Override
    public double unaryAction(double v) {

        if (v > 25 || v < -25) {

            throw new ArithmeticException("This number is too big for doing the action of factorial");
        }

        double accuracy = 0.0001;

        long number = (long) v;

        if (v - number > accuracy) {

            double result = Math.exp(Math.log(factorial(number)) + (v - number) * Math.log(number + 1));

            return result;
        } else {

            return factorial((int) v);
        }
    }

    @Override
    public double binaryAction(double v, double v1) {

        throw new ArithmeticException("If  the action is factorial, the second argument is not needed here!");
    }

    @Override
    public boolean isUnary() {
        return isUnary;
    }

    private long factorial(long n) {

        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }

        return res;
    }
}
