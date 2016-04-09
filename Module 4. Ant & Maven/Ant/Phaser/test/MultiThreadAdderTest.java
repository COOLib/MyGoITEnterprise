import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MultiThreadAdderTest {

    private final static int[] array = new int[100];
    private final static int numberOfThreads = 10;
    private static long squareSum;
    private final MultiThreadAdder adder = new MultiThreadAdder();

    @BeforeClass
    public static void setUp() throws Exception {

        for (int i = 0; i < array.length; i++) {
            array[i] = i * i;
            squareSum += array[i] * array[i];
        }
    }

    @Test
    public void testGetSum() throws Exception {

        final long result = adder.getSum(array, numberOfThreads);
        assertThat(result, is(squareSum));

    }

    @Test
    public void testGetSquareSum() throws Exception {

        final long result = adder.getSquareSum(array, numberOfThreads);
        assertThat(result, is(squareSum));
    }
}