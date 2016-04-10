package ua.java.goit;

import java.util.*;

public class SetComparator {

    public static void populate(Set<Integer> mySet, int size) {

        for (int j = 0; j < size; j++) {
            mySet.add(size - j);
        }
    }

    public static long hashPopulateTime(Set<Integer> mySet, int numberOfIterations, int size) {

        long currentTime = 0;
        mySet = new HashSet<>();

        for (int i = 0; i < numberOfIterations; i++) {

            long beginning = System.nanoTime();

            for (int j = 0; j < size; j++) {
                mySet.add(size - j);
            }

            long ending = System.nanoTime();
            currentTime += ending - beginning;

            mySet = new HashSet<>();
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static long treePopulateTime(Set<Integer> mySet, int numberOfIterations, int size) {

        long currentTime = 0;
        mySet = new TreeSet<>();

        for (int i = 0; i < numberOfIterations; i++) {

            long beginning = System.nanoTime();

            for (int j = 0; j < size; j++) {
                mySet.add(i * j + j);
            }

            long ending = System.nanoTime();
            currentTime += ending - beginning;
            mySet = new TreeSet<>();
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int addingTime(Set<Integer> mySet, int numberOfIterations, int value) {

        int currentTime = 0;

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            mySet.add(value);
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
            mySet.remove(value);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int removingTime(Set<Integer> mySet, int numberOfIterations, int value) {

        int currentTime = 0;

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            mySet.remove(value);
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
            mySet.add(value);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }

    public static int containingTime(Set<Integer> mySet, int numberOfIterations, int value) {

        int currentTime = 0;

        for (int i = 0; i < numberOfIterations; i++) {
            long beginning = System.nanoTime();
            mySet.contains(value);
            long ending = System.nanoTime();
            currentTime += (int) (ending - beginning);
        }

        currentTime = currentTime / numberOfIterations;
        return currentTime;
    }
}
