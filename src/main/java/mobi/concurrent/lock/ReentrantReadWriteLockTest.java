package mobi.concurrent.lock;

import mobi.common.Note;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Note("ReentrantReadWriteLock")
public class ReentrantReadWriteLockTest {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static int num = 0;

    private static int get() {
        readWriteLock.readLock().lock();
        try {
            return num;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    private static void put() {
        readWriteLock.writeLock().lock();
        try {
            for (int i = 0; i < 1000000; i++) {
                num++;
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName() + ": " + ReentrantReadWriteLockTest.get())).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                ReentrantReadWriteLockTest.put();
                System.out.println(Thread.currentThread().getName() + ": " + num);
            }).start();
        }

    }

}
