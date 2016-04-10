package ua.java.goit;

import java.util.*;

public class ListComparator {

    public static void populate(List<Integer> myList, int size) {
        for (int j = 0; j < size; j++) {
            myList.add((int) (Math.random() * size));
        }
    }

    public static long arrayListPopulateTime(List<Integer> myList, int numberOfIterations, int size) {

        long currentTime = 0;
        myList = new ArrayList<>();

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();

            for (int j = 0; j < size; j++) {
                myList.add((int) (Math.random() * size));
            }

            long ending = System.nanoTime();
            currentTime += ending - beginning;
            myList = new ArrayList<>();
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static long linkedListPopulateTime(List<Integer> myList, int numberOfIterations, int size) {

        long currentTime = 0;
        myList = new LinkedList<>();

        for (int i = 0; i < numberOfIterations; i++) {

            long beginning = System.nanoTime();

            for (int j = 0; j < size; j++) {
                myList.add((int) (Math.random() * size));
            }

            long ending = System.nanoTime();
            currentTime += ending - beginning;
            myList = new ArrayList<>();
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int addingTime(List<Integer> myList, int numberOfIterations, int index) {

        int currentTime = 0;

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            myList.add(index, (int) (Math.random() * myList.size()));
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }


    public static int indexGettingTime(List<Integer> myList, int numberOfIterations, int index) {

        int currentTime = 0;

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            myList.get(index);
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int removingTime(List<Integer> myList, int numberOfIterations, int index) {

        int currentTime = 0;

        for (int i = 0; i < numberOfIterations; i++) {
            int k = myList.get(index);
            long beginning = System.nanoTime();
            myList.remove(index);
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
            myList.add(index, k);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int containingTime(List<Integer> myList, int numberOfIterations, int value) {

        int currentTime = 0;

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            myList.contains(value);
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int iterAddingTime(List<Integer> myList, int numberOfIterations, int value) {

        int currentTime = 0;

        ListIterator<Integer> iterator = myList.listIterator();

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            iterator.add(value);
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int iterRemovingTime(List<Integer> myList, int numberOfIterations) {

        int currentTime = 0;

        ListIterator<Integer> iterator = myList.listIterator();

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            iterator.next();
            iterator.remove();
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }
}
