package ua.java.goit;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ua.java.goit.ListComparator.removingTime;

public class ListComparatorTest {

    private static List<Integer> arrays;
    private long testTime = 10000;
    private boolean isLess;

    @Before
    public void setUp() throws Exception {

        ListComparator listComparator = new ListComparator();
    }

    @Test
    public void testPopulate() throws Exception {

        setUp();
        arrays = new ArrayList<>();

        List<Integer> myResult = new ArrayList<>();
        ListComparator.populate(arrays, 10);
        myResult.addAll(arrays);
        assertEquals(myResult, arrays);

    }

    @Test
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

        long populateTime = ListComparator.arrayListPopulateTime(arrays, 10, 100);


        if (time <= populateTime) {
            isLess = false;
        }

        System.out.println("Time of population of the ArrayList " + populateTime);
        System.out.println("Test time " + time);
        System.out.println("Populating time is less than test time : " + isLess);
        System.out.println();
    }

    @Test
    public void testAddingTime() throws Exception {

        newPopulate();

        long addingTime = ListComparator.addingTime(arrays, 1, 50);

        if (testTime < addingTime) {
            isLess = false;
        }

        System.out.println("Element adding time " + addingTime + " is less than test time " + testTime + " : " + isLess);
        System.out.println();
    }

    @Test
    public void testIndexGettingTime() throws Exception {

        newPopulate();

        long gettingTime = ListComparator.indexGettingTime(arrays, 1, 50);

        if (testTime < gettingTime) {
            isLess = false;
        }

        System.out.println("Element getting time " + gettingTime + " is less than test time " + testTime + " : " + isLess);
        System.out.println();
    }

    @Test
    public void testRemovingTime() throws Exception {

        newPopulate();

        long removeTime = removingTime(arrays, 1, 50);

        if (testTime < removeTime) {
            isLess = false;
        }

        System.out.println("Element removing time " + removeTime + " is less than than test time " + testTime + " : " + isLess);
        System.out.println();
    }

    private void newPopulate() {

        arrays = new ArrayList<>();
        ListComparator.populate(arrays, 100);
        isLess = true;
    }
}