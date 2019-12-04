package mobi.rayson.concurrent.lock;

import mobi.rayson.common.Note;

import java.util.concurrent.locks.ReentrantLock;

@Note("ReentrantLock")
public class ReentrantLockTest implements Runnable {

    private static ReentrantLock lock = new ReentrantLock(true);

    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReentrantLockTest(), "t1");
        Thread t2 = new Thread(new ReentrantLockTest(), "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(num);
    }

    @Override
    public void run() {

        for (int i = 0; i < 100000; i++) {
            lock.tryLock();
            try {
                System.out.println(Thread.currentThread().getName() + ": " + num);
                num++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
