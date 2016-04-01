import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadAdder implements SquareSum {

    private long singleThreadTime;
    private long multiThreadTime;
    private long[] multiArr;
    private long[] singleArr;

    public static void main(String[] args) {

        MultiThreadAdder adder = new MultiThreadAdder();
        int[] array = new int[150];

        for (int i = 0; i < array.length; i++) {
            array[i] = i * i + 1;
        }
        int numberOfThreads = array.length / 10;
        adder.getSquareSum(array, numberOfThreads);

        System.out.println("Square sum counted by single thread: " + adder.getSum(array, numberOfThreads));
        System.out.println("Square sum counted by " + numberOfThreads + " threads: " + adder.getSquareSum(array, numberOfThreads));
        System.out.println();
        System.out.println("Required time for single thread(in nanoseconds): " + adder.singleThreadTime);
        System.out.println("Required time for " + numberOfThreads + " threads(in nanoseconds): " + adder.multiThreadTime);
        System.out.println();
        for (int i = 0; i < adder.multiArr.length; i++) {
            System.out.print(adder.multiArr[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < adder.singleArr.length; i++) {
            System.out.print(adder.singleArr[i] + "  ");
        }
    }

    public long getSum(int[] values, int numberOfThreads) {

        long square = 0;
        long a = System.nanoTime();
        singleArr = new long[numberOfThreads];

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
        long b = System.nanoTime();

        singleThreadTime = b - a;

        return square;
    }

    public long getSquareSum(int[] values, int numberOfThreads) {

        AtomicLong k = new AtomicLong();
        multiArr = new long[numberOfThreads];

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Phaser phaser = new Phaser(numberOfThreads);

        long a = System.nanoTime();

        for (int i = 0; i < numberOfThreads; i++) {
            final int finalI = i;
            if (finalI == values.length) {
                break;
            }
            executor.execute(() -> {
                int square = 0;

                if (values.length < numberOfThreads) {
                    square = values[finalI] * values[finalI];
                    multiArr[finalI] = values[finalI] * values[finalI];

                } else {
                    int start = (values.length / numberOfThreads) * finalI;
                    int end = (values.length / numberOfThreads) * (finalI + 1);

                    for (int j = start; j < end; j++) {
                        square += values[j] * values[j];
                        multiArr[finalI] += values[j] * values[j];
                    }

                    if ((finalI == numberOfThreads - 1) && end < values.length) {

                        for (int m = end; m < values.length; m++) {
                            square += values[m] * values[m];
                            multiArr[finalI] += values[m] * values[m];
                        }
                    }
                }
                //String name = Thread.currentThread().getName();
                //System.out.println(name + " starts waiting.");
                phaser.arriveAndAwaitAdvance();
                k.addAndGet(square);
                //System.out.println(name + " finishes waiting.");
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long b = System.nanoTime();

        multiThreadTime = b - a;

        executor.shutdown();

        return k.longValue();
    }
}
