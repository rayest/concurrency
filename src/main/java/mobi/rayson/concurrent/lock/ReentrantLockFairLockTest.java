package mobi.rayson.concurrent.lock;

import mobi.rayson.common.Note;

import java.util.concurrent.locks.ReentrantLock;

@Note("ReentrantLock 的公平锁测试")
public class ReentrantLockFairLockTest {

    private static ReentrantLock lock = new ReentrantLock(true);

    private static void testFairLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": 获得了锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ": 启动");
                ReentrantLockFairLockTest.testFairLock();
            }).start();
        }
    }

}
