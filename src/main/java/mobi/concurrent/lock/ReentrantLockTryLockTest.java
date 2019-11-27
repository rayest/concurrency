package mobi.concurrent.lock;

import mobi.common.Note;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Note("ReentrantLock")
public class ReentrantLockTryLockTest implements Runnable {

    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + ": 获取到了当前锁");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + ": 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + ": 持有当前锁，可以释放");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ReentrantLockTryLockTest(), "t1");
        Thread t2 = new Thread(new ReentrantLockTryLockTest(), "t2");
        t1.start();
        t2.start();
    }

}
