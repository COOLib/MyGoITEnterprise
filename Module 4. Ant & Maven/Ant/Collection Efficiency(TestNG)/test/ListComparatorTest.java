import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
public class ListComparatorTest {

    private static List<Integer> arrays;
    private long testTime = 10000;
    private boolean isLess;
    private ListComparator listComparator;

    @org.testng.annotations.Test
    public void testPopulate() throws Exception {

        setUp();
        arrays = new ArrayList<>();

        List<Integer> myResult = new ArrayList<>();
        listComparator.populate(arrays, 10);
        myResult.addAll(arrays);
        assertEquals(myResult, arrays);
    }

    @org.testng.annotations.Test
    public void testArrayListPopulateTime() throws Exception {

        List<Integer> list = new ArrayList<>();
        long subRes = 0;

        for (int j = 0; j < 10; j++) {
            long begin = System.nanoTime();

            for (int i = 0; i < 100; i++) {
                list.add((int) (Math.random() * 100));
            }

            long end = System.nanoTime();
            list = new ArrayList<>();
            subRes += end - begin;
        }

        isLess = true;
        long time = subRes / 10;

        long populateTime = listComparator.arrayListPopulateTime(arrays, 10, 100);


        if (time <= populateTime) {
            isLess = false;
        }

        System.out.println("Time of population of the ArrayList " + populateTime);
        System.out.println("Test time " + time);
        System.out.println("Populating time is less than test time : " + isLess);
        System.out.println();
    }

    @org.testng.annotations.Test
    public void testAddingTime() throws Exception {

        newPopulate();

        long addingTime = listComparator.addingTime(arrays, 1, 50);

        if (testTime < addingTime) {
            isLess = false;
        }

        System.out.println("Element adding time " + addingTime + " is less than test time " + testTime + " : " + isLess);
        System.out.println();
    }

    @org.testng.annotations.Test
    public void testIndexGettingTime() throws Exception {

        newPopulate();

        long gettingTime = listComparator.indexGettingTime(arrays, 1, 50);

        if (testTime < gettingTime) {
            isLess = false;
        }

        System.out.println("Element getting time " + gettingTime + " is less than test time " + testTime + " : " + isLess);
        System.out.println();
    }

    @org.testng.annotations.Test
    public void testRemovingTime() throws Exception {

        newPopulate();

        long removeTime = listComparator.removingTime(arrays, 1, 50);

        if (testTime < removeTime) {
            isLess = false;
        }

        System.out.println("Element removing time " + removeTime + " is less than than test time " + testTime + " : " + isLess);
        System.out.println();
    }

    public void setUp() throws Exception {

        listComparator = new ListComparator();
    }

    private void newPopulate() throws Exception {

        setUp();

        arrays = new ArrayList<>();
        listComparator.populate(arrays, 100);
        isLess = true;
    }
}