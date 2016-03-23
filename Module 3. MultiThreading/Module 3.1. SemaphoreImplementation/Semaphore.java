
public interface Semaphore {

    public void acquire() throws InterruptedException;

    public void acquire(int permits) throws InterruptedException;

    public void release() throws InterruptedException;

    public void release(int permits);

    public int getAvailablePermits();
}
