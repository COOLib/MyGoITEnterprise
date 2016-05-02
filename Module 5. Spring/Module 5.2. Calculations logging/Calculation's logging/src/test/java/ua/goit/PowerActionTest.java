package ua.goit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PowerActionTest {

    private static Action power;

    @BeforeClass
    public static void init() {

        power = new PowerAction();
    }

    @Test(expected = ArithmeticException.class)
    public void testUnaryAction() throws Exception {

        power.unaryAction(5);
    }

    @Test
    public void testBinaryAction() throws Exception {

        double result = power.binaryAction(3, 3);
        Assert.assertEquals(result, 27, 0.0001);
    }

    @Test
    public void testIsUnary() throws Exception {

        Assert.assertEquals(power.isUnary(), false);
    }
}