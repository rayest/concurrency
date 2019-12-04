package mobi.rayson.interrupt;

public class InterruptDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    // 如果当前线程因调用 sleep、wait、join 方法而阻塞，
                    // 会清除中断状态并接收到 InterruptedException 异常
                    // sleep: if any thread has interrupted the current thread.
                    // The interrupted status  of the current thread is
                    // cleared when this exception is thrown.
                    Thread.sleep(10);
                } catch (Exception e) {
                    // 什么被打断：sleep 是休眠被打断
                    System.out.println("收到中断消息: " + e.getMessage());
                }
            }
        });

        t1.start();
        System.out.println(t1.isInterrupted()); // false
        t1.interrupt(); // 中断，向 t1 线程发送中断消息。设置 t1 为中断状态。
        System.out.println(t1.isInterrupted()); // true

    }
}
