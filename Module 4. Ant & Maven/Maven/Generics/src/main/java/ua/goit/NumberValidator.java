package ua.goit;

public class NumberValidator implements Validator<Double> {

    @Override
    public boolean isValid(Double result) {



        if (result.doubleValue() > 200) {
            return true;
        }
        return false;
    }
}
