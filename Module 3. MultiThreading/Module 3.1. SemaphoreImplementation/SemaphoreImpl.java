
public class SemaphoreImpl implements Semaphore {

    private final Object lock = new Object();
    private int permitCounter;

    public SemaphoreImpl() {}

    public SemaphoreImpl(int counter) {
        if (counter <= 0) {
            throw new IllegalArgumentException("There is no permits in semaphore!");
        }
        this.permitCounter = counter;
    }


    @Override
    public void acquire() throws InterruptedException {

        while (permitCounter == 0) {
            lock.wait();
        }
        permitCounter--;
    }

    @Override
    public void acquire(int permits) throws InterruptedException {

        while (permitCounter == 0) {
            lock.wait();
        }

        if(permits >= permitCounter){
            permitCounter = 0;
        } else {
            permitCounter -= permits;
        }
    }

    @Override
    public void release() {

        if (permitCounter == 0) {
            lock.notify();
        }
        permitCounter++;
    }

    @Override
    public void release(int permits) {

        if (permitCounter < permits) {
            lock.notifyAll();
        }

        if (permitCounter < permits) {
            permitCounter = permits;
        }
    }

    @Override
    public int getAvailablePermits() {
        return permitCounter;
    }
}
