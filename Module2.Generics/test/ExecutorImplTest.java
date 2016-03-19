import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExecutorImplTest {

    @Test
    public void SomeTest() throws Exception {

        List<Number> squares = new ArrayList<>();

        ExecutorImpl numberExecutor = new ExecutorImpl();
        Validator validator = new NumberValidator();

        numberExecutor.addTask(new Rectangle(10, 20), validator);
        numberExecutor.addTask(new Rectangle(0, 0), validator);
        numberExecutor.addTask(new Rectangle(20, 30), validator);
        numberExecutor.addTask(new Rectangle(15, 15), validator);
        numberExecutor.addTask(new Rectangle(10, 8), validator);
        numberExecutor.addTask(new Circle(10), validator);
        numberExecutor.addTask(new Circle(7), validator);


        numberExecutor.execute();

        System.out.println("Valid results:");
        for (Object number : numberExecutor.getValidResults()) {
            System.out.println(number);
        }
        System.out.println("Invalid results:");
        for (Object number : numberExecutor.getInvalidResults()) {
            System.out.println(number);
        }

    }

}