package mobi.rayson.thread;

public class WindowTicketSale implements Runnable {

    private static int TICKETS_AMOUNT = 100; // static 类变量，确保数据共享
    private static int SOLD_OUT_NUM = 1; // 类变量

    @Override
    public void run() {
        while (SOLD_OUT_NUM <= TICKETS_AMOUNT) {
            System.out.println(Thread.currentThread().getName() + ": 售票:" + SOLD_OUT_NUM++);
        }
    }
}
