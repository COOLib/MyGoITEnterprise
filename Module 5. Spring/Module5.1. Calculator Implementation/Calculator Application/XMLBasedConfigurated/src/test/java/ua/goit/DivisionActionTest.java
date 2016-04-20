package ua.goit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DivisionActionTest {

    private static Action division;

    @BeforeClass
    public static void init() {

        division = new DivisionAction();
    }

    @Test(expected = ArithmeticException.class)
    public void testUnaryAction() throws Exception {

        division.unaryAction(5);
    }

    @Test
    public void testBinaryAction() throws Exception {

        double result = division.binaryAction(12, 3);
        Assert.assertEquals(result, 4, 0.0001);
    }

    @Test
    public void testIsUnary() throws Exception {

        Assert.assertEquals(division.isUnary(), false);
    }
}