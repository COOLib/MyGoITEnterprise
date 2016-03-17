import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExecutorImplTest {

    @Test
    public void SomeTest() throws Exception {

        List<Rectangle> rectangles = new ArrayList<>();

        ExecutorImpl numberExecutor = new ExecutorImpl();

        numberExecutor.addTask(new Rectangle(10, 20), new NumberValidator());
        numberExecutor.addTask(new Rectangle(0, 0), new NumberValidator());

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