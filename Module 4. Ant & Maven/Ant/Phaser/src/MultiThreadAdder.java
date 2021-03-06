import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class MultiThreadAdder implements SquareSum {

    public long getSum(int[] values, int numberOfThreads) {

        long square = 0;
        long[] singleArr = new long[numberOfThreads];

        if (values.length < numberOfThreads) {

            for (int i = 0; i < values.length; i++) {
                singleArr[i] = values[i] * values[i];
                square += values[i] * values[i];
            }
        } else {
            for (int k = 0; k < numberOfThreads; k++) {

                int start = (values.length / numberOfThreads) * k;
                int end = (values.length / numberOfThreads) * (k + 1);

                for (int j = start; j < end; j++) {
                    square += values[j] * values[j];
                    singleArr[k] += values[j] * values[j];
                }

                if ((k == numberOfThreads - 1) && end < values.length) {

                    for (int m = end; m < values.length; m++) {
                        square += values[m] * values[m];
                        singleArr[k] += values[m] * values[m];
                    }
                }
            }
        }

        for (long aSingleArr : singleArr) {
            System.out.print(aSingleArr + "  ");
        }
        System.out.println();

        return square;
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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();


        for (long aMultiArr : multiArr) {
            System.out.print(aMultiArr + "  ");
        }
        System.out.println();

        return addingForSum(multiArr);
    }

    private long addingForSum (long[] subResults) {

        long result = 0;

        for (long subResult : subResults) {
            result += subResult;
        }

        return result;
    }
}
