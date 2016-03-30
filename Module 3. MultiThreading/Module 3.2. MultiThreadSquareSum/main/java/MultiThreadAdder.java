import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadAdder implements SquareSum {

    public static void main(String[] args) {

        MultiThreadAdder adder = new MultiThreadAdder();
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i * i + 1;
        }
        int numberOfThreads = array.length / 10;
        adder.getSquareSum(array, numberOfThreads);

        System.out.println(adder.getSquareSum(array, numberOfThreads));
        System.out.println(adder.getSum(array));
    }

    public long getSum(int[] values) {

        long square = 0;

        for (int i = 0; i < values.length; i++) {
            square += values[i] * values[i];
        }

        return square;
    }

    public long getSquareSum(int[] values, int numberOfThreads) {

        AtomicLong k = new AtomicLong();

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Phaser phaser = new Phaser(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            final int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    int square = 0;

                    if (values.length < numberOfThreads) {
                        square = values[finalI] * values[finalI];
                    } else {
                        int start = (values.length / numberOfThreads) * finalI;
                        int end = (values.length / numberOfThreads) * (finalI + 1);

                        for (int j = start; j < end; j++) {
                            square += values[j] * values[j];
                        }

                        if ((finalI == numberOfThreads - 1) && end < values.length) {

                            for (int m = end; m < values.length; m++) {
                                square += values[m] * values[m];
                            }
                        }
                    }
                    //String name = Thread.currentThread().getName();
                    //System.out.println(name + " starts waiting.");
                    phaser.arriveAndAwaitAdvance();
                    k.addAndGet(square);
                    //System.out.println(name + " finishes waiting.");
                }
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        return k.longValue();
    }
}
