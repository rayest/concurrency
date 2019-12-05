package mobi.rayson.threadgroup;

public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("tg1");
        Thread t1 = new Thread(threadGroup, "t1") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("thread:" + Thread.currentThread().getName() + ": groupName:" + getThreadGroup().getName() + ": parentName:" + getThreadGroup().getParent().getName());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
    }
}
