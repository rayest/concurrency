package mobi.rayson.synchronization;

class UnsafeDemo implements Runnable {

    private int i = 1; // 共享变量未受到保护
    private static final int MAX = 500;

    @Override
    public void run() {
        while (i <= MAX) {
            // i ：非原子操作
            System.out.println(Thread.currentThread().getName() + ": " + i++);
        }
    }
}

public class UnsafeDemoApp {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new UnsafeDemo(), "t1");
        Thread t2 = new Thread(new UnsafeDemo(), "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("任务完成");
    }
}
