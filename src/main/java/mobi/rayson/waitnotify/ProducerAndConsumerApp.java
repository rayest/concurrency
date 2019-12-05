package mobi.rayson.waitnotify;

public class ProducerAndConsumerApp {

    private int i = 0;
    private static final Object lock = new Object();
    private static boolean hasOne = false;


    public static void main(String[] args) {
        ProducerAndConsumerApp client = new ProducerAndConsumerApp();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                client.produce();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                client.consume();
            }
        }).start();
    }

    private void produce() {
        synchronized (lock) {
            if (hasOne) {
                try {
                    lock.wait(); // 1. 如果已生产，则等待被消费
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++; // 2. 如果未生产，则执行生产
                System.out.println("生成: " + i);
                lock.notify();  // 3. 生产后，通知消费线程进行消费
                hasOne = true; // 4. 标记已有元素
            }
        }
    }

    private void consume() {
        synchronized (lock) {
            if (hasOne) { // 1. 如果已生产, 进行消费
                System.out.println("消费: " + i);
                lock.notify(); // 2. 消费完成，通知生产线程继续生产
                hasOne = false; // 3. 标记是否有元素待消费
            } else {
                try {
                    lock.wait(); // 4. 如果没有元素等待消费，则等待生产线程生产
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
