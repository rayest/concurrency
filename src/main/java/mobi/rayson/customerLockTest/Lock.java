package mobi.rayson.customerLockTest;

import java.util.Collection;

public interface Lock {

    class TimeoutException extends Exception {
        public TimeoutException(String message) {
            super(message);
        }
    }

    void lock();

    void lock(long mills);

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
