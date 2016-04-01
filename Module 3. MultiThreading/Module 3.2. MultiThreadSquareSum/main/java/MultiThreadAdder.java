import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;


public class MultiThreadAdder implements SquareSum {

    public static void main(String[] args) {

        MultiThreadAdder adder = new MultiThreadAdder();
        int[] array = new int[200];

        for (int i = 0; i < array.length; i++) {
            array[i] = i * i + 1;
        }
        int numberOfThreads = array.length / 10;

        long multiSum = adder.getSquareSum(array, numberOfThreads);
        System.out.println("Square sum counted by " + numberOfThreads + " threads: " + multiSum);
    }

    public long getSquareSum(int[] values, int numberOfThreads) {

        long[] multiArr = new long[numberOfThreads];

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Phaser phaser = new Phaser(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            final int finalI = i;

            if (finalI == values.length) {
                break;
            }
            executor.execute(() -> {

                if (values.length < numberOfThreads) {
                    multiArr[finalI] = values[finalI] * values[finalI];

                } else {
                    int start = (values.length / numberOfThreads) * finalI;
                    int end = (values.length / numberOfThreads) * (finalI + 1);

                    for (int j = start; j < end; j++) {
                        multiArr[finalI] += values[j] * values[j];
                    }

                    if ((finalI == numberOfThreads - 1) && end < values.length) {

                        for (int m = end; m < values.length; m++) {
                            multiArr[finalI] += values[m] * values[m];
                        }
                    }
                }
                phaser.arriveAndAwaitAdvance();
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        long result = addingForSum(multiArr);

        return result;
    }

    private long addingForSum (long[] subResults) {

        long result = 0;

        for (int i = 0; i < subResults.length; i++) {
            result += subResults[i];
        }

        return result;
    }
}
