package mobi.rayson.interrupt.shutdown;

public class Worker extends Thread {

    @Override
    public void run() {
        while (true){
            if (Thread.interrupted()){
                System.out.println("线程中断，优雅退出");
                break;
            }
        }
    }
}
