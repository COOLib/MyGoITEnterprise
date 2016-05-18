package ua.goit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.goit.actions.*;

import java.util.HashMap;
import java.util.Map;

public class SpecificCalculatorTest {

    private static Map<String, Action> actions = new HashMap<>();
    SpecificCalculator calculator = new SpecificCalculator(actions);

    private String str = "pow sqrt, log,tan ";
    private String reverse = "P S, L,T ";
    private String formula = "1+2+pow(4,2)-sqrt(9)";

    @BeforeClass
    public static void setMap() {

        actions.put("+", new AddingAction());
        actions.put("-", new SubtractionAction());
        actions.put("*", new MultiplyingAction());
        actions.put("/", new DivisionAction());
        actions.put("sqrt", new SquareRootAction());
        actions.put("pow", new PowerAction());
        actions.put("!", new FactorialAction());
        actions.put("log", new LogarithmAction());
    }

    @Test
    public void testGetAnswer() throws Exception {

        String answer = calculator.getAnswer(formula);
        Assert.assertEquals(answer,"16");

    }

    @Test
    public void testSimpleConvert() throws Exception {

        String s = "-.2";
        String result = calculator.convert(s);
        Assert.assertEquals(result, "-.2 ");
    }

    @Test
    public void testSomeConvert() throws Exception {

        String s = "2.2+(-2.2)";
        String result = calculator.convert(s);
      //  Assert.assertEquals(result, "+ 2.2 -2.2");
    }


    @Test
    public void testReplace() throws Exception {

        String result = calculator.replace(str);
        Assert.assertEquals(result, "P S, L,T ");
    }

    @Test
    public void testReverseReplace() throws Exception {

        String result = calculator.reverseReplace(reverse);
        Assert.assertEquals(result, "pow sqrt, log,tan ");
    }

    @Test
    public void testCalculateAnswer() throws Exception {

    }
}