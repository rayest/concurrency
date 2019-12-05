package mobi.rayson.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "即将被阻塞");
            LockSupport.park(); // 阻塞当前线程直到获取到许可
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果指定的线程因许可不足而阻塞，该方法可以使指定的线程获得许可
        LockSupport.unpark(thread);
    }
}
