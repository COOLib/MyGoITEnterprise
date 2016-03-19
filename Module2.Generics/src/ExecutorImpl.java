import java.util.ArrayList;
import java.util.List;

public class ExecutorImpl implements Executor<Number> {


    private List<Number> validResults = new ArrayList<>();
    private List<Number> invalidResults = new ArrayList<>();

    private double number1 = 0;
    private double number2 = 0;

    @Override
    public void addTask(Task number) throws Exception {

        addTask(number, new NumberValidator());
    }

    @Override
    public void addTask(Task task, Validator validator) throws Exception {

        if (number1 != 0) {
            throw new Exception("Executor was called.");
        }

        task.execute();

        if (!validator.isValid(task.getResult())) {
            invalidResults.add((Double)task.getResult());
        } else {
            validResults.add((Double)task.getResult());
        }
    }

    @Override
    public void execute() {

        for (int i = 0; i < validResults.size(); i++) {
            validResults.get(i);
            System.out.print(validResults.get(i) + " ");
            number1 += validResults.get(i).doubleValue();
        }
        System.out.println();
        for (int i = 0; i < invalidResults.size(); i++) {
            invalidResults.get(i);
            System.out.print(invalidResults.get(i) + " ");
            number2 += invalidResults.get(i).doubleValue();
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

        if (validResults.size() == 0) {
            throw new Exception("An execute method wasn't called to Rectangle " + invalidResults.toString());
        }
        return invalidResults;
    }
}

