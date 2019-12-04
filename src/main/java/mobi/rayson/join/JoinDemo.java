package mobi.rayson.join;

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new JoinDemoClass(), "t1");
        t1.start();
        t1.join(1000); // main 线程等待 t1 执行一秒钟，然后自己执行
        System.out.println(Thread.currentThread().getName() + ": 执行");
    }
}

class JoinDemoClass implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": 执行");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
