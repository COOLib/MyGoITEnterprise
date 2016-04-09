import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(8);
        Semaphore semaphore = new SemaphoreImpl(5);

        for (int i = 0; i < 8; i++) {
            final int finalI = i;

            executor.execute(() -> {
                System.out.println(finalI + " thread started working...");

                try {

                    if (semaphore.getAvailablePermits() > 0) {
                        semaphore.acquire();
                        System.out.println(finalI + " got access...");
                    } else {
                        System.out.println(1 + " waiting...");
                        Thread.sleep(10);
                    }

                    semaphore.release();
                    System.out.println(finalI + " finished working...");

                } catch (Exception e) {
                    System.out.println(finalI + " can't got access!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            });
        }

        executor.shutdown();
    }
}
