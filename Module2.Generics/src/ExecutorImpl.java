import java.util.ArrayList;
import java.util.List;

public class ExecutorImpl implements Executor<Rectangle> {


    private List<Rectangle> validResults = new ArrayList<>();
    private List<Rectangle> invalidResults = new ArrayList<>();

    private int number1 = 0;
    private int number2 = 0;

    @Override
    public void addTask(Rectangle number) throws Exception {

        Validator<Number> validator = new NumberValidator();

        addTask(number, validator);
    }

    @Override
    public void addTask(Rectangle task, Validator<Number> validator) throws Exception {

        if (number1 != 0 || number2 != 0) {
            throw new Exception("Executor was called.");
        }

        if (!validator.isValid(task)) {
            invalidResults.add(task);
        } else {
            validResults.add(task);
        }
    }

    @Override
    public void execute() {

        for (int i = 0; i < validResults.size(); i++) {
            validResults.get(i).execute();
            System.out.print(validResults.get(i) + " ");
            number1 += validResults.get(i).getResult();
        }
        System.out.println();
        for (int i = 0; i < invalidResults.size(); i++) {
            invalidResults.get(i).execute();
            System.out.print(invalidResults.get(i) + " ");
            number2 += invalidResults.get(i).getResult();
        }
        System.out.println();
    }

    @Override
    public List getValidResults() throws Exception {

        if (number1 == 0) {
            throw new Exception("An execute method wasn't called to Rectangle " + validResults.toString());
        }
        return validResults;
    }

    @Override
    public List getInvalidResults() throws Exception {

        if (number2 != 0) {
            throw new Exception("An execute method wasn't called to Rectangle " + invalidResults.toString());
        }
        return invalidResults;
    }
}

