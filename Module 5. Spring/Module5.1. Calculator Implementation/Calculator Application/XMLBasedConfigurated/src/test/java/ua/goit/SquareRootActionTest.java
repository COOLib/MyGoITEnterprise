package ua.goit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SquareRootActionTest {

    private static Action sqrt;

    @BeforeClass
    public static void init() {

        sqrt = new SquareRootAction();
    }

    @Test
    public void testUnaryAction() throws Exception {

        double result = sqrt.unaryAction(9);
        Assert.assertEquals(result, 3, 0.0001);
    }

    @Test(expected = ArithmeticException.class)
    public void testUnaryAction1() throws Exception {

        double result = sqrt.unaryAction(-5);
    }

    @Test(expected = ArithmeticException.class)
    public void testBinaryAction() throws Exception {

        sqrt.binaryAction(5,2);
    }

    @Test
    public void testIsUnary() throws Exception {

        Assert.assertEquals(sqrt.isUnary(), true);
    }
}