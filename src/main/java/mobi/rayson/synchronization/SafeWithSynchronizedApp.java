package mobi.rayson.synchronization;

class SafeWithSynchronized implements Runnable {
    private int i = 1;
    private int j = 1;
    private static final int MAX = 100;
    private static final Object monitor = new Object(); // 需要锁住的对象

    @Override
    public void run() {
        while (true) {
            // 同步方法块。锁是自定义的 monitor
            synchronized (monitor) {
                if (i > MAX) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-i: " + i++);
            }
        }
        while (true) {
            if (handleJ()) {
                break;
            }
        }
    }

    // 同步方法，锁是当前锁 this
    private synchronized boolean handleJ() {
        if (j > MAX) {
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-j: " + j++);
        return false;
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

