package mobi.rayson.synchronization;

class SafeWithSynchronized implements Runnable {
    private int i = 1;
    private static final int MAX = 100;
    private static final Object monitor = new Object(); // 需要锁住的对象

    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                if (i > MAX) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": " + i++);
            }
        }
    }
}

public class SafeWithSynchronizedApp {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new SafeWithSynchronized(), "t1");
        Thread t2 = new Thread(new SafeWithSynchronized(), "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("安全完成任务");
    }
}

