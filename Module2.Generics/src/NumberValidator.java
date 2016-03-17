
public class NumberValidator implements Validator<Number> {

    @Override
    public boolean isValid(Rectangle result) {

        result.execute();

        if (!result.getResult().equals(Double.valueOf(0))){
            return true;
        }
        return false;
    }
}
