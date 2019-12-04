package mobi.rayson.interrupt.shutdown;

public class WorkerApp {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            System.out.println("开始执行...");
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       worker.interrupt();
    }
}
