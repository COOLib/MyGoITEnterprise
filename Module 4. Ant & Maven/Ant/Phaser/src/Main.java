
public class Main {

    public static void main(String[] args) {

        MultiThreadAdder adder = new MultiThreadAdder();
        int[] array = new int[200];

        for (int i = 0; i < array.length; i++) {
            array[i] = i * i + 1;
        }
        int numberOfThreads = array.length / 10;

        long singleSum = adder.getSum(array,numberOfThreads);
        System.out.println("Square sum counted by single thread: " + singleSum);

        System.out.println();
        long multiSum = adder.getSquareSum(array, numberOfThreads);
        System.out.println("Square sum counted by " + numberOfThreads + " threads: " + multiSum);
    }
}
